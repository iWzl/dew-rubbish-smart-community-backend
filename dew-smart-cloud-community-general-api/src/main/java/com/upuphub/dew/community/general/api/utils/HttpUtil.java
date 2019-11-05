package com.upuphub.dew.community.general.api.utils;

import cc.itsc.rbc.api.shiro.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
@Slf4j
public class HttpUtil {


    /**
     * 获取Request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())
        ).getRequest();
    }


    /**
     * 获取用户Token
     * @return 用户Token
     */
    public static String getUserToken(){
        return getHttpServletRequest().getHeader("X-Token");
    }


    /**
     * 获取用户Uin
     * @return 用户Uin
     */
    public static Long getUserUin(){
        String uinString =  JWTUtil.getUin(getUserToken());
        return Long.valueOf((uinString == null || "".equals(uinString)) ?"":uinString);
    }

    /**
     * 获取Ip地址，多级反向代理
     * @return 用户IP地址
     */
    public static String getIpAddr(){
        HttpServletRequest request = getHttpServletRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取IP失败",e);
                }
                assert inet != null;
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if(ipAddress!=null && ipAddress.length()>15){
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
