package com.upuphub.dew.community.general.api.service;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;

public interface MessageService {


    /**
     * 持久化用户发送消息
     *
     * @param receiver 消息发送者
     * @param messageType 消息类型
     * @param content 消息正文
     * @return 消息的发送处理状态
     */
    ServiceResponseMessage persistMessage(long receiver, int messageType, String content);

    /**
     * 按时间线ID拉取message信息
     *
     * @param timeLineId 时间线ID
     * @param sequenceId 同步的Message序列号
     * @param size 拉取的Message数量
     * @param forward 拉取的方向
     * @return 消息拉取的结果
     */
    ServiceResponseMessage fetchPersistMessageByTimeLineId(String timeLineId, Long sequenceId, Integer size, boolean forward);
}
