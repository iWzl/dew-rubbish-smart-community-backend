package com.upuphub.dew.community.relation.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

/**
 * OpenFeign默认使用HttpUrlConnection进行网络请求的发送;
 * 相关实现代码在DefaultFeignLoadBalancedConfiguration 的Client.Default。
 * 而其使用的编解码器默认为jackson2，默认配置为HttpMessageConvertersAutoConfiguration。
  * Protocol Buffer的编解码效率要远高于jackson2，在微服务实例频频通信的场景下，
 * 使用Protocol Buffer编解码时会少占用系统资源，并且效率较高。
 * 具体详见这个对比对比各种序列化和反序列化框架的性能的文档，https://github.com/eishay/jvm-serializers/wiki。
 *
 * 在以下配置中ProtobufHttpMessageConverter是HttpMessageConverters的Protobuf的实现类，
 * 负责使用Protocol Buffer进行网络请求和响应的编解码。
 * 而SpringEncoder和ResponseEntityDecoder是OpenFeign中的编解码器实现类。
 *
 * 服务端对于Protocol Buffer的集成。我们也需要使用自定义配置类将ProtobufHttpMessageConverter设置为系统默认的编解码器
 * ProtobufHttpMessageConverter会自动将其转换为Protocol Buffer的数据格式进行传输。
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */

@Configuration
public class ProtoBufConfiguration {
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}

