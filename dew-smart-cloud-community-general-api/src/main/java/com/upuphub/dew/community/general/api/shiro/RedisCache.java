package com.upuphub.dew.community.general.api.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;
import java.util.Set;

@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {
    private RedisManager redisManager;
    private long expireTime = 0L;
    private String keyPrefix = "";

    public RedisCache(RedisManager redisManager, long expireTime, String keyPrefix) {
        if (redisManager == null) {
            throw new IllegalArgumentException("redisManager cannot be null.");
        }
        this.redisManager = redisManager;
        if (expireTime != -1) {
            this.expireTime = expireTime;
        }
        if (keyPrefix != null && !"".equals(keyPrefix)) {
            this.keyPrefix = keyPrefix;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) throws CacheException {
        log.debug("Cache Get [{}]", key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            return (V) redisManager.get(redisCacheKey);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.debug("Cache Put key [{}:{}]",key,value);
        if (key == null) {
            log.warn("Saving a null key is meaningless, return value directly without call Redis.");
            return value;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            redisManager.set(redisCacheKey, value, expireTime);
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) throws CacheException {
        log.debug("Remove key [{}]",key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getRedisCacheKey(key);
            V value = (V) redisManager.get(redisCacheKey);
            redisManager.del(redisCacheKey);
            return value;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void clear() throws CacheException {
        log.debug("Clear Cache");
        Set<String> keys = null;
        try {
            keys = redisManager.scan(this.keyPrefix + "*");
        } catch (Exception e) {
            log.error("Get Keys Error", e);
        }
        if (keys == null || keys.size() == 0) {
            return;
        }
        for (String key: keys) {
            redisManager.del(key);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private String getRedisCacheKey(K key) {
        if (key == null) {
            return null;
        }
        return this.keyPrefix + getStringRedisKey(key);
    }

    private String getStringRedisKey(K key) {
        String redisKey;
        if (key instanceof PrincipalCollection) {
            redisKey = ((PrincipalCollection) key).getPrimaryPrincipal().toString();
            return redisKey;
        }
        return key.toString();
    }
}
