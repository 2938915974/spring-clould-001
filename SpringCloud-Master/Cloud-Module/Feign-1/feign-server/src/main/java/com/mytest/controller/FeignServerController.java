package com.mytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wales
 * <p>
 * 对外提供一个所谓的接口地址
 */
@RestController
@RequestMapping(value = "/feign")
public class FeignServerController {

    private final String str = "通过 Feign 调用了 FeignServerController 中的 get 方法 !";

    /**
     * 在调用 http://localhost:30000/feign/get [GET]
     */
    @GetMapping(value = "/get")
    public String get(@RequestParam(value = "id") String id) {
        System.out.println(id+str);
        return "接收的参数为: " + id + " & " + str;
    }
}
