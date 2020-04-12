package com.upuphub.dew.community.machine.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/10 23:59
 */

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.upuphub.dew.community.machine.api.service.remote"})
@SpringBootApplication
@MapperScan(basePackages = "com.upuphub.dew.community.machine.api.dao")
public class DewMachineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DewMachineApiApplication.class, args);
    }

}
