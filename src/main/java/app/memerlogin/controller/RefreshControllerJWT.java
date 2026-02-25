package com.module.memerlogin.controller;

import com.ApiResponse.ApiResponse;
import com.module.principal.JwtTokenProvide.JwtTokenProvider;
import com.module.principal.Service.AuthService;
import com.module.principal.dto.SecurityPrincipal;
import com.module.principal.dto.TokenPair;
import com.module.principal.web.CookieHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class RefreshControllerJWT {


    private final AuthService authService; // ✅ 토큰방식 외부

    private final CookieHelper cookieHelper;

    private final JwtTokenProvider jwt;



    //✅Access/Refresh 토큰 발급 로직
    //Redis에 refresh 저장/삭제
    //쿠키(ACCESS_TOKEN, REFRESH_TOKEN) 내려주는 로직
    //JWT 필터에서 토큰 파싱 후 SecurityContextHolder에 Authentication 넣기
    //🧩토큰용 리플레시 (토큰용 통제권한)
    @PostMapping("/refresh")  //(서버가 허용한 refresh만 access를 재발급 자동화
                                 //시간권한조절은 Auth서비스에서 진행
    public ResponseEntity<ApiResponse<Void>> refresh(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response
    ) {
        if (refreshToken == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "REFRESH_TOKEN 없음", null));
        }

        TokenPair tokens = authService.refresh(refreshToken);

        cookieHelper.addHttpOnlyCookie(response, "ACCESS_TOKEN", tokens.getAccessToken(),  60);
        cookieHelper.addHttpOnlyCookie(response, "REFRESH_TOKEN", tokens.getRefreshToken(),  60);

        return ResponseEntity.ok(new ApiResponse<>(true, "OK", null));
    }





    /** ✅ 개인 로그아웃(= loginId의 모든 sid 폐기) */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @CookieValue(value="REFRESH_TOKEN", required=false) String refreshToken,
            HttpServletResponse response //쿠키삭제
    ) {
        if (refreshToken == null || !jwt.validate(refreshToken)) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "REFRESH_TOKEN 없음/유효하지 않음", null));
        }

        String loginId = jwt.parseRefreshLoginId(refreshToken);
        String sid     = jwt.parseRefreshSid(refreshToken); // ✅ sid 전용 파서
        authService.logout(loginId, sid);


        // ✅ 브라우저 쿠키 삭제 (무조건)
        cookieHelper.deleteCookie(response, "ACCESS_TOKEN");
        cookieHelper.deleteCookie(response, "REFRESH_TOKEN");


        return ResponseEntity.noContent().build();
    }








    /** ✅ 전체 로그아웃(= loginId의 모든 sid 폐기) */
    @PostMapping("/logout/all")
    public ResponseEntity<ApiResponse<Void>> allLogout(
            @CookieValue(value = "REFRESH_TOKEN", required = false) String refreshToken,
              HttpServletResponse response //쿠키삭제
    ) {
        if (refreshToken == null || !jwt.validate(refreshToken)) {
            return ResponseEntity.status(401)
                    .body(new ApiResponse<>(false, "REFRESH_TOKEN 없음/유효하지 않음", null));
        }

        String loginId = jwt.parseRefreshLoginId(refreshToken);
        authService.Alllogout(loginId);


        // ✅ 브라우저 쿠키 삭제 (무조건)
        cookieHelper.deleteCookie(response, "ACCESS_TOKEN");
        cookieHelper.deleteCookie(response, "REFRESH_TOKEN");


        return ResponseEntity.noContent().build();
    }
}
