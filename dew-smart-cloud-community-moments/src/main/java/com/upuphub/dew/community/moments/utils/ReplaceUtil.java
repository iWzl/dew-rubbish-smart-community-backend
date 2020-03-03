package com.upuphub.dew.community.moments.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则替换工具
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2020/1/31 15:22
 */
public class ReplaceUtil {

    private final static String MOMENT_AT_REGEX = "@\\{uin:(.*?)\\}";

    public static List<String> getReplaceParams(String body, String regex){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(body);
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    public static String buildNewTextWithReplaceParams(String body, Map<String, String> replaceParams, String start, String end) {
        String replaceString = body;
        for (Map.Entry<String, String> entry : replaceParams.entrySet()) {
            replaceString = replaceString.replace(String.format("%s%s%s",start,entry.getKey(),end),entry.getValue());
        }
        return replaceString;
    }

    public static List<Long> getAtUinList(String body){
        List<String> uinList = getReplaceParams(body,MOMENT_AT_REGEX);
        if(ObjectUtil.isEmpty(uinList)){return Collections.emptyList();}
        Set<Long> uinNumberSet = new HashSet<>();
        for (String stringUin : uinList) {
            if(!ObjectUtil.isEmpty(stringUin) && stringUin.matches("^\\d+$")){
                uinNumberSet.add(Long.parseLong(stringUin));
            }
        }
        return new ArrayList<>(uinNumberSet);
    }

    public static void main(String[] args) {
        String momentsBody = "Hello This is Moment Content @{uin:123456}@{uin:123457}@{uin:123458}";
        System.out.println(getAtUinList(momentsBody));
    }
}
