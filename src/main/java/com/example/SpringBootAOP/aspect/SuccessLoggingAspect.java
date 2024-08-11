package com.example.SpringBootAOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class SuccessLoggingAspect {
    @AfterReturning("within(com.example.SpringBootAOP.service.*) &&" +
            "@within(com.example.SpringBootAOP.annotation.SuccessLogging)")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("Метод успешно выполнился: {}", joinPoint.getSignature().getName());
    }
}
