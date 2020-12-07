package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableTurbine
@Controller
@EnableDiscoveryClient
public class TurbineApp {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApp.class,args);
    }

    @RequestMapping("/")
    public String home(){return "forward:/turbine.stream";}
}