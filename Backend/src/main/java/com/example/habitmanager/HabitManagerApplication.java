package com.example.habitmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:CredInfo.properties")
public class HabitManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabitManagerApplication.class, args);
    }

}
