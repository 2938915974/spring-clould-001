package com.mytest;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author linzhengyu1
 * <p>
 * Spring Boot Admin Server的配置及启动类，springboot提供的生产级监控方案
 * </p>
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
    }
}



