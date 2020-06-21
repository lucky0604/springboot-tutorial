package com.lucky.blog.shiro;

import com.lucky.blog.app.entity.UserEntity;
import com.lucky.blog.app.entity.UserTokenEntity;
import com.lucky.blog.app.service.ShiroService;
import com.lucky.blog.common.constant.Base;
import com.lucky.blog.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<String>();

        // 简单处理 只有admin一个角色
        if (user.getAdmin().intValue() == 1) {
            roles.add(Base.ROLE_ADMIN);
        }
        info.setRoles(roles);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        // 根据accessToken, 查询用户信息
        UserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        // token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token expired, please login again.");
        }

        // 查询用户信息
        UserEntity userEntity = shiroService.queryUser(tokenEntity.getUserId());
        // 账号锁定
        if (Constant.UserStatus.PAUSE.getValue().equals(userEntity.getStatus())) {
            throw new LockedAccountException("Account has been locked, please contact the admin.");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity, accessToken, getName());
        log.info("[Oauth-Token login]token = {}, account={}", accessToken, userEntity.getAccount());

        return info;
    }
}
