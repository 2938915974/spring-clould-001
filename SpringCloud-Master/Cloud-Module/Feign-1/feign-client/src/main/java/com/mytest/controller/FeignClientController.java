package com.mytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import com.mytest.service.FeignClientService;
import com.mytest.service.inforedis;

/**
 * @author linzhengyu
 * <p>
 * Feign Client -> 用于调用 其他项目/模块提供的接口
 * </p>
 */
@RestController
@RequestMapping(value = "/client")
public class FeignClientController {

    @Resource
    private FeignClientService feignClientService;

    @Resource
    private inforedis inforedis;

    @GetMapping(value = "/test")
    public Object test(@RequestParam(value = "name") String name) {
        String str = feignClientService.get(name);
        return str;
    }

    @GetMapping("/test01")
    public  Object name() {
        return inforedis.a();
    }

}
