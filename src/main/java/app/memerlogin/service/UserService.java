package com.module.memerlogin.service;

import com.module.authentication.dto.AuthenticationRequestDTO;
import com.module.memerlogin.dto.login.LoginRequestDTO;
import com.module.memerlogin.dto.login.LoginResponseDTO;
import com.module.memerlogin.dto.login.LoginRowDTO;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import com.module.principal.dto.SecurityPrincipal;


public interface UserService {



    /**
     * ✅ 로그인 유스케이스(컨트롤러가 호출)
     * - loginId로 passwordHash 조회
     * - matches() 검증
     * - 응답 DTO 구성
     */
    LoginResponseDTO login(LoginRequestDTO loginReq);
    /**
     * ✅ 회원가입 유스케이스(컨트롤러가 호출)
     //  * - 중복체크(조회=MyBatis)
     //  * - 비번 해시(SecurityService)
     //  * - 저장(쓰기=JPA)
     //  * - 응답 DTO 구성
     */



    /**
     * ✅ 회원가입(계정 생성)
     * - 초기 단계에서는 MEMBER_LOGIN만 생성한다.
     * - 상세정보(MEMBER_PROFILE)는 별도 API(/api/profile)에서 생성한다.
     */
    UserResponseDTO createCommon(UserRequestDTO userReq , AuthenticationRequestDTO authReq );





    // ----------------------------------------------------------------------
    // (참고/보관) 예전 동시 커밋 방식
    //  - 회원가입 시 계정 + 상세정보를 한 트랜잭션에서 같이 저장
    //  - 지금은 상세는 별도 서비스에서 처리하도록 분리
    // ----------------------------------------------------------------------
    // UserResponseDTO createLogin(UserRequestDTO userReq, AuthenticationRequestDTO authReq);

    UserResponseDTO createEmployee(UserRequestDTO userReq, String inviteCodePlain);





    //⭐me
    UserResponseDTO Me(SecurityPrincipal principal);



}