package com.dongl.servicemeeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceMeetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMeetingApplication.class, args);
    }

}
