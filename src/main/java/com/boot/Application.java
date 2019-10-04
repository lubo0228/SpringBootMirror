package com.boot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})
@EnableSwagger2
@EnableBatchProcessing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
} 
