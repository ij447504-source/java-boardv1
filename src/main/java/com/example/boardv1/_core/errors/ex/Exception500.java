package com.example.boardv1._core.errors.ex;

// 500 : 서버에러 
public class Exception500 extends RuntimeException {

    public Exception500(String message) {

        super(message);
    }

}
