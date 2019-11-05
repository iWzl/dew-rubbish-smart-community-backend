package com.upuphub.dew.community.general.api.service.rpc.hystrix;

import cc.itsc.rbc.api.service.rpc.RpcPushService;
import cc.itsc.utils.protobuf.push.EmailAndCode;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class RpcPushServiceHystrix implements RpcPushService {

    @Override
    public void sendVerificationCodeMail(EmailAndCode verifyCode){
    }
}
