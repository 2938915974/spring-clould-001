package com.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author linzhengyu
 * <p>
 * 基于 RestTemplate 的 HTTP 远程调用 -> Feign
 * </p>
 */
@EnableFeignClients
@SpringCloudApplication
public class FeignServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }
}
