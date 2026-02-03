package com.example.boardv1.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    /**
     * 1. 브라우저의 쿠키에 sessionKey 삭제
     * 2. 30분 동안 요청하지 않기(request) - 금융권 5분
     * 3. 모든브라우저종료 > 다시 로그인 필요
     * 4. 서버 로그아웃(락카를 비우기)
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";// 로그아웃하고나면 메인페이지로 넘어가기
    }

    // 조회인데, 예외로 post 요청 (로그인하는것이 조회임)
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO, HttpServletResponse resp) {
        User sessionUser = userService.로그인(reqDTO.getUsername(), reqDTO.getPassword());
        session.setAttribute("sessionUser", sessionUser);
        // http Response header에 set-Cookie : sessionKey 저장되서 응답됨.
        Cookie cookie = new Cookie("username", sessionUser.getUsername()); // 로그인정보 안 넣어도되게 하기
        cookie.setHttpOnly(false); // true = 자바스크립트 접근불가 /false 접근가능능
        resp.addCookie(cookie);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        userService.회원가입(reqDTO.getUsername(), reqDTO.getPassword(), reqDTO.getEmail());
        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }
}