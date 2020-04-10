package com.upuphub.dew.community.operation.api.shiro;

import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.utils.JsonConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * JWT拦截器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/11 11:44
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含token字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("X-Token");
        return authorization != null;
    }

    /**
     * 执行用户登录
     * 校验请求头中的X-Token是否存在
     *
     * @param request  请求体
     * @param response 返回
     * @return boolean 通过Toke有无判断是否需要拦截用户
     * @author Leo Wang
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("X-Token");
        // 从Token中获取jwtToken的值,转换为JWTToken对象
        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入
        getSubject(request, response).login(token);
        // 没有抛出异常，代表登入成功
        return true;
    }

    /**
     * 这里最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!isLoginAttempt(request, response)) {
            response401(request, response, "账户未登录");
            return false;
        }
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            response401(request, response, e.getMessage());
            return false;
        }
    }

    /**
     * 无需转发，直接返回Response信息
     */
    private void response401(ServletRequest req, ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try(ServletOutputStream out = httpServletResponse.getOutputStream()) {
            String data = JsonConvertUtil.toJson(ServiceResponseMessage.createGeneralMessage(HttpStatus.UNAUTHORIZED.value(), "没有权限", msg));
            out.write(data.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
