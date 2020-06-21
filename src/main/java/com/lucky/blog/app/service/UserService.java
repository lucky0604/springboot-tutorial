package com.lucky.blog.app.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.common.utils.PageUtils;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名，查询系统用户
     * @param username
     * @return
     */
    UserEntity queryByUserAccount(String username);

    /**
     * 保存用户
     * @param user
     */
    void save(UserEntity user);

    /**
     * 更新用户
     * @param user
     */
    void update(UserEntity user);

    /**
     * 删除用户
     * @param userIds
     */
    void deleteBatch(Long[] userIds);

    /**
     * 更新密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}