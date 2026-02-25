package com.module.memerlogin.dto.membership.Response;






//출력용 me (프론트)//

import lombok.Data;

@Data
//비밀번호 평문용
//**회원가입(용)** 응답전용(Res) 프론트 DTO (출력) 보내기
//서버 내부에서 JPA 요청에 쓰이도록 가공

//🔁me 직업용 호출
public class UserResponseDTO {

    private Long   userId;
    private String loginId;
    private String roleCode;  //🔁 초대코드 가입
    //// DOCTOR / NURSE / ADMIN


    // ✅ 공통 프로필 존재 여부 판정용
    private Long profileId; // 없으면 null
    private String displayName;  // 헤더 표시용(실명/별칭)
    private String phone;
    private String email;        // 선택
    private String imageUrl;
}
