package com.sanyicloud;

import com.sanyicloud.sanyi.common.feign.annotation.EnableSanyiFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * by zhaowenyuan create 2021/10/22 12:19
 */
@EnableSanyiFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sanyicloud.**.mapper")
public class SanyiYoYoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanyiYoYoApplication.class,args);
    }
}
