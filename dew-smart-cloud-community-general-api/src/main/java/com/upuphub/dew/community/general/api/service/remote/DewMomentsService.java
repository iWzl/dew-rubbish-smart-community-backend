package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMomentsSentinel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leo Wang
 * @version 1.0
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



    /**
     * 用户Moment的评论信息
     *
     * @param momentCommentRequest Moment动态信息的评论信息
     * @return 评论用户动态信息的评论结果
     */
    @PostMapping(value = "/dynamic/comment",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentCommentResult pushMomentDynamicComment(@RequestBody MomentCommentRequest momentCommentRequest);

    /**
     * 用户Moment的评论回复信息
     *
     * @param momentReplyRequest Moment动态信息的评论回复信息
     * @return 评论用户动态信息的评论回复结果ID
     */
    @PostMapping(value = "/dynamic/reply",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentReplyResult pushMomentDynamicCommentReply(@RequestBody MomentReplyRequest momentReplyRequest);

    /**
     * 查询用户Moments信息通过用户地理位置信息
     *
     * @param momentDetailsLocationRequest  拉取用户的地理位置信息需要的请求参数
     * @return  Moment的详细相关信息
     */

    @PostMapping(value = "/dynamic/search/location",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentsDetailsResult fetchMomentsDetailByLocation(@RequestBody MomentDetailsLocationRequest momentDetailsLocationRequest);

    /**
     * 查询用户Moments信息通过用户地理位置信息
     *
     * @param momentDetailsUinRequest  拉取需要的请求参数
     * @return  Moment的详细相关信息
     */
    @PostMapping(value = "/dynamic/search/uin",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentsDetailsResult fetchMomentsDetailByUin(@RequestBody MomentDetailsUinRequest momentDetailsUinRequest);


    /**
     * 查询用户Moments信息通过用户地理位置信息
     *
     * @param momentDetailsClassifyRequest  拉取需要的请求参数
     * @return  Moment的详细相关信息
     */
    @PostMapping(value = "/dynamic/search/classify",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentsDetailsResult fetchMomentsDetailByClassify(@RequestBody MomentDetailsClassifyRequest momentDetailsClassifyRequest);

    /**
     * 查询用户Moments信息通过用户地理位置信息
     *
     * @param momentDetailsTopicRequest  拉取需要的请求参数
     * @return  Moment的详细相关信息
     */
    @PostMapping(value = "/dynamic/search/topic",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentsDetailsResult fetchMomentsDetailByTopic(@RequestBody MomentDetailsTopicRequest momentDetailsTopicRequest);

    /**
     * 根据MomentId查询Moment请求消息
     *
     * @param momentIdRequest momentId
     * @return 请求的Moment请求消息正文
     */
    @PostMapping(value = "/dynamic/search/id",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    MomentContentDetailResult fetchMomentDetailByMomentId(@RequestBody MomentIdRequest momentIdRequest);

}
