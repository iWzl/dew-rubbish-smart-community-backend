package com.upuphub.dew.community.operation.api.utils;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public class RedisCacheUtil {

    public static String getRedisCacheKey(Long uin) {
        if (uin == null) {
            return null;
        }
        return String.format("shiro:oss:cache:%s",uin);
    }
}
