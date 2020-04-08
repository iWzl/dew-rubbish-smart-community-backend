package com.upuphub.dew.community.relation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.upuphub.dew.community.relation.dao"})
public class DewRelationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DewRelationApplication.class, args);
    }

}
