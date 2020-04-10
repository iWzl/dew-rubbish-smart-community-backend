package com.upuphub.dew.community.operation.api.config;

import com.upuphub.dew.community.connection.common.JsonHelper;
import com.upuphub.dew.community.operation.api.shiro.Base64RedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/14 21:18
 */

@Slf4j
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Base64RedisSerializer base64RedisSerializer = new Base64RedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用自定义的Base64方式
        template.setValueSerializer(base64RedisSerializer);
        //  value序列化方式采用自定义的Base64方式
        template.setHashValueSerializer(base64RedisSerializer);
        // 简单储存,不需要开启事务(Redis事务开关) 需要结合事务注解使用,不然会出现连接未释放的情况出现
        template.setEnableTransactionSupport(false);
        template.afterPropertiesSet();
        return template;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params)->{
            //格式化缓存key字符串
            StringBuilder cacheKeyBuilder = new StringBuilder();
            //追加类名
            cacheKeyBuilder.append(target.getClass().getName());
            cacheKeyBuilder.append(".");
            //追加方法名
            cacheKeyBuilder.append( method.getName());
            cacheKeyBuilder.append(".");
            //遍历参数并且追加
            for (Object obj : params) {
                cacheKeyBuilder.append(JsonHelper.toJson(obj));
                cacheKeyBuilder.append(".");
            }
            String cacheKey = cacheKeyBuilder.toString();
            log.debug("call Cache Key [{}] ....",cacheKey);
            return cacheKey;
        };
    }

    /**
     * 采用RedisCacheManager作为缓存管理器
     * @param redisConnectionFactory Redis缓存工厂
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(45));
        return  RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
