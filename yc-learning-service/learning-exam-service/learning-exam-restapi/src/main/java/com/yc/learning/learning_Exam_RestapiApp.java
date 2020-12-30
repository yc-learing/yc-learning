package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.yc.learning.**","com.yc.learning.aspect"})
@MapperScan(basePackages= "com.yc.learning.dao.impl")
@EnableAspectJAutoProxy//启动AOP编程
public class learning_Exam_RestapiApp {
    public static void main(String[] args) {
        SpringApplication.run( learning_Exam_RestapiApp.class,args);

    }
}
