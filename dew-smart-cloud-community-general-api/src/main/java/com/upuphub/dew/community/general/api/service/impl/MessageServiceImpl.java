package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MessageConst;
import com.upuphub.dew.community.connection.protobuf.message.*;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.MessageService;
import com.upuphub.dew.community.general.api.service.remote.DewMessageService;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class MessageServiceImpl implements MessageService {


    @Resource
    DewMessageService dewMessageService;

    @Override
    public ServiceResponseMessage persistMessage(long receiver, int messageType, String content) {
        MessagePayload messagePayload = MessagePayload.newBuilder()
                .setMessageTypeValue(messageType)
                .setSender(HttpUtil.getUserUin())
                .setReceiver(receiver)
                .setOpType(MESSAGE_OP_TYPE.MSG_OP_SET)
                .setContent(content)
                .build();
        MessageResultCode rpcResultCode = dewMessageService.persistMessage(messagePayload);
        if(rpcResultCode.getCode() == MessageConst.ERROR_CODE_SUCCESS){
            return ServiceResponseMessage.createBySuccessCodeMessage("发送成功", Collections.singletonMap("messageId",rpcResultCode.getMessageId()));
        }
        return ServiceResponseMessage.createByFailCodeMessage("发送失败");
    }

    @Override
    public ServiceResponseMessage fetchPersistMessageByTimeLineId(String timeLineId, Long sequenceId, Integer size, boolean forward) {
        MessageSearchByTimeLine messageSearchByTimeLine = MessageSearchByTimeLine.newBuilder()
                .setTimeLineId(timeLineId)
                .setSequenceId(sequenceId)
                .setSize(size)
                .setForward(forward)
                .build();
        MessagePayloads messagePayloads = dewMessageService.fetchMessagePayloadsByTimeLine(messageSearchByTimeLine);
        return ServiceResponseMessage.createBySuccessCodeMessage("拉取成功", MessageUtil.buildBase64Message(messagePayloads));
    }
}
