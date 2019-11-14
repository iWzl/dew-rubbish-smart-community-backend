package com.upuphub.dew.community.moments.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
