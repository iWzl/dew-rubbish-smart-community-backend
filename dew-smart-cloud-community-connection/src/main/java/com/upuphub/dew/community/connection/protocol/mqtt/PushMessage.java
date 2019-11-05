package com.upuphub.dew.community.connection.protocol.mqtt;

import com.upuphub.dew.community.connection.common.JsonHelper;

public class PushMessage {
    private int type;
    private String message;
    private Long createTime;


    public PushMessage(int type, Long createTime) {
        this.type = type;
        this.createTime = createTime;
    }

    public PushMessage(PUSH_MESSAGE_TYPE type, String message, Long createTime) {
        this.type = type.value();
        this.message = message;
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(PUSH_MESSAGE_TYPE type) {
        this.type = type.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String buildMqttMessage(){
        return JsonHelper.toJson(this);
    }
}
