package com.example.person_tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class PersonTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonTasksApplication.class, args);
    }

}
