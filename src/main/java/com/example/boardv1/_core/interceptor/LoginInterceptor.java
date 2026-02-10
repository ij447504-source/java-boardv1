package com.example.boardv1._core.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.boardv1._core.errors.ex.Exception401;
import com.example.boardv1.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    // 뷰가 완성되면 만들어짐
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        System.out.println("--------view render complete------------");

    }

    // 컨트롤러 메서드 호출 직전 /boards, /replies 일때만 인증이 필요하기에 실행됨
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI(); // localhost:8080/hello/5 -> /hello/5

        // 예외: /boards/숫자 패턴 -> 인증 체크 안 함
        if (uri.matches(".*/boards/\\d+$")) {
            return true;
        }

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다");
        }

        return true; // 메서드 진입
    }

    // 컨트롤러 메서드 호출 직전
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("------------postHandel complete--------------");

    }

}
