package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHystrixDashboard
@Controller
public class HystrixDashboradApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboradApp.class,args);
    }

    //自动将本地址的所有请求转发到/hystrix上
    @RequestMapping("/")
    public String home(){return "forward:/hystrix";}
}
