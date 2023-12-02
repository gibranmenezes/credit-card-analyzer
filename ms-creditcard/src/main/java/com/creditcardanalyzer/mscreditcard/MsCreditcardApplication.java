package com.creditcardanalyzer.mscreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCreditcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCreditcardApplication.class, args);
    }

}
