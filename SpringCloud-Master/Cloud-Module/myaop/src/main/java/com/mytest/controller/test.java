package com.mytest.controller;

import javax.annotation.Resource;

import com.mytest.service.LogService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logs")
public class test {

    @Resource
    private LogService logsService;
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 任意创建携带参数的接口⽅法即可 从 Redis 返回
     *
     * @param id   任意参数其⼀
     * @param name 任意参数其⼆
     * @return {@link Object}
     */
    @GetMapping("/over")
    public Object isover(){
        return redisTemplate.opsForValue().get("OverloadingChar");
    }

    @GetMapping(value = "/test1")
    public Object testByRedis(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name) {
        if (redisTemplate.opsForValue().get(id) != null) {
            return redisTemplate.opsForValue().get(id);
        }
        return logsService.findAll();
    }

    /**
     * 任意创建携带参数的接口⽅法即可 从 数据库 返回
     *
     * @param id   任意参数其⼀
     * @param name 任意参数其⼆
     * @return {@link Object}
     */
    @GetMapping(value = "/test2")
    public Object testByMySQL(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name) {
        return logsService.findAll();
    }
}