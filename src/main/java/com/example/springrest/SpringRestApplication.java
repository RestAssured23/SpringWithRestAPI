package com.example.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringRestApplication.class);
        app.setAdditionalProfiles("qa"); // Set the profile you want
        app.run(args);
    /*    SpringApplication.run(SpringRestApplication.class, args);*/
    }

}
