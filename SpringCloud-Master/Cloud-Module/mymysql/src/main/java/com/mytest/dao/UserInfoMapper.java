package com.mytest.dao;

import com.mytest.entity.UserInfoEntity;

/**
 * @author May
 * <p>
 * 操作数据库接口层
 */
public interface UserInfoMapper {

    /**
     * 查询单个用户数据，根据全局唯一ID查询
     *
     * @param id 用户id
     * @return 用户实体 entity
     */
    UserInfoEntity findOnlyUserInfo(String id);

    /**
     * 插入用户信息， entity与数据库字段对应，插入的其实就是Entity
     *
     * @param userInfoEntity 用户信息表字段
     * @return 返回插入成功的记录行数，因为只是单个插入，所以如果插入成功，返回信息是数字类型的 1
     */
    int insertUserInfo(UserInfoEntity userInfoEntity);

    /**
     * 更新用户信息，与插入同理，但是更新的时候，MySQL会根据update语句，去查找你的主键ID，如果存在则更新
     *
     * @param userInfoEntity 用户信息表字段
     * @return 单个更新，返回的是更新成功的表信息，这里如果更新成功则值是 1
     */
    int updateUserInfo(UserInfoEntity userInfoEntity);

    /**
     * 删除用户信息，直接根据全局唯一ID删除就行了，如果不知道用户信息对应的ID是什么，可以单独写个根据用户名或者其他信息查询到这条信息表中的主键ID，然后再调用删除方法
     *
     * @param id 主键ID
     * @return 单个删除，删除成功返回 1
     */
    int deleteUserInfo(String id);
}
