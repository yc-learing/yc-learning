package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liyan
 * @create 2020-12-2020/12/28-18:43
 */
@SpringBootApplication(scanBasePackages = {"com.yc.learning.**","com.yc.learning.aspect"})
@MapperScan(basePackages= "com.yc.learning.dao.impl")
@EnableAspectJAutoProxy//启动AOP编程
public class UserModuleApp {
    public static void main(String[] args) {
        SpringApplication.run( UserModuleApp.class,args);
    }
}
