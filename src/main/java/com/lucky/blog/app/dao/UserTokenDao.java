package com.lucky.blog.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lucky.blog.app.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenEntity> {
    UserTokenEntity queryByToken(String token);
}
