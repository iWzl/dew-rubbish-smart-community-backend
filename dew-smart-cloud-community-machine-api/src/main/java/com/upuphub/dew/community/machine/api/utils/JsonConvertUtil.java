package com.upuphub.dew.community.machine.api.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public class JsonConvertUtil {
    private static ObjectMapper newParserMapper(boolean underScore){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true)                           //允许字符串中存在注释
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)               //允许无引号的键
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)                      //允许单引号
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)             //允许存在换行符等特殊字符
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)          //允许字符串中出现Bean中没有的属性
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))                   //设置日期格式
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)         //遇到空值时不抛出异常
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);        //如果列表中只有一条记录，依然处理成列表
        if(underScore){
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        }
        return mapper;
    }

    private static ObjectMapper newGenerateMapper(boolean underScore){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE) //不准获取其任何字段
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)    //不准获取isXxx方法
//                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)       //不准获取getter方法
                .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.NONE)      //不准获取构造函数和static方法
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)                      //不准获取空值
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));                  //设置日期格式
        if(underScore){
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        }

        return mapper;
    }


    public static <T> T valueOf(String json, Class<T> c){
        return valueOf(json, c, false);
    }
    /**
     * 将json字符串转换成对象
     * @param json json字符串
     * @param c 对象的类型
     * @return
     */
    public static <T> T valueOf(String json, Class<T> c, boolean underScore){
        T result;
        try {
            ObjectMapper parserMapper = newParserMapper(underScore);
            result = valueOf(parserMapper.getFactory().createParser(json), c);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private static <T> T valueOf(JsonParser parser, Class<T> c) throws IOException{
        T result = parser.readValueAs(c);
        parser.close();
        return result;
    }

    /**
     * 将json字符串转换成对象
     * @param reader 文件读取器
     * @param c 对象的类型
     * @return
     */
    public static <T> T valueOf(Reader reader, Class<T> c) throws IOException {
        return valueOf(reader, c, false);
    }

    public static <T> List<T> valueOfList(Reader reader, Class<T> c) throws IOException {
        return valueOfList(reader, c, false);
    }

    public static <T> T valueOf(Reader reader, Class<T> c, boolean underScore) throws IOException {
        ObjectMapper parserMapper = newParserMapper(underScore);
        return valueOf(parserMapper.getFactory().createParser(reader), c);
    }

    public static <T> List<T> valueOfList(Reader reader, Class<T> c, boolean underScore) throws IOException {
        List<T> result;
        ObjectMapper parserMapper = newParserMapper(underScore);
        JavaType type = parserMapper.getTypeFactory().constructParametricType(ArrayList.class, c);
        result = parserMapper.readValue(reader, type);
        reader.close();
        return result;
    }

    /**
     * 将json字符串转换成指定类型的列表
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> List<T> valueOfList(String json, Class<T> c) {
        return valueOfList(json, c, false);
    }


    public static <T> List<T> valueOfList(String json, Class<T> c, boolean underScore) {
        try {
            ObjectMapper parserMapper = newParserMapper(underScore);
            JavaType type = parserMapper.getTypeFactory().constructParametricType(ArrayList.class, c);
            return parserMapper.readValue(json, type);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }



    /**
     * 将json字符串转换成指定类型的Map
     * @param json 字符串
     * @param keyClass key的class
     * @param valueClass value的class
     * @param <T> key泛型
     * @param <V> value泛型
     * @return
     */
    public static <T, V> Map<T, V> valueOfMap(String json, Class<T> keyClass, Class<V> valueClass){
        return valueOfMap(json, keyClass, valueClass, false);
    }


    public static <T, V> Map<T, V> valueOfMap(String json, Class<T> keyClass, Class<V> valueClass, boolean underScore){
        try {
            ObjectMapper parserMapper = newParserMapper(underScore);
            JavaType type = parserMapper.getTypeFactory().constructParametricType(LinkedHashMap.class, keyClass, valueClass);
            return parserMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> valueOfMap(Reader reader){
        return valueOfMap(reader, String.class, Object.class);
    }

    public static Map<String, Object> valueOfMap(String json){
        return valueOfMap(json, String.class, Object.class);
    }

    /**
     * 将json流转换成指定类型的Map
     * @param reader json流
     * @param keyClass key的class
     * @param valueClass value的class
     * @param <T> key泛型
     * @param <V> value泛型
     * @return
     */
    public static <T, V> Map<T, V> valueOfMap(Reader reader, Class<T> keyClass, Class<V> valueClass) {
        try {
            ObjectMapper parserMapper = newParserMapper(false);
            JavaType type = parserMapper.getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
            return parserMapper.readValue(reader, type);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 将java对象转换成json字符串
     * @param data 实际数据
     * @param <T> 泛型
     * @return
     */
    public static <T> String toJson(T data, boolean underScore){

        try {
            if(data == null) {
                return "";
            }
            ObjectMapper generatorMapper = newGenerateMapper(underScore);
            return generatorMapper.writeValueAsString(data);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> String toJson(T data){
        return toJson(data, false);
    }

    /**
     * 将对象的所有字段都输出
     * 该方法只能使用于单元测试
     * @param data
     * @param <T>
     * @return
     */
    public static <T> String allToJson(T data, boolean underScore){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)           //获取字段
                    .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)    //不准获取isXxx方法
//                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)       //不准获取getter方法
                    .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.NONE)      //不准获取构造函数和static方法
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)                      //不准获取空值
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));                  //设置日期格式
            if(underScore){
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            }

            return mapper.writeValueAsString(data);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> String allToJson(T data){
        return allToJson(data, false);
    }

    public static String parameters2Json(Object ... parameters) {

        Map<Integer, Object> key2value = new HashMap<>();
        for(int i=0; i < parameters.length; i++){
            key2value.put(i,parameters[i]);
        }

        return JsonConvertUtil.allToJson(key2value, false);
    }
}
