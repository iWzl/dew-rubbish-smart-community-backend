package com.upuphub.dew.community.operation.api.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 登陆次数限制
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/21 11:44
 */
@Slf4j
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //判断用户账号和密码是否正确
        return super.doCredentialsMatch(token, info);
    }
}
