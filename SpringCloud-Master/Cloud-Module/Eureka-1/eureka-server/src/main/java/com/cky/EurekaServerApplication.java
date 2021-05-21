package com.cky;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author linzhengyu
 * <p>
 * Spring Cloud 注册中心 - Eureka
 * </p>
 */
@EnableEurekaServer
@SpringCloudApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
