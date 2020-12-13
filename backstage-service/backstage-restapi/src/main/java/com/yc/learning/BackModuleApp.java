package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages= "com.yc.learning.dao.impl")
public class BackModuleApp {
    public static void main(String[] args) {
        SpringApplication.run( BackModuleApp.class,args);
    }
}
