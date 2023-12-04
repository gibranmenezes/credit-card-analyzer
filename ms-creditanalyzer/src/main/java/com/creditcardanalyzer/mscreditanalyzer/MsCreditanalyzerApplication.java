package com.creditcardanalyzer.mscreditanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCreditanalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCreditanalyzerApplication.class, args);
    }

}
