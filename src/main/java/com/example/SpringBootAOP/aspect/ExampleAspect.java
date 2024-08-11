package com.example.SpringBootAOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExampleAspect {

    @Pointcut("execution(public void info())")
    public void beforePointcut() {}

    @Pointcut("execution(public void info())")
    public void afterPointcut() {
        log.error("error");
    }

    @Before("beforePointcut()")
    public void before() {
        log.info("before");
    }

    @After("afterPointcut()")
    public void after() {
        log.info("after");
    }
}
