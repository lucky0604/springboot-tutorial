package com.lucky.blog.app.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.UserTokenEntity;
import com.lucky.blog.common.component.R;
import com.lucky.blog.common.utils.PageUtils;

public interface UserTokenService extends IService<UserTokenEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 生成token
     * @param userId
     * @return
     */
    R createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId
     */
    void logout(long userId);

}