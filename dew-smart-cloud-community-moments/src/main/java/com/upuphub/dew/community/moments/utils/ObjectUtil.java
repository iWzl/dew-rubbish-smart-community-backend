package com.upuphub.dew.community.moments.utils;

import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象相关工具
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/18 20:58
 */

public class ObjectUtil {
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isEmpty(Map map){
        return map == null || map.size() == 0;
    }

    public static boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Long object){
        return object == null || object == 0L;
    }

    public static boolean isEmpty(Object object){
        return object == null;
    }

    public static Map<String,String> map2StringMap(Map<String,Object> objectMap){
        Map<String,String> map = new HashMap<>();
        objectMap.forEach((key,value)->{
            map.put(key,String.valueOf(value));
        });
        return map;
    }

    public static Map<String,Object> stringMap2Map(Map<String,String> objectMap){
        Map<String,Object> map = new HashMap<>();
        objectMap.forEach(map::put);
        return map;
    }


    public static Map<String,Object> beanToMap(Object bean, Set<String> ignoreSet){
        Map<String,Object> map = new HashMap<>();
        try {
            //获取指定类的BeanInfo 对象
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            //获取所有的属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd:pds){
                String key = pd.getName();
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(bean);
                if(!ignoreSet.contains(key) && null != value){
                    map.put(key, value);
                }
            }
            return map;
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            return Collections.emptyMap();
        }
    }
}
