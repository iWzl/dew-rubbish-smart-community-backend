package com.upuphub.dew.community.push;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.upuphub.dew.community.push.dao")
public class DewPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewPushApplication.class, args);
    }

}
