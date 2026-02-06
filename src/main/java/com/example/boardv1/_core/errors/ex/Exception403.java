package com.example.boardv1._core.errors.ex;

//인증 실패시
// 403 : 권한실패 
public class Exception403 extends RuntimeException {

    public Exception403(String message) {

        super(message);
    }

}
