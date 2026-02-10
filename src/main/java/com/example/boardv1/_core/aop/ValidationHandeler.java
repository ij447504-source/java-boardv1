package com.example.boardv1._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.boardv1._core.errors.ex.Exception400;

@Aspect
@Component
public class ValidationHandeler {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void validationCheck(JoinPoint jp) {
        for (Object arg : jp.getArgs()) {
            // Errors 타입 파라미터를 찾으면
            if (arg instanceof Errors errors) {
                // 에러가 있으면 Exception400 throw
                if (errors.hasErrors()) {
                    throw new Exception400(
                            errors.getAllErrors().get(0).getDefaultMessage());
                }

            }
        }
    }
}