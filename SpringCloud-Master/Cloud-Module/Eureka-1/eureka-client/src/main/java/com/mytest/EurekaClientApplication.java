package com.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author linzhengyu
 * <p>
 * Eureka 客户端 [之一]
 * </p>
 */
@SpringCloudApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
