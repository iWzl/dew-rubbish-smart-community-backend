package com.upuphub.dew.community.general.api.utils;

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
        return String.format("shiro:cache:%s",uin);
    }

    public static String getEmailCodeCacheKey(String email){
        return String.format("temp:email:%s:%s",HttpUtil.getUserUin(),email);
    }

}
