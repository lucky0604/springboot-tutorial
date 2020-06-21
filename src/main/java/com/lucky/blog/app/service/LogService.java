package com.lucky.blog.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.LogEntity;
import com.lucky.blog.common.utils.PageUtils;

import java.util.Map;

public interface LogService extends IService<LogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
