package com.example.pessoamicroservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class PessoaMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoaMicroserviceApplication.class, args);
    }

    @Bean
    NewTopic peopleTopic() {
        return TopicBuilder.name("people-topic").partitions(2).replicas(3).build();
    }
}
