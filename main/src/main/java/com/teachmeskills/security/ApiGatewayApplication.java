package com.teachmeskills.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.teachmeskills.security", "org.example.common"},
        exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.teachmeskills.security.client")
public class ApiGatewayApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
