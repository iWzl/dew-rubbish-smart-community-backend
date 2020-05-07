package com.upuphub.dew.community.message.controller;

import com.upuphub.dew.community.connection.constant.MessageConst;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayloads;
import com.upuphub.dew.community.connection.protobuf.message.MessageResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessageSearchByTimeLine;
import com.upuphub.dew.community.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@Slf4j
@RestController
public class MessageController {

    @Resource
    MessageService messageService;


    /**
     * 持久化用户发送发送的消息
     *
     * @param messagePayload 需要持久化的消息正文
     * @return 持久话消息完成以后的消息正文信息
     */
    @PostMapping("/message/persist")
    public MessageResultCode persistMessage(@RequestBody MessagePayload messagePayload) {
        MessagePayload newMessagePayload = messageService.persistMessage(messagePayload);
        return MessageResultCode.newBuilder().setCode(MessageConst.ERROR_CODE_SUCCESS)
                .setMessageId(newMessagePayload.getMessageId())
                .setMessagePayload(newMessagePayload)
                .build();
    }



    @PostMapping("/message/timeline")
    public MessagePayloads fetchMessagePayloadsByTimeLine(@RequestBody MessageSearchByTimeLine messageSearchByTimeLine) {
        return MessagePayloads.newBuilder().addAllMessagePayloadList(
                messageService.fetchMessagePayloadsByTimeLine(
                        messageSearchByTimeLine.getTimeLineId(),
                        messageSearchByTimeLine.getSequenceId(),
                        messageSearchByTimeLine.getForward(),
                        messageSearchByTimeLine.getSize()
                )
        ).build();
    }
}
