package com.upuphub.dew.community.push.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttHeartBeatMessage;
import com.upuphub.dew.community.push.annotation.MqttTopic;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 20:51
 */
public interface MqttHandlerService {

    @MqttTopic(topic = MqttConst.TOPIC_RELATION,tag = MqttConst.TAG_RELATION_SYNC_RELATION)
    @Async("mqttHandlerExecutor")
    void fireRelationChange(ByteString payload) throws InvalidProtocolBufferException;

    @Async("mqttHandlerExecutor")
    void syncDewHeartBeatActivity(MqttHeartBeatMessage payload);
}
