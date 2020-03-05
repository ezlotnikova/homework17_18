package com.gmail.ezlotnikova.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gmail.ezlotnikova.web",
        "com.gmail.ezlotnikova.service",
        "com.gmail.ezlotnikova.repository"})
public class WebModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebModuleApplication.class, args);
    }

}