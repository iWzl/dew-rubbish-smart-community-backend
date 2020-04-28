package com.upuphub.dew.community.push.bean.po;

import lombok.Data;

@Data
public class PushOnlinePO {
    private long uin;
    private String clientId;
    private long refreshTime;
    private long notifyTime;
    private int status;
}
