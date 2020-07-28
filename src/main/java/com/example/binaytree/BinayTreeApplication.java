package com.example.binaytree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.binaytree.*"})
public class BinayTreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinayTreeApplication.class, args);
    }

}
