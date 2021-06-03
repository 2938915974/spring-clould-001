package com.mytest.entity;

import lombok.Data;

@Data
public class LogEntity {
    /**
     * 主键 ID
     */
    private String id;
    /**
     * 存储 URI
     */
    private String uri;
    /**
     * 存⼊ URL
     */
    private String url;
    /**
     * 请求类型
     */
    private String methodType;
    /**
     * ⽅法名称
     */
    private String methodName;
    /**
     * 传⼊参数名称
     */
    private String parameterName;
    /**
     * 传⼊参数值
     */
    private String parameterValues;
    /**
     * 存⼊返回值
     */
    private String result;
}
