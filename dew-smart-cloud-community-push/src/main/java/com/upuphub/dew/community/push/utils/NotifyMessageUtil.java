package com.upuphub.dew.community.push.utils;

import com.upuphub.dew.community.connection.protobuf.mqtt.Header;
import com.upuphub.dew.community.connection.protobuf.mqtt.NotifyMessage;

import java.util.Base64;
import java.util.UUID;

public class NotifyMessageUtil {
    public static Header buildMessageHeader(long uin,int notifyType){
        return Header.newBuilder()
                .setNotifyUUID(UUID.randomUUID().toString().substring(0,12))
                .setNotifyTypeValue(notifyType)
                .setTimestamp(System.currentTimeMillis())
                .setVersion(1)
                .setToUin(uin)
                .build();
    }

    public static String buildMessageNotifyString(Header header,String payload){
       return Base64.getEncoder().encodeToString(
               NotifyMessage.newBuilder()
                       .setHeader(header)
                       .setPayload(payload)
                       .build().toByteArray()
       );
    }


}
