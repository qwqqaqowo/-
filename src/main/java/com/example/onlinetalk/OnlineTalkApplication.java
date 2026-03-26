package com.example.onlinetalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.onlinetalk.mapper")
public class OnlineTalkApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineTalkApplication.class, args);
    }
}