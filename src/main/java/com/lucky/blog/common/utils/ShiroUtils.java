package com.lucky.blog.common.utils;

import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.common.exception.RestException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserEntity getUserEntity() {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (userEntity == null) {
            throw new RestException("You have no permission.", 401);
        }
        return userEntity;
    }

    public static Long getUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new RestException("Verify code has been expired!");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }
}
