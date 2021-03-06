package com.meikinfo.erukaconsumer.erukaconsumer;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableDistributedTransaction
public class ErukaconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErukaconsumerApplication.class, args);
    }
}
