package com.lucky.blog.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.lucky.blog.common.constant.CaptchaConstants;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("captchaService")
public class CaptchaServiceImpl {
    @Resource
    RedisTemplate<String, String> kvLockTemplate;

    /**
     * 获取验证码
     */
    public String getCaptcha(String key) {
        return kvLockTemplate.opsForValue().get(CaptchaConstants.CAPTCHA_PREFIX + key);
    }

    /**
     * 设置验证码
     * @param key
     * @param value
     */
    public void setCaptcha(String key, String value) {
        kvLockTemplate.opsForValue().set(CaptchaConstants.CAPTCHA_PREFIX + key, value, 5, TimeUnit.MINUTES);
    }

    /**
     * 清除验证码
     * @param key
     */
    public void delCaptcha(String key) {
        List<String> keys = new ArrayList<>();
        keys.add(CaptchaConstants.CAPTCHA_PREFIX + key);
        kvLockTemplate.delete(keys);
    }
}