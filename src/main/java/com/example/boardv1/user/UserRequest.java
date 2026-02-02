package com.example.boardv1.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class JoinDTO { // 회원가입시 받을 정보보
        private String username;
        private String password;
        private String email;
    }
}