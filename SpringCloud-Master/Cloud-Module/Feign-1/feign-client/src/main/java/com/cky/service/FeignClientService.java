package com.cky.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wales
 * <p>
 * 通过 Feign 的方式来调用接口
 */
@FeignClient(name = "feign-server", path = "/")
public interface FeignClientService {

    /**
     * 调用的第一个 无参 的 GET 请求方法
     *
     * @return {@link String}
     */
    @GetMapping(value = "/feign/get")
    String get(@RequestParam("id") String id);
}
