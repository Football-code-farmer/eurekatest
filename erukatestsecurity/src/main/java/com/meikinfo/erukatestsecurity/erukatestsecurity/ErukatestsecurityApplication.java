package com.meikinfo.erukatestsecurity.erukatestsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author swh
 * @create: 2020-03-02 16:03
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan("com.meikinfo.erukatestsecurity.erukatestsecurity.dao")
public class ErukatestsecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErukatestsecurityApplication.class,args);
    }
}
