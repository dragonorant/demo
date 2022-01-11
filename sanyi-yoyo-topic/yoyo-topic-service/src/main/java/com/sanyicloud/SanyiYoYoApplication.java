package com.sanyicloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * by zhaowenyuan create 2021/10/22 12:19
 */
@EnableFeignClients(basePackages = {"com.sanyicloud.yoyo"})
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sanyicloud.**.mapper")
public class SanyiYoYoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiYoYoApplication.class,args);
    }
}
