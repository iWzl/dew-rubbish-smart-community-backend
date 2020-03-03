package com.upuphub.dew.community.push.service.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.common.RegexUtils;
import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.connection.protobuf.push.MomentSyncActivityNotify;
import com.upuphub.dew.community.push.service.MailService;
import com.upuphub.dew.community.push.service.MqttHandlerService;
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
    MailService mailService;

    @Override
    public void sendEmailCode(ByteString payload) {
        try {
            EmailAndCode emailAndCode = EmailAndCode.parseFrom(payload);
            if (!RegexUtils.isEmail(emailAndCode.getEmail())) {
                return;
            }
            int error = mailService.sendVerificationCodeMail(emailAndCode.getEmail(), emailAndCode.getCode().toUpperCase());
            if (error != PushConst.ERROR_CODE_SUCCESS) {
                log.error("Send Email Error {}", error);
            }
        } catch (InvalidProtocolBufferException ignore) { }
    }

    @Override
    public void pushSyncMomentActivity(ByteString payload) {
        try {
            MomentSyncActivityNotify momentSyncActivityNotify = MomentSyncActivityNotify.parseFrom(payload);
            // todo 通知用户拉取moment信息
            System.out.println();
        } catch (InvalidProtocolBufferException ignore) { }
    }
}
