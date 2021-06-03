package com.mytest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "springcuid", path = "/")
public interface inforedis {
    @GetMapping("/user/test000")
    String a();
}
