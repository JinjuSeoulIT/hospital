package com.module.memerlogin.dto.invite;

/**
 * 초대코드 기반 가입(Invite Signup) 유스케이스의 "성공 결과"를 서버에서 확정한 값으로 고정해 내려준다.
 */

//🧠내부용 발급 검증 (이건 닥터, 간호사 , 관리자 3개의 타입이 들어와야하기에 레코드로 가져옴) DOCTOR/NURSE/ADMIN
//🧠유저 JPA입력서비스에서 쓰는중  정제후 값 반환중

public record InviteSignupResult(

        Long userId,
        String roleCode   //2종류이상의 값반환은 이런식으로 레코드로 씌워서 가져보내야함

) {}



