package com.module.memerlogin.controller;

import com.ApiResponse.ApiResponse;
import com.module.memerlogin.dto.login.LoginRequestDTO;
import com.module.memerlogin.dto.login.LoginResponseDTO;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import com.module.memerlogin.service.UserService;
import com.module.principal.Service.AuthService;
import com.module.principal.dto.SecurityPrincipal;
import com.module.principal.dto.TokenPair;
import com.module.principal.web.CookieHelper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class LoginControllerJWT {

    private final AuthService authService; // ✅ 토큰방식 외부 // 쿠키서비스 

    private final UserService userService; // ✅ 기존 로그인 검증 재활용 //검증은 세션방식 인코더,매치스로 하는중 //시큐리터 서비스

    private final CookieHelper cookieHelper;//✅ 쿠키설정핸들러


     //기존 세션 ✅ 세션 = 서버가 상태(state) 저장
     //    서버가 JSESSIONID(키) 발급
     //    실제 로그인 정보는 서버(=Redis) 에 저장
     //    요청 올 때마다 JSESSIONID로 Redis에서 꺼내서 “로그인 상태” 확인

    //✅ 컨트롤러 → UserService(로그인 검증) → AuthService(토큰 발급+Redis 저장) → 쿠키 세팅
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(
            @RequestBody LoginRequestDTO req,
                         HttpServletResponse response
    ) {
        // 1) 🔄 기존 검증 로직 사용 (userId/loginId/authenticationId 채워줌)
        LoginResponseDTO data = userService.login(req);
        if (data == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "로그인 실패", null));
        }

        // 2) 🔄 principal (프린퍼설) 만들기 (staffType은 지금 없으니 기본 USER로)
        //    🔄 principal 원래 auth 내부에서 만들어야하지만 흐름확인용으로 만듬)
        SecurityPrincipal principal = new SecurityPrincipal(
                data.getUserId(),
                data.getLoginId(),
                data.getRoleCode());

        /// //////////////////////////////////////////////////////////
         //는 **오케스트레이션(흐름 제어)**
        // 3) 🧩토큰 발급,재발급요청 + refresh Redis 저장
        TokenPair tokens = authService.login(principal);
        /// ///////////////////////////////////////////////////////////
        // 4) 🧩 쿠키로 내려주기 (필터가 ACCESS_TOKEN 읽게)
        //에세스 토큰: 짧게(노출돼도 피해 최소화)
        //서명검증만 (DB/Redis 안 봄)

        //쿠키로 심기
        cookieHelper.addHttpOnlyCookie(response, "ACCESS_TOKEN", tokens.getAccessToken(), 15 * 60); // 15분
        //Access Token  요청 올 때마다 서버가 Redis/DB를 뒤질 필요 없이
        //서명 검증 + 만료만 확인하고 통과
        //Access는 들고 다니면서 빠르게 인증


        //리플레시 토큰: 길게(자주 로그인 안 하게)
        //Redis 화이트리스트 확인 (서버 상태 봄)

        //쿠키로 심기
        cookieHelper.addHttpOnlyCookie(response, "REFRESH_TOKEN", tokens.getRefreshToken(), 14 * 24 * 60 * 60); // 14일
//        🧩 강제 로그아웃: Redis에서 refresh 삭제하면 끝
//        🧩 토큰 회전: refresh 재발급할 때 이전 refresh는 폐기
//        🧩 탈취 대응: Redis에 없는 refresh면 무조건 거부
//        🧩 Refresh는 Redis로 통제해서 강제 로그아웃/탈취 대응 가능
//        한마디로  세션+토큰방식 인증은 가볍게  통제는 가능하게 만들어놨음 (하이브리드 JWT 현업방식)



        // 5) 응답 바디는 기존 스타일 유지 (토큰은 굳이 안 내려도 됨)
        return ResponseEntity.ok(new ApiResponse<>(true, "로그인 성공", data));
    }






    /** ⭐ 내 계정 수정 (HTTP 클라이언트 principal 기반) */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> me(
            @AuthenticationPrincipal
            //**“SecurityContext에 들어있는 principal(사용자)을 컨트롤러 파라미터로 바로 꺼내주는 애노테이션”**
            SecurityPrincipal principal) {

        if (principal == null)

        {return ResponseEntity.status(401).body(new ApiResponse<>(false, "정보가 없습니다.", null));}

        UserResponseDTO data = userService.Me(principal);

        return ResponseEntity.ok(new ApiResponse<>(true, "성공", data));
    }




//    //쿠키헬퍼  //러플릭스(외부호출)
//    ✅ 현재 프린서펄 모듈폴더안에 web으로 따로 분리
    
//    public void addHttpOnlyCookie(HttpServletResponse response, String name, String value, int maxAgeSeconds) {
//        Cookie cookie = new Cookie(name, value);  //쿠키 생성
//        cookie.setHttpOnly(true);                //JS에서 document.cookie로 못 읽게 막음 (XSS 방어)
//        cookie.setSecure(false);                 // HTTPS에서만 보내게 할지 여부 (운영은 보통 true)
//        cookie.setPath("/");                    //사이트 전체 요청에 쿠키를 같이 보냄
//        cookie.setMaxAge(maxAgeSeconds);         //쿠키 만료시간(초)
//        // SameSite는 Cookie API로 직접 설정이 애매하니(서블릿 버전에 따라)
//        // 운영에서는 ResponseHeader로 Set-Cookie를 직접 만들어 붙이는 방식도 많이 씀
//        response.addCookie(cookie);   //응답 헤더(Set-Cookie)에 실어서 브라우저로 전달
//    }




}