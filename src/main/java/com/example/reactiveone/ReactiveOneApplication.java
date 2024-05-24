package com.example.reactiveone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@SpringBootApplication
public class ReactiveOneApplication implements AsyncConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveOneApplication.class, args);
    }

}
