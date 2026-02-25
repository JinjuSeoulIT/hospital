package com.module.memerlogin.service.JpaService;
import com.module.memerlogin.dto.invite.InviteSignupResult;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;


public interface JpaUserService {

    /**
     * ✅ 회원가입: 계정(MEMBER_LOGIN)만 생성
     * - 상세(MEMBER_PROFILE)는 별도 서비스에서 생성
     */
    Long createCommon(UserRequestDTO userReq, String passwordHash);

    // ----------------------------------------------------------------------
    // (참고/보관) 예전 동시 커밋 방식
    //  Long createLoginId(UserRequestDTO userReq, AuthenticationRequestDTO authReq, String passwordHash);
    // ----------------------------------------------------------------------


    // 🔄 초대코드 직원용가입 : 여기서 한방에 생성
    InviteSignupResult createEmployee(UserRequestDTO userReq,
                                             String passwordHash,
                                             String inviteCodePlain);



     
//    void updateAuthentication(Long userId, AuthenticationRequestDTO authReq);
//
//
//
//    void deleteUser(Long userId);
}