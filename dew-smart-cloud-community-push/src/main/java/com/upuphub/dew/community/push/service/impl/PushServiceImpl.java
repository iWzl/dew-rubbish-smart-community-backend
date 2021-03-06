package com.upuphub.dew.community.push.service.impl;

import com.google.protobuf.Message;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.mqtt.ChatNotifyMessage;
import com.upuphub.dew.community.connection.protobuf.mqtt.Header;
import com.upuphub.dew.community.connection.protobuf.mqtt.MachineNotifyMessage;
import com.upuphub.dew.community.connection.protobuf.mqtt.NotifyType;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;
import com.upuphub.dew.community.push.component.sender.MqttSender;
import com.upuphub.dew.community.push.dao.PushOnlineDao;
import com.upuphub.dew.community.push.service.PushService;
import com.upuphub.dew.community.push.utils.NotifyMessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;

@Service
public class PushServiceImpl implements PushService {

    @Resource
    MqttSender mqttSender;
    @Resource
    PushOnlineDao pushOnlineDao;

    @Override
    public int fireMachineHealthInfoWithUin(long uin, long timestamp) {
        String clientId= pushOnlineDao.selectPushOnlineClientIdByUin(uin);
        if(null != clientId && !"".equals(clientId)){
            Header header = NotifyMessageUtil.buildMessageHeader(uin, NotifyType.SYNC_MACHINE_HEALTH_VALUE);
            mqttSender.sendToMqtt(clientId,1,NotifyMessageUtil.buildMessageNotifyString(header, String.valueOf(timestamp)));
        }
        return PushConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public int fireMachineSearch(SyncMachineSearchInfo syncMachineSearchInfo) {
        String clientId= pushOnlineDao.selectPushOnlineClientIdByUin(syncMachineSearchInfo.getUin());
        if(null != clientId && !"".equals(clientId)){
            Header header = NotifyMessageUtil.buildMessageHeader(syncMachineSearchInfo.getUin(), NotifyType.NOTIFY_MACHINE_SEARCH_VALUE);
            MachineNotifyMessage machineNotifyMessage = MachineNotifyMessage.newBuilder()
                    .setNikeName(syncMachineSearchInfo.getMachineNikeName())
                    .setSearchKey(syncMachineSearchInfo.getSearchKey())
                    .setTimestamp(syncMachineSearchInfo.getTimestamp())
                    .build();
            String machineNotifyMessagePayload = Base64.getEncoder().encodeToString(machineNotifyMessage.toByteArray());
            mqttSender.sendToMqtt(clientId,NotifyMessageUtil.buildMessageNotifyString(header,machineNotifyMessagePayload));
        }
        return PushConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public int fireMessageHasArrived(MessagePayload messagePayload) {
        String clientId= pushOnlineDao.selectPushOnlineClientIdByUin(messagePayload.getReceiver());
        if(null != clientId && !"".equals(clientId)){
            Header header = NotifyMessageUtil.buildMessageHeader(messagePayload.getReceiver(), NotifyType.SYNC_NEW_MESSAGE_VALUE);
            ChatNotifyMessage chatNotifyMessage = ChatNotifyMessage.newBuilder()
                    .setMessagePayload(MessageUtil.buildBase64Message(messagePayload))
                    .build();
            String machineNotifyMessagePayload = Base64.getEncoder().encodeToString(chatNotifyMessage.toByteArray());
            mqttSender.sendToMqtt(clientId,NotifyMessageUtil.buildMessageNotifyString(header,machineNotifyMessagePayload));
        }
        return PushConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public void fireCommonMqttNotify(String clientId, Header header, Message message) {
        String payloadString = Base64.getEncoder().encodeToString(message.toByteArray());
        mqttSender.sendToMqtt(clientId,NotifyMessageUtil.buildMessageNotifyString(header,payloadString));
    }

}
