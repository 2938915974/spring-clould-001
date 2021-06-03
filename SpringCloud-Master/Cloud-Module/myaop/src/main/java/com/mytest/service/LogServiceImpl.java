package com.mytest.service;

import java.util.List;

import javax.annotation.Resource;

import com.mytest.dao.LogMapper;
import com.mytest.entity.LogEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    
    // 添加 Slf4j ⽤作⽇志, 替代 System.out.print() ⽅法
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 注⼊ DAO 层，业务数据来源需要调⽤数据库层⾯的 DAO
    @Resource
    private LogMapper logsMapper;

    /**
     * ⽇志业务 插⼊ 接口层
     *
     * @param entity {@link LogsEntity}
     * @return {@link Integer}
     */
    @Override
    public int insert(LogEntity entity) {
        int flag = 0;
        try {
            flag = logsMapper.logsInsert(entity);
        } catch (Exception e) {
            logger.error("\n---> ⽇志插⼊报错: {}", e.getMessage());
        }
        return flag;
    }

    @Override
    public List<LogEntity> findAll() {
        return logsMapper.findAll();
    }

}
