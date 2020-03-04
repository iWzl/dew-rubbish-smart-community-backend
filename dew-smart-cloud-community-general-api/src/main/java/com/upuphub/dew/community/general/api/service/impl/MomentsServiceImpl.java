package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.general.api.bean.dto.MomentCommentDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentIdDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentReplyDTO;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.*;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentDynamicContentResp;
import com.upuphub.dew.community.general.api.service.MomentsService;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import com.upuphub.dew.community.general.api.utils.basic.ResultMessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Leo Wang
 */

@Service
public class MomentsServiceImpl implements MomentsService {
    @Autowired
    DewMomentsService remoteMomentsService;

    @Override
    public MomentIdDTO postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq) {
        MomentDynamicContent momentDynamicContent = EDSUtil.toProtobufMessage(momentDynamicContentReq);
        long dynamicId = remoteMomentsService.commitMomentDynamicContent(momentDynamicContent).getDynamicId();
        if (dynamicId != MomentsConst.ERROR_CODE_COMMON_FAIL) {
            return MomentIdDTO.builder().momentId(dynamicId).build();
        }
        return MomentIdDTO.builder().momentId(0L).build();
    }

    @Override
    public MomentDynamicContentResp pullDraftMomentDynamicContent() {
        long founderUin = HttpUtil.getUserUin();
        if (founderUin == 0) {
            return null;
        }
        MomentDynamicContent momentDynamicContent = remoteMomentsService.pullDraftMomentDynamicContent(
                Founder.newBuilder().setFounder(founderUin).build());
        if (null == momentDynamicContent) {
            return null;
        }
        return EDSUtil.toHttpVoBean(momentDynamicContent);
    }

    @Override
    public ServiceResponseMessage deleteDraftMomentDynamicContent() {
        int error = remoteMomentsService.deleteDraftMomentDynamicContent(Founder.newBuilder().setFounder(HttpUtil.getUserUin()).build()).getCode();
        if (error == MomentsConst.ERROR_CODE_SUCCESS) {
            return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.DELETE_MOMENTS_DRAFT_SUCCESS);
        }
        return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.DELETE_MOMENTS_DRAFT_FAIL);
    }

    @Override
    public ServiceResponseMessage publishMomentContent(MomentsPublishReq momentsPublishReq) {
        MomentDynamicPublish momentDynamicPublish = EDSUtil.toProtobufMessage(momentsPublishReq);
        if (null != momentDynamicPublish) {
            int error = remoteMomentsService.publishMomentDynamicContent(momentDynamicPublish).getCode();
            if (error == MomentsConst.ERROR_CODE_SUCCESS) {
                return ServiceResponseMessage.createBySuccessCodeMessage("发布成功");
            } else if (error == MomentsConst.ERROR_CODE_NOT_EXISTS) {
                ServiceResponseMessage.createByFailCodeMessage("Moment不存在");
            } else if (error == MomentsConst.ERROR_CODE_PUBLISH_TYPE) {
                ServiceResponseMessage.createByFailCodeMessage("不允许的发布类型");
            }
        }
        return ServiceResponseMessage.createByFailCodeMessage("参数错误");
    }

    @Override
    public MomentCommentDTO postMomentComment(MomentCommentReq momentCommentReq) {
        MomentCommentResult momentCommentResult =
                remoteMomentsService.pushMomentDynamicComment(EDSUtil.toProtobufMessage(momentCommentReq));
        return MomentCommentDTO.builder().commentId(momentCommentResult.getCommentId()).build();
    }

    @Override
    public MomentReplyDTO postMomentCommentReply(MomentReplyReq momentReplyReq) {
        MomentReplyResult momentReplyResult =
                remoteMomentsService.pushMomentDynamicCommentReply(EDSUtil.toProtobufMessage(momentReplyReq));
        return MomentReplyDTO.builder().momentReplyId(momentReplyResult.getReplyId()).build();
    }

    @Override
    public ServiceResponseMessage fetchMomentAndReplyDetailByLocationCond(MomentLocationFilterReq momentLocationFilterReq) {
        if (null == momentLocationFilterReq || null == momentLocationFilterReq.getLocationReq() || null == momentLocationFilterReq.getPageParam()) {
            return ServiceResponseMessage.createByFailCodeMessage("请求的参数不能为空");
        }
        MomentDetailsLocationRequest momentDetailsLocationRequest = EDSUtil.toProtobufMessage(momentLocationFilterReq);
        MomentsDetailsResult momentsDetailsResult = remoteMomentsService.fetchMomentsDetailByLocation(momentDetailsLocationRequest);
        return null;
    }
}
