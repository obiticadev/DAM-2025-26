package com.masterclass.api.b07_dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Lanzador de Spring Boot autónomo para el bloque b07_dto.
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class, org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class})
public class ApiRestMasterclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestMasterclassApplication.class, args);
    }
}
