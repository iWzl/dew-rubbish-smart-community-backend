package com.upuphub.dew.community.general.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.general.api.service.remote.DewPushService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class DewPushServiceSentinel implements DewPushService {

    @Override
    public void sendVerificationCodeMail(EmailAndCode verifyCode){
    }
}
