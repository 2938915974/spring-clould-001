package com.mytest.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.entity.LogEntity;
import com.mytest.service.LogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@EnableScheduling
@Aspect
@Configuration
public class aoptest {

    @Resource
    private LogService logService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 指定需要拦截的包、类、⽅
    @Pointcut(value = "execution(* com.mytest.controller..*.*(..))")
    public void pointcut() {}

    // Before 代码⽅法执⾏前的拦截
    @Before(value = "pointcut()")
    public void before() {}

    // Around 代码⽅法执⾏ 前 + 中 + 后的拦截
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable.getMessage());
        }
    }

    // After 代码执⾏后的拦截
    @After(value = "pointcut()")
    public void after() {}

    // 以上均可通过 AfterReturning ⽅法实现
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // 1. 获取 接口 相关信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 1.1. 获取 URI，也就是 /user/find 接口地址
        String requestURI = request.getRequestURI();
        // 1.2. 获取 URL，也就是 http://localhost:33000/user/find 接口地址
        StringBuffer requestURL = request.getRequestURL();
        // 1.3. 获取 请求类型，诸如 GET、POST、PUT、DELETE
        String methodType = request.getMethod();
        // 2. 获取了⽅法类上的相关信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 2.1. 获取⽅法名称
        String name = method.getName();
        // 2.2. 获取参数名称
        String[] parameterNames = signature.getParameterNames();
        // 2.3. 获取参数值
        Object[] parameterValues = joinPoint.getArgs();
        // 2.4. 通过 函数式编程 将数组转换为 字符
        String parameterNamesToString = String.join(",", parameterNames);
        String parameterValuesToString = Arrays.stream(parameterValues).map(Object::toString)
                .collect(Collectors.joining(","));
        // 3. 获取返回值
        String resultData = result.toString();
        String over = resultData.replace((CharSequence) redisTemplate.opsForValue().get("OverloadingChar")," ==== 重载了上一个方法！==== ");
        redisTemplate.opsForValue().set("OverloadingChar", over);
        System.out.println(over);
        // 4. 将 ⽇志 写⼊ Redis 和 MySQL
        LogEntity logentity = new LogEntity();
        logentity.setId(UUID.randomUUID().toString().replace("-", ""));
        logentity.setUri(requestURI);
        logentity.setUrl(requestURL.toString());
        logentity.setMethodType(methodType);
        logentity.setMethodName(name);
        logentity.setParameterName(parameterNamesToString);
        logentity.setParameterValues(parameterValuesToString);
        logentity.setResult(over);

        //5.数据存入redis
        redisTemplate.opsForValue().set(logentity.getId(), logentity);

        //6. 数据存入mysql
        LogEntity redislog =  RedisToMysql(logentity);
        int flag = logService.insert(redislog);

        // 6. 数据打印
        logger.info(
                "\n---> URI: {}, & URL: {}, & MethodType: {}, & MethodName:{}, & ParameterName: {}, & ParameterValue: {}, & result: {}, & flag: {}",
                requestURI, requestURL, methodType, name, parameterNamesToString, parameterValuesToString, result,
                flag > 0 ? "数据库插入成功" : "数据库插⼊失败");
    }

    // AfterTrowing 抛出异常后的拦截
    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        logger.info("---> 执⾏到了 afterThrowing ⽅法");
    }

    // 序列化，向mysql存入的数据不能直接从类中提取，要从redis获取，但是redis的数据无法直接用于mysql，所以要进行序列化
    private LogEntity RedisToMysql(LogEntity entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        String aaa = null;
        LogEntity Redisentity = null;
        try {
            aaa = objectMapper.writeValueAsString(redisTemplate.opsForValue().get(entity.getId()));
            Redisentity = objectMapper.readValue(aaa, LogEntity.class);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return Redisentity;
    }
}
