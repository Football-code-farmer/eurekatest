package com.meikinfo.erukaprovide.erukaprovide;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan(value = "com.meikinfo.erukaprovide.erukaprovide")
public class ErukaprovideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErukaprovideApplication.class, args);
    }

}
