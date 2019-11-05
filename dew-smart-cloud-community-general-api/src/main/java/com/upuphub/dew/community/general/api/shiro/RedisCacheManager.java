package com.upuphub.dew.community.general.api.shiro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
public class RedisCacheManager implements CacheManager {
    private static final Long DEFAULT_EXPIRE = 18000L;
    private static final String DEFAULT_CACHE_KEY_PREFIX = "shiro:cache:";
    private Map<String,Cache> category2Cache = new HashMap<>();

    private RedisManager redisManager;
    private String keyPrefix = DEFAULT_CACHE_KEY_PREFIX;
    private Long expire = DEFAULT_EXPIRE;


    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        log.debug("Get Cache,name = [{}]",name);
        category2Cache.put(name,new RedisCache<K, V>(redisManager,expire,keyPrefix));
        return category2Cache.get(name);
    }

}
