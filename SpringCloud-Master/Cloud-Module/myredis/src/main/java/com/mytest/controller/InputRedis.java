package com.mytest.controller;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.entity.UserInfoEntity;
import com.mytest.service.InfoSevice;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/KKK")
public class InputRedis {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private InfoSevice infoSevice;

    // 查询
    @GetMapping("/test")
    public Object rushToA(@RequestParam(value = "id") String id) {
        Object str = redisTemplate.opsForValue().get(id);
        if (str != null && str != "") {
            System.out.println("redis有数据");
            return redisTemplate.opsForValue().get(id);
        }
        System.out.println("redis无数据,前往mysql查询");
        // redis无数据，跳转到mysql查询。
        
         Object object = infoSevice.rushA(id);
         UserInfoEntity Redisentity = MysqlToRedis(object);
        redisTemplate.opsForValue().set(id,Redisentity);
        return "前往了mysql查询数据，结果：" + Redisentity + ", redis返回数据";
    }

    // 修改
    @PostMapping("/test")
    public Object rushToB(@RequestBody UserInfoEntity entity) {
        Object AAA = redisTemplate.opsForValue().get(entity.getId());
        String A = "redis修改成功";
        int a = 100;
        if (AAA == null && AAA != "") {
            A = "redis没有数据，查看mysql";
            UserInfoEntity Redisentity = RedisToMysql(entity);
            a = infoSevice.rushB(Redisentity);
            if(a != 0){ return "mysql 与 redis 皆无数据，请检查 ID 是否正确"; }
        }
        redisTemplate.opsForValue().set(entity.getId(), entity);
        return "redis修改结果: " + A + " , 数据库修改结果: " + a;
    }

    // 删除
    @DeleteMapping("/test")
    public Object rushToC(@RequestParam(value = "id") String id) {
        Boolean a = redisTemplate.delete(id);
        // 删除mysql中的数据并获取返回值
        int aa = infoSevice.rushC(id);
        return "redis删除结果: " + a + ", 数据库删除结果: " + aa;
    }

    // 增加
    @PutMapping("/test")
    public Object rushToD(@RequestBody UserInfoEntity entity) {
        redisTemplate.opsForValue().set(entity.getId(), entity);
        UserInfoEntity Redisentity = RedisToMysql(entity);
        int a = infoSevice.rushD(Redisentity);

        return "redis添加结果: " + redisTemplate.opsForValue().get(entity.getId()) + ", 数据库添加结果: " + a;
    }

    // 序列化，向mysql存入的数据不能直接从类中提取，要从redis获取，但是redis的数据无法直接用于mysql，所以要进行序列化
    private UserInfoEntity RedisToMysql(UserInfoEntity entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        String aaa = null;
        UserInfoEntity Redisentity = null;
        try {
            aaa = objectMapper.writeValueAsString(redisTemplate.opsForValue().get(entity.getId()));
            Redisentity = objectMapper.readValue(aaa, UserInfoEntity.class);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return Redisentity;
    }

        // 序列化，mysql的数据以Object封装，然后数据全部序列化成String，再转换为 UserInfoEntity类 类型数据
        //不能直接序列化成 类 类型，规避可能出现的类型不一样的错误。
        private UserInfoEntity MysqlToRedis(Object object) {
            ObjectMapper objectMapper = new ObjectMapper();
            String aaa = null;
            UserInfoEntity Redisentity = null;
            try {
                aaa = objectMapper.writeValueAsString(object);
                Redisentity = objectMapper.readValue(aaa, UserInfoEntity.class);
            } catch (JsonProcessingException e) {
                e.getMessage();
            }
            return Redisentity;
        }

}
