package com.example.SpringBootAOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class ExceptionHandlerAspect {

    @AfterThrowing(pointcut = "within(com.example.SpringBootAOP.service.*) && " +
            "execution(* * (..)) throws com.example.SpringBootAOP.exception.Throw *", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("Проищошла ошибка при вызове метода: {}", joinPoint.getSignature().toLongString());
        log.info("Ошибка: {}", e.getMessage());
    }
}
