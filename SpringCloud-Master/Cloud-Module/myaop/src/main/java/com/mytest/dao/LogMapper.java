package com.mytest.dao;

import java.util.List;

import com.mytest.entity.LogEntity;

import org.springframework.data.repository.query.Param;


public interface LogMapper {
    /**
    * 根据 ID 查询对应⽇志信息
    *
    * @param id ID
    * @return {@link LogEntity}
    */
    LogEntity findById(@Param("id") String id);
    /**
    * 查询所有⽇志信息
    *
* @return {@link LogsEntity}
*/
List<LogEntity> findAll();
/**
* 插⼊ ⽇志信息
*
* @param logsEntity {@link LogEntity}
* @return {@link Integer}
*/
int logsInsert(LogEntity logsEntity);
}
