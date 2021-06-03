package com.mytest.service;

import com.mytest.entity.UserInfoEntity;

public interface UserInfoService {

    UserInfoEntity findOnlyUserInfo(String id);

    int insertUserInfo(UserInfoEntity userInfoEntity);

    int updateUserInfo(UserInfoEntity userInfoEntity);

    int deleteUserInfo(String id);
}
