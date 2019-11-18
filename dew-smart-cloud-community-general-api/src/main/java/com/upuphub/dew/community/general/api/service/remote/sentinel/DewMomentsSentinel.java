package com.upuphub.dew.community.general.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.Founder;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.general.api.exception.RpcServiceConnectionException;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import com.upuphub.dew.community.general.api.service.remote.DewPushService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class DewMomentsSentinel implements DewMomentsService {

    @Override
    public RpcResultCode commitMomentDynamicContent(MomentDynamicContent dynamicContent) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentDynamicContent pullDraftMomentDynamicContent(Founder founder) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }
}
