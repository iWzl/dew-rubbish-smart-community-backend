package com.upuphub.dew.community.connection.common;



import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.upuphub.dew.community.connection.annotation.ProtobufField;
import com.upuphub.dew.community.connection.protobuf.account.GeneralProfile;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage;
import net.sf.cglib.beans.BeanMap;

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
     * 通过Map构建Protobuf的Message对象
     *
     * @param descriptor Protobuf的描述对象,主要用于获取Message中的field属性
     * @param builder    Protobuf的构建者对象
     * @param object      需要转换的Class参数
     * @return 成功构建的Params对象参数
     */
    public static Message buildMessageByBean(Descriptors.Descriptor descriptor, GeneratedMessageV3.Builder builder, Object object) {
        Map<String,Object>params = beanToMap(object);
        for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
            Object obj =  params.get(field.getName());
            if(!(obj instanceof Iterable)){
                builder.setField(field, buildValue(field, obj));
            }else if(obj instanceof List){
                for (int i = 0; i < ((List) obj).size(); i++) {
                    builder.setRepeatedField(field,i,((List) obj).get(i));
                }
            }
        }
        return builder.build();
    }

    /**
     * 将对象装换为map
     * @param bean 需要转换的类
     * @return 转换后的Map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
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
                ProtobufField protobufField = declaredField.getDeclaredAnnotation(ProtobufField.class);
                if(null != protobufField &&protobufField.ignore()){
                    continue;
                }
                String protobufFieldName = null;
                if(null != protobufField && !"".equals(protobufField.value())){
                   protobufFieldName = protobufField.value();
                }
                if(null == protobufFieldName || "".equals(protobufFieldName)){
                    protobufFieldName = declaredField.getName();
                }
                declaredField.setAccessible(true);
                Object value = messageMap.get(protobufFieldName);
                if (value != null) {
                    declaredField.set(commonBean, buildValue(declaredField,value));
                }
            }
            return commonBean;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildBase64MqttMessage(Integer tag, Message message) {
        return Base64
                .getEncoder()
                .encodeToString(
                        MqttMessage
                                .newBuilder()
                                .setPayload(message.toByteString())
                                .setTag(tag)
                                .build()
                                .toByteArray());
    }


    public static String buildBase64Message(Message message) {
        return Base64
                .getEncoder()
                .encodeToString(
                      message.toByteArray());
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

    public static <Bean> Bean buildCommonBeanByMap(Map<String, String> profileMap, Class<Bean> clazz, Set ignore) {
        try {
            Bean commonBean = clazz.newInstance();
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
