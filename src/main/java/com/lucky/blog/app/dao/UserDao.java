package com.lucky.blog.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * 根据用户信息，查询系统用户
     * @param account
     * @return
     */
    UserEntity queryByUserAccount(String account);
}
