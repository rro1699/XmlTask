package com.example.xmltask;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class XmlTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmlTaskApplication.class, args);
    }
}