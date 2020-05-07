package com.upuphub.dew.community.general.api.service;

import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface PushService {
    /**
     * 发送邮箱验证码
     * @return 发送的状态
     * @param email 邮箱地址
     */
    ServiceResponseMessage sendEmailVerifyCode(String email);

    @Async
    void fireMessageHasArrived(MessagePayload messagePayload);


}
