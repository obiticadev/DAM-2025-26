package com.masterclass.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

/**
 * Punto de arranque del bootcamp. Mongo se autoconfigura de forma perezosa:
 * los bloques que no usan Mongo no requieren un servidor levantado.
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class ApiRestMasterclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestMasterclassApplication.class, args);
    }
}
