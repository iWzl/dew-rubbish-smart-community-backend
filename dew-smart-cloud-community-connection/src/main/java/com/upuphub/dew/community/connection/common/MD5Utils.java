package com.upuphub.dew.community.connection.common;

import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicReference;

public class MD5Utils {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        AtomicReference<StringBuffer> hexValue = new AtomicReference<>(new StringBuffer());
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.get().append("0");
            hexValue.get().append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
    /**
     * 根据自己的规则进行MD5加密
     * 例如，现在需求是有字符串传入zhang，xy
     * 其中zhang是传入的字符
     * 然后需要将zhang的字符进行拆分z，和hang，
     * 最后需要加密的字段为zxyhang
     */
    public static String string2MD5WithSalt(String inStr,String salt){
        String finalStr="";
        if(inStr!=null) {
            finalStr = string2MD5(String.format("%s%s%s",
                    inStr.substring(0,inStr.length()/2),
                    salt,
                    inStr.substring(inStr.length()/2,inStr.length())));

        }
        return finalStr;
    }
}
