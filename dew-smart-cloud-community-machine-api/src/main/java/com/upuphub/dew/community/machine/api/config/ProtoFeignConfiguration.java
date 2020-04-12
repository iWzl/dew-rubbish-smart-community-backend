package com.upuphub.dew.community.machine.api.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
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
 * 在使用时，需要声明添加配置
 *   添加注解配置@FeignClient(name = "SERVICE-NAME", configuration = ProtoFeignConfiguration.class)
 *   请求Mapping  @RequestMapping(value = "/PATH"
 *             consumes = "application/x-protobuf", produces = "application/x-protobuf")
 */

@Configuration
public class ProtoFeignConfiguration {
    private final ObjectFactory<HttpMessageConverters> messageConverterObjectFactory;

    public ProtoFeignConfiguration(ObjectFactory<HttpMessageConverters> messageConverterObjectFactory) {
        this.messageConverterObjectFactory = messageConverterObjectFactory;
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public Encoder springEncoder() {
        return new SpringEncoder(this.messageConverterObjectFactory);
    }

    @Bean
    public Decoder springDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverterObjectFactory));
    }
}
