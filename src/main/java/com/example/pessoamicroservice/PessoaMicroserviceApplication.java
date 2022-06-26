package com.example.pessoamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class PessoaMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoaMicroserviceApplication.class, args);
    }

}
