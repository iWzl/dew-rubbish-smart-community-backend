package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.Founder;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMomentsSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(qualifier = "dew-moments",value = "dew-smart-community-moments",configuration = ProtoFeignConfiguration.class,fallback = DewMomentsSentinel.class)
public interface DewMomentsService {

    @PostMapping(value = "/dynamic/build",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode commitMomentDynamicContent(@RequestBody MomentDynamicContent dynamicContent);

    /**
     * 创建用户动态信息(草稿)
     *
     * @param founder 消息发布者Uin
     * @return 用户动态正文的创建结果
     */
    @PostMapping(value = "/dynamic/draft/pull",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    MomentDynamicContent pullDraftMomentDynamicContent(@RequestBody Founder founder);
}
