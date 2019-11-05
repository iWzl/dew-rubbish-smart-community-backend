package com.upuphub.dew.community.general.api.config;

import cc.itsc.rbc.api.shiro.Base64RedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/14 21:18
 */

@Configuration
public class RedisConfiguration {

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
}
