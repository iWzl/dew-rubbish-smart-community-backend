package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("持久化消息请求")
public class PersistMessageReq {
    long receiver;
    int messageType;
    String content;
}
