package com.lucky.blog.app.service;

import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.app.entity.UserTokenEntity;

import java.util.Set;

public interface ShiroService {

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(long userId);

    UserTokenEntity queryByToken(String token);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    UserEntity queryUser(Long userId);
}
