package com.upuphub.dew.community.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DewPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewPushApplication.class, args);
    }

}
