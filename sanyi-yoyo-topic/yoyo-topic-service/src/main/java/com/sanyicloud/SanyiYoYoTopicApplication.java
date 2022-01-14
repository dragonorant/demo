package com.sanyicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * by zhaowenyuan create 2021/10/22 12:19
 */
@EnableFeignClients(basePackages = {"com.sanyicloud.yoyo"})
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan
public class SanyiYoYoTopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiYoYoTopicApplication.class,args);
    }
}
