package com.upuphub.dew.community.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 21:41
 */

@SpringBootApplication
@EnableDiscoveryClient
public class DewMachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(DewMachineApplication.class, args);
    }

}
