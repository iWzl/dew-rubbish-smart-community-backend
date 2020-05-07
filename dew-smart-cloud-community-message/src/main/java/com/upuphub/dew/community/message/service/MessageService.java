package com.upuphub.dew.community.message.service;

import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;

import java.util.List;

public interface MessageService {
    /**
     * 持久话用户的消息存储
     *
     * @param messagePayload 用户消息内容正文
     * @return 消息保存完成的处理结果
     */
    MessagePayload persistMessage(MessagePayload messagePayload);

    /**
     * 查询消息内容通过消息查询条件
     *
     * @param timeLineId 消息设计的时间线ID
     * @param sequenceId 消息的同步序号
     * @param forward 消息拉取的方向
     * @param size 拉取的数量
     * @return 处理的完成结果
     */
    List<String> fetchMessagePayloadsByTimeLine(String timeLineId, long sequenceId, boolean forward, int size);
}
