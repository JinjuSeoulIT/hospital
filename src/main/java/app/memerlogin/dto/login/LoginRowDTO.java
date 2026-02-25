package com.module.memerlogin.dto.login;

import lombok.Data;


//검증용  (중간서버내부용 검출)
    @Data
    public class LoginRowDTO {

        private Long   userId;
        private String loginId;
        private String passwordHash;  // 서버 내부용
        private String loginType;
        private String roleCode;

    }


