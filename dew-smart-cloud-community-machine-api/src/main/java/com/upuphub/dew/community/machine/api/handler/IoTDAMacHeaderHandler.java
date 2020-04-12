package com.upuphub.dew.community.machine.api.handler;

import com.upuphub.dew.community.machine.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.machine.api.utils.JsonConvertUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 13:30
 */
public class IoTDAMacHeaderHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!request.getRequestURI().startsWith("/api")) return true;
        String macAddress = request.getHeader("X-DEW-IoTDA-MAC");
        if(null == macAddress || "".equals(macAddress) || !"Test".equals(macAddress)){
            response.setStatus(401);
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            response.setContentType("application/json; charset=utf-8");
            try(ServletOutputStream out = response.getOutputStream()) {
                String data = JsonConvertUtil.toJson(ServiceResponseMessage.createGeneralMessage(HttpStatus.UNAUTHORIZED.value(), "没有权限", "设备未注册或不合法"));
                out.write(data.getBytes(StandardCharsets.UTF_8));
                out.flush();
            } catch (IOException ignore) {}
            return false;
        }
        return true;
    }
}
