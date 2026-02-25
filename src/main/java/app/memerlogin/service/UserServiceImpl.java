package com.module.memerlogin.service;

import com.module.authentication.dto.AuthenticationRequestDTO;
import com.module.authentication.mapper.AuthenticationMapper;
import com.module.authentication.service.JpaAuthenticationService.JPAAuthenticationService;
import com.module.memerlogin.dto.invite.InviteSignupResult;
import com.module.memerlogin.service.SecurityService.SecurityService;
import com.module.principal.dto.SecurityPrincipal;

import lombok.RequiredArgsConstructor;

import com.module.memerlogin.dto.login.LoginRequestDTO;
import com.module.memerlogin.dto.login.LoginResponseDTO;
import com.module.memerlogin.dto.login.LoginRowDTO;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import com.module.memerlogin.mapper.UserMapper;
import com.module.memerlogin.service.JpaService.JpaUserService;

import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;                     // ✅유저 조회(MyBatis)
    private final AuthenticationMapper authenticationMapper; // userId -> profile 존재여부 조회

    // ✅ 해시/검증
    private final JpaUserService jpaUserService;              // ✅ 쓰기(JPA)
    private final SecurityService securityService;

    private final JPAAuthenticationService jpaauthenticationService;



    //로그인 조회  로그인할때 (검증)
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginReq) {
        // 1) loginId로 유저 row 조회 (userId, loginId, passwordHash, loginType)
        LoginRowDTO row = userMapper.selectLoginRowByLoginId(loginReq.getLoginId());
        if (row == null) {
            return null;
        }

//         2) 비밀번호 검증 (encode X, matches O)
        boolean ok = securityService.matches(loginReq.getPassword(), row.getPasswordHash());
        if (!ok) {
            return null;
        }
        LoginResponseDTO res = new LoginResponseDTO();
        res.setUserId(row.getUserId());
        res.setLoginId(row.getLoginId());
        res.setRoleCode(row.getRoleCode());

        return res;
    }






    //✅ 공통 회원가입
    @Override
    @Transactional
    public UserResponseDTO createCommon(UserRequestDTO userReq ,AuthenticationRequestDTO authReq) {

        // ✅ 중복 체크는 서비스에서 같은 아이디 확인
        // [수정] countByLoginId 제거 → existsByLoginId로 통일
        // 중복 체크
        if (userMapper.existsByLoginId(userReq.getLoginId()) > 0) return null;

        String hash = securityService.encode(userReq.getPassword());

        // 1) 계정 생성 (같은 트랜잭션 안)
        Long userId = jpaUserService.createCommon(userReq, hash);

        try {
        // 2) 프로필 생성 (같은 트랜잭션 안)
        jpaauthenticationService.createProfile(userId, authReq);
        } catch (Exception error) {
            // ✅ 무조건 전체 롤백
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("프로필 생성 실패", error);
        }


        // 3) 응답
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(userId);
        dto.setLoginId(userReq.getLoginId());
        dto.setRoleCode("COMMON");
        return dto;
        }




    //🔃 직업 회원가입 (직업용)
    //(직업가입은 하나의 트랜잭션이 등록 권한부여까지만 하도록 하며 상세가입은 따로 해놨음)
    @Override

    public UserResponseDTO createEmployee(UserRequestDTO userReq, String inviteCodePlain) {


        // 0) 입력 검증
        if (inviteCodePlain == null || inviteCodePlain.isBlank()) throw new IllegalArgumentException("초대코드 입력");
        if (userMapper.existsByLoginId(userReq.getLoginId()) > 0) throw new IllegalStateException("중복 loginId");

        // ✅ 비번해시 (여기서!)
        String hash = securityService.encode(userReq.getPassword());



        // 5) 티켓 사용 처리 (USED로)
        //// 🔃 여기서 "로그인 생성" 따로 하지 말고, 아래 한 방에 처리중 (트랙잭션)
        InviteSignupResult result = jpaUserService.createEmployee(userReq, hash, inviteCodePlain);


        // ✅ 응답 DTO
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(result.userId());   // ✅ 실제 생성된 userId

        dto.setLoginId(userReq.getLoginId());

        dto.setRoleCode(result.roleCode());  //☑️ DOCTOR/NURSE/ADMIN 여러개의 토큰타입 DTO ⭐record(레코드활용)

        return dto;

    }


    //⭐me
    @Override
    public UserResponseDTO Me(SecurityPrincipal principal) {
        Long userId = principal.getUserId();

        UserResponseDTO profileId = userMapper.findMeByUserId(userId);

        if (profileId == null) {
            // 토큰은 있는데 DB에 user가 없다는 뜻 → 비정상
            throw new RuntimeException("ME 조회 실패: 사용자 없음");
        }

        return profileId;
    }


}