package com.upuphub.dew.community.push.service.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.protobuf.mqtt.Header;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttHeartBeatMessage;
import com.upuphub.dew.community.connection.protobuf.mqtt.NotifyType;
import com.upuphub.dew.community.connection.protobuf.mqtt.RelationChangeMessage;
import com.upuphub.dew.community.connection.protobuf.relation.RelationMqttMessage;
import com.upuphub.dew.community.push.component.sender.MqttSender;
import com.upuphub.dew.community.push.dao.PushOnlineDao;
import com.upuphub.dew.community.push.service.MqttHandlerService;
import com.upuphub.dew.community.push.service.PushService;
import com.upuphub.dew.community.push.utils.NotifyMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 20:51
 */

@Service
@Slf4j
public class MqttHandlerServiceImpl implements MqttHandlerService {

    @Resource
    PushService pushService;
    @Resource
    PushOnlineDao pushOnlineDao;

    @Override
    public void fireRelationChange(ByteString payload) throws InvalidProtocolBufferException {
        RelationMqttMessage relationMqttMessage = RelationMqttMessage.parseFrom(payload);
        String clientId = pushOnlineDao.selectPushOnlineClientIdByUin(relationMqttMessage.getRecipient());
        if(null != clientId && !"".equals(clientId)){
            RelationChangeMessage relationChangeMessage = RelationChangeMessage.newBuilder()
                    .setRefreshTime(relationMqttMessage.getRefreshTime())
                    .setRelationType(relationMqttMessage.getRelationTypeValue())
                    .setRelationSource(relationMqttMessage.getRelationSourceValue())
                    .setSponsor(relationMqttMessage.getSponsor()).build();
            Header header = NotifyMessageUtil.buildMessageHeader(relationMqttMessage.getRecipient(), NotifyType.NOTIFY_RELATION_CHANGE_VALUE);
            pushService.fireCommonMqttNotify(clientId,header,relationChangeMessage);
        }
    }

    @Override
    public void syncDewHeartBeatActivity(MqttHeartBeatMessage mqttHeartBeatMessage) {
        if(mqttHeartBeatMessage.getUin() == 0){
            return;
        }
        Long uin = pushOnlineDao.selectPushOnlineUinWithUin(mqttHeartBeatMessage.getUin());
        if(uin != null && mqttHeartBeatMessage.getUin() == uin){
            pushOnlineDao.updatePushOnlineInfo(
                    mqttHeartBeatMessage.getUin(),
                    String.format("%s@%s",mqttHeartBeatMessage.getUin(),
                            mqttHeartBeatMessage.getLinkKey()),mqttHeartBeatMessage.getTimestamp());
        }else {
            pushOnlineDao.insertNewPushOnline(mqttHeartBeatMessage.getUin(),
                    String.format("%s@%s",mqttHeartBeatMessage.getUin(),
                            mqttHeartBeatMessage.getLinkKey()),mqttHeartBeatMessage.getTimestamp());
        }
    }
}
