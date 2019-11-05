package com.upuphub.dew.community.general.api.service;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:54
 */
public interface RedisCacheService {

    /**
     * 放入Redis缓存
     * @param key 缓存的Key
     * @param value 缓存的Value
     * @param expire 缓存的过期时间
     */
    void putWithExpire(String key, String value, Long expire);

    /**
     * 获取邮箱验证码
     * @return 当前账户的邮箱验证码
     * @param email 邮箱
     */
    String getEmailVerifyCode(String email);

    /**
     * 缓存用户的邮件验证码
     * @param code 邮件验证码
     * @param email 邮箱
     */
    void putEmailVerifyCode(String email, String code);

}
