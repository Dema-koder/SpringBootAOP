package com.example.SpringBootAOP.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExampleBean {

    public void info() {
        log.info("info");
    }
}
