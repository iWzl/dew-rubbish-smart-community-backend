package com.upuphub.dew.community.general.api.service;


import com.upuphub.dew.community.general.api.bean.dto.MomentIdDTO;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentsPublishReq;
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
    MomentIdDTO publishMomentContent(MomentsPublishReq momentsPublishReq);
}
