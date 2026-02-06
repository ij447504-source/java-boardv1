package com.example.boardv1._core.errors.ex;

//인증 실패시
// 401 : 인증안됨 
public class Exception401 extends RuntimeException {

    public Exception401(String message) {

        super(message);
    }

}
