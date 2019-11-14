package com.upuphub.dew.community.connection.protocol.mqtt;

import com.upuphub.dew.community.connection.common.JsonHelper;

public class PushMessage {
    private int type;
    private String message;
    private Long createTime;
    private Long version;


    public PushMessage(int type, Long createTime) {
        this.type = type;
        this.createTime = createTime;
    }

    public PushMessage(PUSH_MESSAGE_TYPE type, Object message, Long createTime) {
        this.type = type.value();
        this.message = JsonHelper.allToJson(message);
        this.createTime = createTime;
    }

    public PushMessage(int type, Object message, Long createTime, Long version) {
        this.type = type;
        this.message = JsonHelper.allToJson(message);
        this.createTime = createTime;
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
