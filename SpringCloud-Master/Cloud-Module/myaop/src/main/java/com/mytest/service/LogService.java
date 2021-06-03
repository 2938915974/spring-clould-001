package com.mytest.service;

import java.util.List;

import com.mytest.entity.LogEntity;

public interface LogService {
    /**
     * ⽇志业务 插⼊ 接口层
     *
     * @param entity {@link LogsEntity}
     * @return {@link Integer}
     */
    int insert(LogEntity entity);

    /**
     * ⽇志业务 查询 接口层
     *
     * @return {@link LogsEntity}
     */
    List<LogEntity> findAll();
}
