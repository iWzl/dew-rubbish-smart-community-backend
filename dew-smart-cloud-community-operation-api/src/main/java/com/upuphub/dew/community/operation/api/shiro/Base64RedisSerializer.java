package com.upuphub.dew.community.operation.api.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;
import java.util.Base64;


/**
 * 自定义的Redis序列化器
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/14 21:18
 */
@Slf4j
public class Base64RedisSerializer implements RedisSerializer {

    private static boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
    /**
     * 序列化
     * @param object 需要序列化的对象本身
     * @return 序列化后的对象
     * @throws SerializationException 序列化错误异常
     */
    @Override
    public byte[] serialize(Object object) throws SerializationException {
        byte[] result = null;

        if (object == null) {
            return new byte[0];
        }
        try (
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream)
        ){

            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(Base64RedisSerializer.class.getSimpleName() + " requires a Serializable payload " +
                        "but received an object of type [" + object.getClass().getName() + "]");
            }
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result =  byteStream.toByteArray();
            if (result == null) throw new SerializationException("ByteResult is Null");
        } catch (Exception ex) {
            log.error("Failed to serialize",ex);
        }
        assert result != null;
        return Base64.getEncoder().encode(result);
    }

    /**
     * 反序列化
     * @param bytes Redis读取到的信息
     * @return 反序列化后的对象
     * @throws SerializationException 反序列化异常错误
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        Object result = null;

        if (isEmpty(bytes)) {
            return null;
        }
        bytes = Base64.getDecoder().decode(bytes);
        try (

                ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteStream)
        ){
            result = objectInputStream.readObject();
        } catch (Exception e) {
            log.error("Failed to deserialize",e);
        }
        return result;
    }
}
