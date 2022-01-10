package com.sanyicloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * by zhaowenyuan create 2022/1/5 17:55
 */
@EnableFeignClients(basePackages = "com.sanyicloud.game")
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sanyicloud.**.mapper")
public class SanyiGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiGameApplication.class,args);
    }
}
