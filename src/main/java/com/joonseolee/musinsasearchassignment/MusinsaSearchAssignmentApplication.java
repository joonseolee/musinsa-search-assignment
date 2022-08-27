package com.joonseolee.musinsasearchassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MusinsaSearchAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusinsaSearchAssignmentApplication.class, args);
    }

}
