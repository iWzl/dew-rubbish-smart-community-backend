package com.upuphub.dew.community.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/18 00:21
 */
@MapperScan({"com.upuphub.dew.community.account.dao"})
@SpringBootApplication
@EnableDiscoveryClient
public class DewAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(DewAccountApplication.class, args);
    }

}
