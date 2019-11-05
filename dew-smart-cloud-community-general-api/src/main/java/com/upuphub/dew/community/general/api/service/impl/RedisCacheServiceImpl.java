package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.general.api.service.RedisCacheService;
import com.upuphub.dew.community.general.api.utils.RedisCacheUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void putWithExpire(String key, String value, Long expire) {
        redisTemplate.opsForValue().set(key,value,expire, TimeUnit.MINUTES);
    }

    @Override
    public String getEmailVerifyCode(String email){
        return String.valueOf(redisTemplate.opsForValue().get(RedisCacheUtil.getEmailCodeCacheKey(email)));
    }

    @Override
    public void putEmailVerifyCode(String email,String code) {
        putWithExpire(RedisCacheUtil.getEmailCodeCacheKey(email),code.toUpperCase(),10L);
    }
}
