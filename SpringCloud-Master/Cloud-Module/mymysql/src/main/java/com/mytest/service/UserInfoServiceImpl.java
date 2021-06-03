package com.mytest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.mytest.dao.UserInfoMapper;
import com.mytest.entity.UserInfoEntity;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoEntity findOnlyUserInfo(String id) {
        return userInfoMapper.findOnlyUserInfo(id);
    }

    @Override
    public int insertUserInfo(UserInfoEntity userInfoEntity) {
        return userInfoMapper.insertUserInfo(userInfoEntity);
    }

    @Override
    public int updateUserInfo(UserInfoEntity userInfoEntity) {
        return userInfoMapper.updateUserInfo(userInfoEntity);
    }

    @Override
    public int deleteUserInfo(String id) {
        return userInfoMapper.deleteUserInfo(id);
    }
}
