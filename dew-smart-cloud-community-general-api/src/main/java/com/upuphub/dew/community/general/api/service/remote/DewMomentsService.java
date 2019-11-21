package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMomentsSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(qualifier = "dew-moments",value = "dew-smart-community-moments",configuration = ProtoFeignConfiguration.class,fallback = DewMomentsSentinel.class)
public interface DewMomentsService {

    /**
     * 提交用户动态正文文档
     *
     * @param dynamicContent 用户动态正文
     * @return 受影响的行数
     */
    @PostMapping(value = "/dynamic/draft",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    DynamicIdResult commitMomentDynamicContent(@RequestBody MomentDynamicContent dynamicContent);

    /**
     * 创建用户动态信息(草稿)
     *
     * @param founder 消息发布者Uin
     * @return 用户动态正文的创建结果
     */
    @PutMapping(value = "/dynamic/draft", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentDynamicContent pullDraftMomentDynamicContent(@RequestBody Founder founder);


    /**
     * 删除用户动态信息(草稿)
     *
     * @param founder 消息发布者Uin
     * @return 用户动态正文的创建结果
     */
    @DeleteMapping(value = "/dynamic/draft",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    RpcResultCode deleteDraftMomentDynamicContent(@RequestBody Founder founder);

    /**
     * 发表用户消息动态
     *
     * @param dynamicContent 用户动态信息正文
     * @return 用户动态消息正文的发布结果
     */
    @PostMapping(value = "/dynamic/publish",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    RpcResultCode publishMomentDynamicContent(@RequestBody MomentDynamicPublish dynamicContent);

    /**
     * 动态消息的发布者Uin
     *
     * @param dynamicHistoryRequest 消息发布者Uin和分页信息
     * @return 用户动态消息正文的发布结果
     */
    @GetMapping(value = "/dynamic/publish",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    DynamicsContentResult pullMomentDynamicPublishContent(@RequestBody DynamicHistoryRequest dynamicHistoryRequest);
}
