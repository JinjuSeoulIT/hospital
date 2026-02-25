package com.module.memerlogin.dto.login;

import lombok.Getter;
import lombok.Setter;

/**
 * ✅ 로그인 성공 응답
 *
 * - USER_ID 기반으로 통일 (AUTHENTICATION_ID는 폐기)
 */
@Getter
@Setter
public class LoginResponseDTO {

    private Long   userId;
    private String loginId;
    private String roleCode;


    }





