package com.mytest.service;

import com.mytest.entity.UserInfoEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "springcuid", path = "/")
public interface InfoSevice {

    @GetMapping("/user/test")
    public  Object rushA(@RequestParam(value = "id") String id);

    @PostMapping("/user/test")
    public int rushB(@RequestBody UserInfoEntity entity);

    @DeleteMapping("/user/test")
    public int rushC(@RequestParam(value = "id") String id);

    @PutMapping("user/test")
    public int rushD(@RequestBody UserInfoEntity entity);

}