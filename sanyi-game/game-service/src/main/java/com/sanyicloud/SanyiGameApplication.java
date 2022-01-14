package com.sanyicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * by zhaowenyuan create 2022/1/5 17:55
 */
@EnableFeignClients(basePackages = "com.sanyicloud.game")
@EnableDiscoveryClient
// dynamic 读写分离
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@SpringBootApplication
@ServletComponentScan
public class SanyiGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiGameApplication.class,args);
    }
}
