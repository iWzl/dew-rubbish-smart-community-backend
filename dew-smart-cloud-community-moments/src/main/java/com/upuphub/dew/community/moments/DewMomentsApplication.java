package com.upuphub.dew.community.moments;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan({"com.upuphub.dew.community.moments.dao"})
@SpringBootApplication
@EnableDiscoveryClient
public class DewMomentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewMomentsApplication.class, args);
    }

}
