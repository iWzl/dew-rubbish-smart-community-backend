package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewPushServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(value = "dew-smart-community-moments",configuration = ProtoFeignConfiguration.class,fallback = DewPushServiceSentinel.class)
public interface DewMomentsService {

    @PostMapping(value = "/dynamic/build",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public RpcResultCode commitMomentDynamicContent(@RequestBody MomentDynamicContent dynamicContent);
}
