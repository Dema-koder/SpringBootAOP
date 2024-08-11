package com.example.SpringBootAOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
@Order(4)
public class AsyncRunnerAspect {

    @Pointcut("execution(@com.example.SpringBootAOP.annotation.Asynchronously public void add*(..))")
    public void asyncRunnerPointcut() {}

    @Around("asyncRunnerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("Асинхронный запуск в AsyncRunnerAspect");
                joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("Ошибка в AsyncRunnerAspect", throwable);
            }
        });
    }
}
