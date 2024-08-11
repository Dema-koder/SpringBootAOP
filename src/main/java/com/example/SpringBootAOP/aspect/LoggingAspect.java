package com.example.SpringBootAOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
@Slf4j
public class LoggingAspect {

    @Around("bean(plantService)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Выполнение метода {} с аргуменетами {}", methodName, args);

        Object result = joinPoint.proceed(args);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, duration, result);

        return result;
    }
}
