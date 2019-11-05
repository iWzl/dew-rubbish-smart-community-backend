package com.upuphub.dew.community.push.service;

import com.google.protobuf.ByteString;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 20:51
 */
public interface MqttHandlerService {

    /**
     * 发送邮箱验证码
     * @param payload 邮箱验证码(邮箱验证码protobuf)
     */
    @Async("mqttHandlerExecutor")
    public void sendEmailCode(ByteString payload);
}
