package com.upuphub.dew.community.general.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
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
    public RpcResultCode sendEmailWithTemplateCode(EmailTemplateAndParams emailTemplateAndParams) {
        return null;
    }

    @Override
    public RpcResultCode fireMessageHasArrived(MessagePayload messagePayload) {
        return RpcResultCode.newBuilder().setCode(-1).build();
    }
}
