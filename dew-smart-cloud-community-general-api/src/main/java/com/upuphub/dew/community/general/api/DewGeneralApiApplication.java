package com.upuphub.dew.community.general.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cc.itsc.rbc.api.service.rpc"})
@SpringBootApplication
@MapperScan(basePackages = "cc.itsc.rbc.api.dao")
public class DewGeneralApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewGeneralApiApplication.class, args);
    }

}
