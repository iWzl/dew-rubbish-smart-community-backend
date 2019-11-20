package com.upuphub.dew.community.moments.controller;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class MomentsServiceController {

    @Autowired
    MomentsService momentsService;

    /**
     * 创建用户动态信息(草稿)
     *
     * @param dynamicContent 用户动态信息正文
     * @return 用户动态正文的创建结果
     */
    @PostMapping(value = "/dynamic/draft")
    public DynamicIdResult commitMomentDynamicContent(@RequestBody MomentDynamicContent dynamicContent) {
        return DynamicIdResult.newBuilder().setDynamicId(
                momentsService.commitMomentDynamicContent(dynamicContent)
        ).build();
    }

    /**
     * 拉取用户动态信息(草稿)
     *
     * @param founder 消息发布者Uin
     * @return 用户动态正文的创建结果
     */
    @PutMapping(value = "/dynamic/draft")
    public MomentDynamicContent pullDraftMomentDynamicContent(@RequestBody Founder founder) {
        return momentsService.pullDraftMomentDynamicContent(founder.getFounder());
    }

    /**
     * 删除用户动态信息(草稿)
     *
     * @param founder 消息发布者Uin
     * @return 用户动态正文的创建结果
     */
    @DeleteMapping(value = "/dynamic/draft")
    public RpcResultCode deleteDraftMomentDynamicContent(@RequestBody Founder founder) {
        return RpcResultCode.newBuilder().setCode(
                momentsService.deleteDraftMomentDynamicContent(founder.getFounder())
        ).build();
    }

    /**
     * 发表用户消息动态
     *
     * @param dynamicContent 用户动态信息正文
     * @return 用户动态消息正文的发布结果
     */
    @PostMapping(value = "/dynamic/publish")
    public RpcResultCode publishMomentDynamicContent(@RequestBody MomentDynamicPublish dynamicContent) {
        return RpcResultCode.newBuilder().setCode(
                momentsService.publishMomentDynamicContent(dynamicContent)
        ).build();
    }

    /**
     * 动态消息的发布者Uin
     *
     * @param dynamicHistoryRequest 消息发布者Uin和分页信息
     * @return 用户动态消息正文的发布结果
     */
    @GetMapping(value = "/dynamic/publish")
    public DynamicsContentResult pullMomentDynamicPublishContent(@RequestBody DynamicHistoryRequest dynamicHistoryRequest) {
        return momentsService.pullMomentDynamicPublishContent(dynamicHistoryRequest);
    }
}
