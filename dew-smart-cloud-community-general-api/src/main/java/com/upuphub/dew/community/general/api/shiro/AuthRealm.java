package com.upuphub.dew.community.general.api.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Objects;


/**
 * 权限认证的逻辑实现
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/14 21:18
 */
public class AuthRealm extends AuthorizingRealm {

    /**用户角色和角色对应权限的添加*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String jwtToken= (String) principalCollection.getPrimaryPrincipal();
        return new SimpleAuthorizationInfo();
    }


    /**用户角色和角色对应权限的添加*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // token验证
        // todo 通过secretKey验证K账户
        Long uin = Long.parseLong(Objects.requireNonNull(JWTUtil.getUin(token)));
        if(!JWTUtil.verify(token,"Password")){
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(uin, token, getName());
    }
}