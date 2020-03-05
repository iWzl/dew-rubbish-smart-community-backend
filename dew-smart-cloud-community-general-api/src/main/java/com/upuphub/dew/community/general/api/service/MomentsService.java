package com.upuphub.dew.community.general.api.service;


import com.upuphub.dew.community.general.api.bean.dto.MomentCommentDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentIdDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentReplyDTO;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.*;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentDynamicContentResp;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface MomentsService {
    /**
     * 提交用户瞬间动态得Content
     *
     * @return 用户Moments的Id
     * @param momentDynamicContentReq momentDynamicContent
     */
    MomentIdDTO postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq);

    /**
     * 拉取用户正在编辑的Content正文记录
     *
     * @return 用户正在编辑动态正文记录
     */
    MomentDynamicContentResp pullDraftMomentDynamicContent();

    /**
     * 删除用户动态草稿的编辑信息
     *
     * @return 用户动态的草稿的删除情况
     */
    ServiceResponseMessage deleteDraftMomentDynamicContent();

    /**
     * 发布用户的Moments信息
     *
     * @param momentsPublishReq 用户Moments的发布信息
     * @return 用户Moments的Id
     */
    ServiceResponseMessage publishMomentContent(MomentsPublishReq momentsPublishReq);

    /**
     * Moment Comment评论动态
     *
     * @param momentCommentReq moment评论的请求
     * @return Moment评论的ID
     */
    MomentCommentDTO postMomentComment(MomentCommentReq momentCommentReq);

    /**
     * Moment 评论回复
     *
     * @param momentReplyReq moment回复评论
     * @return Moment回复评论的回复ID
     */
    MomentReplyDTO postMomentCommentReply(MomentReplyReq momentReplyReq);

    /**
     * 拉取Moment和回复回复信息同通过地理位置信息
     *
     * @param momentLocationFilterReq 用户携带的地理位置信息和查询的分页参数信息
     * @return 查询处理的处理结果
     */
    ServiceResponseMessage fetchMomentAndReplyDetailByLocationCond(MomentLocationFilterReq momentLocationFilterReq);

    /**
     * 拉取Moment和回复回复信息同通过地理位置信息
     *
     * @param momentUinFilterReq 用户UIN和查询的分页参数信息
     * @return 查询处理的处理结果
     */
    ServiceResponseMessage fetchMomentAndReplyDetailByUin(MomentUinFilterReq momentUinFilterReq);

    /**
     * 拉取Moment和回复回复信息通过Topic
     *
     * @param momentTopicFilterReq  Topic和分页参数
     * @return 查询处理的处理结果
     */
    ServiceResponseMessage fetchMomentAndReplyDetailByTopic(MomentTopicFilterReq momentTopicFilterReq);

    /**
     * 拉取Moment和回复回复信息通过Topic
     *
     * @param momentClassifyFilterReq  Topic和分页参数
     * @return 查询处理的处理结果
     */
    ServiceResponseMessage fetchMomentAndReplyDetailByClassify(MomentClassifyFilterReq momentClassifyFilterReq);
}
