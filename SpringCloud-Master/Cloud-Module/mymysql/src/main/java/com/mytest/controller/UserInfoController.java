package com.mytest.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.mytest.entity.UserInfoEntity;
import com.mytest.service.UserInfoService;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate<String, Object> redis;

    @GetMapping("/test000")
    public String testity(){
        System.out.println("----> 进入 TEST 方法 ------");
        return "连接测试完成";
    }

    @GetMapping ("/test")
    public Object findOnlyUserInfo(@RequestParam(value = "id") String id){
        return userInfoService.findOnlyUserInfo(id);
    }
    
    @PostMapping("/test")
    public Object updateUserInfo(@RequestBody UserInfoEntity entity){
        return userInfoService.updateUserInfo(entity);
    }

    @PutMapping("/test")
    public Object insertUserInfo(@RequestBody UserInfoEntity entity){
        return userInfoService.insertUserInfo(entity);
    }

    @DeleteMapping("/test")
    public Object deleteUserInfo(@RequestParam(value = "id") String id){
        return userInfoService.deleteUserInfo(id);
    }

}
