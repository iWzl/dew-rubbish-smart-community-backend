package com.upuphub.dew.community.operation.api.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户退出拦截器
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/21 11:44
 */
public class ShiroLogoutFilter extends LogoutFilter {

    /**
     * 自定义登出,登出之后,清理当前用户redis部分缓存信息
     * @param request request
     * @param response response
     * @return 是否继续执行
     * @throws Exception 错误异常
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //登出操作 清除缓存,由于使用JWT且没有使用Session,需要手动清理缓存
        Subject subject = getSubject(request,response);
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm authRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("X-Token");
        // 从Token中获取jwtToken的值,转换为JWTToken对象
        authRealm.getAuthenticationCache().remove(JWTUtil.getUin(authorization));
        return false;
    }
}
