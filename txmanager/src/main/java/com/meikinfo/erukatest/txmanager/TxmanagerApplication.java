package com.meikinfo.erukatest.txmanager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class TxmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxmanagerApplication.class, args);
    }

}
