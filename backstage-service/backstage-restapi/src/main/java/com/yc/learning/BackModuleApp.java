package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import tk.mybatis.spring.annotation.MapperScan;


@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication(scanBasePackages = {"com.yc.learning.**"})
@MapperScan(basePackages= "com.yc.learning.dao.impl")
public class BackModuleApp {
    public static void main(String[] args) {
        SpringApplication.run( BackModuleApp.class,args);
    }
}
