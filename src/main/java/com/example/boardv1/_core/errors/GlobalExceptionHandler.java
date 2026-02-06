package com.example.boardv1._core.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.boardv1._core.errors.ex.Exception400;
import com.example.boardv1._core.errors.ex.Exception401;
import com.example.boardv1._core.errors.ex.Exception403;
import com.example.boardv1._core.errors.ex.Exception404;
import com.example.boardv1._core.errors.ex.Exception500;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 행위만들기

    @ExceptionHandler(exception = Exception400.class) // 유효성 검사사
    public String ex400(Exception400 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception401.class) // 로그인불가
    public String ex401(Exception401 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    location.href='/login-form';

                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception403.class) // 권한없음.
    public String ex403(Exception403 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        // log남기기
        return html;
    }

    @ExceptionHandler(exception = Exception404.class) // 자원X
    public String ex404(Exception403 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception500.class) // 서버측 에러 (겟메세지불가)
    public String ex500(Exception500 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());

        return html;
    }

    @ExceptionHandler(exception = Exception.class) // 서버측 에러 (겟메세지불가)
    public String exUnknown(Exception e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, "관리자에게 문의하세요");
        System.out.println("error:" + e.getMessage());
        // 1. 로그
        // 2. SMS알림 -> e.getMassge() 잡지않은 것들은 모두 여기오는 것임.
        return html;
    }
}
