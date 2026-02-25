package com.module.memerlogin.controller;


import com.ApiResponse.ApiResponse;
import com.module.memerlogin.dto.invite.InviteSignupResult;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import com.module.memerlogin.dto.membership.Signup.SignupRequestDTO;
import com.module.memerlogin.service.UserService;
import com.module.principal.dto.SecurityPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserControllerJWT {

    private final UserService userService;

    /**
     * ✅ 공통 회원가입
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createCommon(
            @RequestBody SignupRequestDTO req//Body(JSON) 를 읽어서 DTO로 변환
            //일반가입자는 인증요청 x 한번에 상세가입용으로 넣었음

    ) {

        // ✅ 초기 단계: 계정만 생성
        UserResponseDTO created = userService.createCommon(req.getUserReq(),req.getAuthReq());


        if (created == null) {
            return ResponseEntity.status(409).body(new ApiResponse<>(false, "중복 아이디가 있습니다.", null));
        }

        return ResponseEntity.status(201).body(new ApiResponse<>(true, "회원가입 성공", created));
    }


    /**
     * 🔃 직원(초대코드) 회원가입
     */
    @PostMapping("/create/invite")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createEmployee(
            @RequestBody UserRequestDTO userReq,
            @RequestParam("inviteCode") String inviteCodePlain //주요데이터 파람
    ) {             //// DOCTOR / NURSE / ADMIN


        // 🔐 초대코드 기반 가입
        //  /**
        //     * ✅ 직원(초대코드) 회원가입
        //     * //서비스는 값을 반환하고  컨트롤러는 클라이언트http로 반환
        //     */
        UserResponseDTO result = userService.createEmployee(userReq, inviteCodePlain);

        UserResponseDTO data = new UserResponseDTO();
        data.setUserId(result.getUserId());
        data.setLoginId(userReq.getLoginId());
        data.setRoleCode(result.getRoleCode());

        return ResponseEntity.status(201)
                .body(new ApiResponse<>(true, "직원 회원가입 성공", data));


    }

}




