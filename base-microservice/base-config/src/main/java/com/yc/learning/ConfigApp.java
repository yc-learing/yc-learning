package com.yc.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author liyan
 * @create 2020-11-2020/11/12-19:45
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
@EnableConfigServer
public class ConfigApp {
    public static void main(String[] args) {
        System.out.print("fdsfadsf");
        SpringApplication.run(ConfigApp.class, args);
    }
}
