package com.module.memerlogin.service.JpaService;

import com.module.authentication.entity.AuthenticationEntity;
import com.module.invite.entity.InviteTicketEntity;
import com.module.invite.repository.InviteTicketRepository;
import com.module.invite.service.JPAInviteService;
import com.module.memerlogin.dto.invite.InviteSignupResult;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.entity.UserEntity;
import com.module.memerlogin.mapstruct.Request.UserReqMapStruct;
import com.module.memerlogin.repository.UserRepository;


@Service
@RequiredArgsConstructor
//jpa가 맵퍼 , 매칭

public class JpaUserServiceImpl implements JpaUserService {


    private final UserRepository userRepository;
    private final UserReqMapStruct userReqMapStruct;

    private final JPAInviteService jpaInviteService;  //🔃


    /// ///////////////////////////////////////////////////////////

    //회원가입 트랙잭션
    //추가 정보
    //✅ 일반 회원가입: 계정만 생성
    //새 엔티티는 영속성 컨텍스트에 없음 (그래서 이건 따로 수정이랑 달리 세이브포인트로 만들어 놓음)
    @Override
    @Transactional
    public Long createCommon(UserRequestDTO userReq, String passwordHash) {


        /////////////////////////////////////////////////////////////////////
        //아이디 맵스테이트 (여기부터 ID/PW/ 트랙잭션처리)
        UserEntity user = userReqMapStruct.toEntity(userReq);
        // 1) user 맵스테이트를 처리했기때문에 따로 겟을 안불러옴
//        user.setLoginId(userReq.getLoginId());
        user.setPasswordHash(passwordHash); //비밀번호 헤시저장


        // ✅ 이거 추가 (둘 중 하나로 통일)
        user.setRoleCode("COMMON"); // 또는 "USER"

//        user.setLoginType("LOCAL"); // ✅ 이거 반드시
        // 1) 먼저 user 저장 (login_id 확실히 DB에 들어감)
        UserEntity savedUser = userRepository.save(user);
        //여기까지 저장후  뒤에 만약 실패하면 롤백
/////////////////////////////////////////////////////////////////////////////////////////////////

//         ----------------------------------------------------------------------
//         (참고/보관) 예전 동시 커밋 방식
//          - 회원가입 시 계정(MEMBER_LOGIN) + 상세(MEMBER_PROFILE)를 한 트랜잭션에서 같이 저장
//          - 지금은 상세를 별도 서비스(/api/profile)로 분리해서 처리

//         AuthenticationEntity auth = authenticationReqMapStruct.toEntity(authReq);
//         auth.setUser(savedUser);
//         authenticationRepository.save(auth);
         //----------------------------------------------------------------------
        return savedUser.getUserId();
//역할이 고정값이라서
        //dto.setRoleCode("COMMON");
//.getUserId()하나만 리턴
    }




    /**
     * ✅ 초대코드 가입: 초대권 락→계정생성→초대권 소진→STAFF/타입 생성까지 한 트랜잭션
     */
    @Override
    @Transactional
    public InviteSignupResult createEmployee(UserRequestDTO userReq, String passwordHash, String inviteCodePlain) {

//      // 1️⃣ 초대권 확보 + row lock (레지스파토리로 안쓸거면 이걸로)
        // (등록코드 부분 레지스파토리에 하드코딩처리해놨음)
//      InviteTicketEntity ticket =inviteService.lockUsableTicket(inviteCodePlain);

        // 🧠1) 초대권 락 + role 확정 (레지스파토리로 쓰는중)
        InviteTicketEntity ticket = jpaInviteService.lockUsableTicket(inviteCodePlain); // 등록 엔터티)

        //  2) (권장) 여기서 roleCode를 ticket에서 확정
        String roleCode = ticket.getRoleCode();  // ⭐ 여기서 프론트로 날라오면서 로그코드로확정

        // 4)☑️ 권한 확인용
        validateRole(roleCode);
//(초대 활성화) 오케스트레이션 메서드가 jpaInviteService.lockUsableTicket()를 호출해서
//락된 ticket을 손에 쥐고 있는 상태
//        user 생성(ROLE_CODE 저장까지)
//        invite.consume(userId)로 매칭
//        Result 반환중


        // 3) 계정 생성 (JPA save)
        UserEntity user = userReqMapStruct.toEntity(userReq);
        user.setPasswordHash(passwordHash);
        user.setRoleCode(roleCode);              // ⭐ 로그인테이블 ROLE_CODE 저장

//        Long userId = userRepository.save(user).getUserId();
        Long userId = userRepository.saveAndFlush(user).getUserId();
        // 🧠4) 초대권 소진
        ////선택할시 roleCode 확정용
        //userId  PK저장

        jpaInviteService.consume(ticket, userId);


    return new InviteSignupResult(userId, roleCode);
        //“이 초대코드로 가입한 결과는
        // userId = 10
        // roleCode = DOCTOR
        //검증용
    }


    private void validateRole(String roleCode) {
        switch (roleCode) {    // ✅ 허용(통과)
            case "DOCTOR", "NURSE", "ADMIN" -> {
            }
            default -> throw new IllegalArgumentException("roleCode가 올바르지 않습니다.");
        }

    }
}









//트랙잭선 정리//
//“FK 주인(auth)에 user를 박아라. user 쪽 set만 하면 FK 안 들어가서 깨진다.”
//“insertUser 1개 + insertAuthentication 1개” 를 JPA에선 어떻게 처리?
//JPA에선 보통 두 가지 패턴 중 하나로 가.
//
//A안) 서비스 메서드 1개로 묶어서 한 번에 처리 (지금 너 코드 방향)
//
//signupWithAuthentication() 하나에서
//
//User 만들고
//
//Authentication 만들고
//
//연관관계 세팅하고
//
//save() 한 번(또는 두 번) 호출
//
//이게 현업에서도 제일 흔함. (회원가입은 “한 유스케이스”)