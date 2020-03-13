package com.upuphub.dew.community.moments.service;

import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.bean.dto.MomentsDetailsDTO;

public interface MomentsService {
    /**
     * 提交动态消息的草稿消息
     *
     * @param dynamicContent 动态消息
     * @return 动态消息的草稿ID
     */
    long commitMomentDynamicContent(MomentDynamicContent dynamicContent);

    /**
     * 获取草稿的动态消息
     *
     * @param founder 消息发送者
     * @return 消息的正文信息
     */
    MomentDynamicContent pullDraftMomentDynamicContent(long founder);

    /**
     * 放弃动态草稿的修改
     *
     * @param founder 消息发送者
     * @return 动态消息的草稿删除结果
     */
    int deleteDraftMomentDynamicContent(long founder);

    /**
     * 发布用户消息正文
     *
     * @param dynamicPublish 用户消息正文
     * @return 消息正文的发布结果
     */
    int publishMomentDynamicContent(MomentDynamicPublish dynamicPublish);

    /**
     * 拉取指定发送者的发送的所有动态消息
     *
     * @param dynamicHistoryRequest 用户发送者Uin和分页信息
     * @return 用户发送消息历史的返回结果
     */
    DynamicsContentResult pullMomentDynamicPublishContent(DynamicHistoryRequest dynamicHistoryRequest);

    /**
     * 评论用户Moment
     *
     * @param momentCommentRequest 评论用户Moment的请求体
     * @return 评论用户的贫困请求处理状态
     */
    long pushMomentDynamicComment(MomentCommentRequest momentCommentRequest);

    /**
     * 回复用户Moment评论
     *
     * @param momentReplyRequest 回复用户Moment的请求返回体
     * @return 回复评论的回复ID
     */
    long pushMomentDynamicCommentReply(MomentReplyRequest momentReplyRequest);

    /**
     * 通过地理位置信息筛选Moment详细信息
     *
     * @param momentDetailsLocationRequest 用户地理位置信息的详细请求
     * @return 查询到的Moment返回结果
     */
    MomentsDetailsDTO fetchMomentsDetailByLocation(MomentDetailsLocationRequest momentDetailsLocationRequest);

    /**
     * 查询Moment详细信息通过UIN
     *
     * @param momentDetailsUinRequest moment详细信息请求
     * @return 查询到的Moment返回结果
     */
    MomentsDetailsDTO fetchMomentsDetailByUin(MomentDetailsUinRequest momentDetailsUinRequest);

    /**
     * 通过MomentClassify分类查询Moments
     *
     * @param momentDetailsClassifyRequest 带有MomentId的分类
     * @return 查询到的Moment返回结果
     */
    MomentsDetailsDTO fetchMomentsDetailByClassify(MomentDetailsClassifyRequest momentDetailsClassifyRequest);

    /**
     * 通过Moment参与的话题查询Moment信息
     *
     * @param momentDetailsTopicRequest moment详细信息请求
     * @return 查询到的Moment话题查询的返回结果
     */
    MomentsDetailsDTO fetchMomentsDetailByTopic(MomentDetailsTopicRequest momentDetailsTopicRequest);

    /**
     * 同步Moment活动信息,通过活动行为Id
     *
     * @param syncMomentActivityRequest 同步Moment信息请求
     * @return MomentActivityResult
     */
    MomentActivityResult syncMomentActivityBySyncActivityId(SyncMomentActivityRequest syncMomentActivityRequest);

    /**
     * 根据Moment行为ID请求查询Moment信息
     *
     * @param syncMomentActivityRequest moment行为请求
     * @return 查询到的Moment行为请求的返回值
     */
    MomentActivityDetailsResult fetchMomentActivityBySyncActivityId(SyncMomentActivityRequest syncMomentActivityRequest);

    /**
     * 根据MomentId查询Moments
     *
     * @param momentId MomentID
     * @return 查询到的Moment结果
     */
    MomentContentDetailResult fetchMomentDetailByMomentId(long momentId);
}
