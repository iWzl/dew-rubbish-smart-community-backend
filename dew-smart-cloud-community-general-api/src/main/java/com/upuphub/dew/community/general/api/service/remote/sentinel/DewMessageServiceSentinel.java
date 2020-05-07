package com.upuphub.dew.community.general.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayloads;
import com.upuphub.dew.community.connection.protobuf.message.MessageResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessageSearchByTimeLine;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
import com.upuphub.dew.community.general.api.service.remote.DewMessageService;
import com.upuphub.dew.community.general.api.service.remote.DewPushService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class DewMessageServiceSentinel implements DewMessageService {
    @Override
    public MessageResultCode persistMessage(MessagePayload messagePayload) {
        return MessageResultCode.newBuilder().setCode(-1).build();
    }

    @Override
    public MessagePayloads fetchMessagePayloadsByTimeLine(MessageSearchByTimeLine messageSearchByTimeLine) {
        return null;
    }
}
