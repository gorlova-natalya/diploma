package com.teachmeskills.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.teachmeskills.documents", "org.example.common"})
public class DocumentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentsServiceApplication.class, args);
    }
}
