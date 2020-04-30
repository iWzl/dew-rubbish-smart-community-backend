package com.upuphub.dew.community.push.service.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.protobuf.mqtt.MqttHeartBeatMessage;
import com.upuphub.dew.community.connection.protobuf.push.MomentSyncActivityNotify;
import com.upuphub.dew.community.connection.protobuf.relation.RelationMqttMessage;
import com.upuphub.dew.community.push.component.sender.MqttSender;
import com.upuphub.dew.community.push.dao.PushOnlineDao;
import com.upuphub.dew.community.push.service.MailService;
import com.upuphub.dew.community.push.service.MqttHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 20:51
 */

@Service
@Slf4j
public class MqttHandlerServiceImpl implements MqttHandlerService {


    @Resource
    MqttSender mqttSender;
    @Resource
    MailService mailService;
    @Resource
    PushOnlineDao pushOnlineDao;

    @Override
    public void fireRelationChange(ByteString payload) throws InvalidProtocolBufferException {
        RelationMqttMessage relationMqttMessage = RelationMqttMessage.parseFrom(payload);



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
