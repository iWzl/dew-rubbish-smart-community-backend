package com.upuphub.dew.community.connection.common;



import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.upuphub.dew.community.connection.protobuf.account.GeneralProfile;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/29 00:21
 */
public class MessageUtil {


    /**
     * 通过Map构建Protobuf通用GeneralProfile的Message对象
     *
     * @param params 需要转换的params参数
     * @return 成功构建的 GeneralProfile Message对象
     */
    public static GeneralProfile buildMessageByMap(Long uin, Map<String, String> params) {
        GeneralProfile.Builder builder = GeneralProfile.newBuilder();
        builder.setUin(uin);
        builder.putAllProfile(params);
        return builder.build();
    }


    /**
     * 通过Map构建Protobuf的Message对象
     *
     * @param descriptor Protobuf的描述对象,主要用于获取Message中的field属性
     * @param builder    Protobuf的构建者对象
     * @param params     需要转换的params参数
     * @return 成功构建的Params对象参数
     */
    public static Message buildMessageByMap(Descriptors.Descriptor descriptor, GeneratedMessageV3.Builder builder, Map params) {
        for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
            builder.setField(field, buildValue(field, params.get(field.getName())));
        }
        return builder.build();
    }


    /**
     * Protobuf Message对象转Map
     *
     * @param message Protobuf对象的Message实体
     * @return 转换就的Map对象
     */
    public static Map<String, Object> buildMessageToMap(Message message) {
        Map<String, Object> messageMap = new HashMap<>(message.getAllFields().size());
        for (Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
            messageMap.put(field.getName(), message.getField(field));
        }
        return messageMap;
    }

    /**
     * 获取构成Protobuf Message需要的参数
     *
     * @param descriptor Protobuf的描述对象,主要用于获取Message中的field属性
     * @return 构成Message对象需要的列参数
     */
    public static List<String> getProtobufKeys(Descriptors.Descriptor descriptor) {
        if (descriptor != null) {
            List<String> keys = new LinkedList<>();
            for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
                keys.add(field.getName());
            }
            return keys;
        }
        return Collections.emptyList();
    }

    /**
     * // todo 缺少实现的处理
     * Message对象转换为普通对象的实现
     *
     * @param message      原始Message对象
     * @param clazz        普通对象的类
     * @param <CommonBean> 普通对象的实例
     * @return 转换后的普通对象的实例
     */
    public static <CommonBean> CommonBean messageToCommonPojo(Message message, Class<CommonBean> clazz) {
        try {
            Map<String, Object> messageMap = new HashMap<>(message.getAllFields().size());
            for (Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
                messageMap.put(field.getName(), buildValue(field, message.getField(field)));
            }
            // todo 嵌套循环的处理
            CommonBean commonBean = clazz.newInstance();
            for (Field declaredField : clazz.getDeclaredFields()) {
                declaredField.setAccessible(true);
                Object value = messageMap.get(declaredField.getName());
                if (value != null) {
                    declaredField.set(commonBean, value);
                }
            }
            return commonBean;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildBase64MqttMessage(Integer tag,Integer type, Message message) {
        return Base64
                .getEncoder()
                .encodeToString(
                        MqttMessage
                                .newBuilder()
                                .setPayload(message.toByteString())
                                .setType(type)
                                .setTag(tag)
                                .build()
                                .toByteArray());
    }


    /**
     * 元素参数类型转换
     *
     * @param field Protobuf的描述对象,用于获取Message中的field参数类型
     * @param value 需要写入的参数类型
     * @return 转换后的指定参数类型
     */
    private static Object buildValue(Descriptors.FieldDescriptor field, Object value) {
        switch (field.getJavaType()) {
            case STRING:
                return String.valueOf(value);
            case FLOAT:
                return Float.valueOf(value.toString());
            case INT:
                if (value != null && !"".equals(value.toString())) {
                    return Integer.valueOf(value.toString());
                } else {
                    return 0;
                }
            case LONG:
                if (value != null && !"".equals(value.toString())) {
                    return Long.valueOf(value.toString());
                } else {
                    return 0L;
                }
            case DOUBLE:
                return Double.valueOf(value.toString());
            case BOOLEAN:
                return Boolean.parseBoolean(value.toString());
            default:
                return value;
        }
    }


    /**
     * 元素参数类型转换
     *
     * @param field Protobuf的描述对象,用于获取Message中的field参数类型
     * @param value 需要写入的参数类型
     * @return 转换后的指定参数类型
     */
    private static Object buildValue(Field field, Object value) {
        switch (field.getType().getTypeName()) {
            case "java.lang.Integer":
                if (value != null && !"".equals(value.toString())) {
                    return Integer.valueOf(value.toString());
                } else {
                    return 0;
                }
            case "java.lang.Long":
                if (value != null && !"".equals(value.toString())) {
                    return Long.valueOf(value.toString());
                } else {
                    return 0L;
                }
            default:
                return value;
        }
    }

    public static List<String> getProtobufKeys(Class clazz) {
        List<String> profileKeys = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if ("openId".equals(field.getName())) {
                continue;
            }
            profileKeys.add(field.getName());
        }
        return profileKeys;
    }

    public static <CommonBean> CommonBean buildCommonBeanByMap(Map<String, String> profileMap, Class<CommonBean> clazz, Set ignore) {
        try {
            CommonBean commonBean = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (ignore.contains(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                field.set(commonBean, buildValue(field, profileMap.get(field.getName())));
            }
            return commonBean;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
