package com.upuphub.dew.community.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leo Wang
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DewMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewMessageApplication.class, args);
    }

}
