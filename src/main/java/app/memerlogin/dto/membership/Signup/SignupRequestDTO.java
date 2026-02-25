package com.module.memerlogin.dto.membership.Signup;

import com.module.authentication.dto.AuthenticationRequestDTO;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//✅일반회원 가입 작업용
//ID +PW + 상세 프로필가입

public class SignupRequestDTO {
    private UserRequestDTO userReq;              //ID/PW  DTO
    private AuthenticationRequestDTO authReq; //상세정보 DTO
}