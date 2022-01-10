package com.sanyicloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * by zhaowenyuan create 2021/11/3 15:22
 */
@EnableFeignClients(basePackages = "com.sanyicloud.account")
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sanyicloud.**.mapper")
public class SanyiAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiAccountApplication.class,args);
    }
}
