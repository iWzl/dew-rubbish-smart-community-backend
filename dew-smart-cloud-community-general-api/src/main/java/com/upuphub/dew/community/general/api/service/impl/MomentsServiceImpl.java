package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.general.api.bean.dto.MomentCommentDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentIdDTO;
import com.upuphub.dew.community.general.api.bean.dto.MomentReplyDTO;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.*;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentDynamicContentResp;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentsDetailsResp;
import com.upuphub.dew.community.general.api.bean.vo.resp.PageInfoResp;
import com.upuphub.dew.community.general.api.service.AccountService;
import com.upuphub.dew.community.general.api.service.MomentsService;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import com.upuphub.dew.community.general.api.utils.basic.ResultMessageConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Leo Wang
 */

@Service
public class MomentsServiceImpl implements MomentsService {
    @Resource
    DewMomentsService remoteMomentsService;
    @Resource
    AccountService accountService;

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
    public ServiceResponseMessage fetchMomentAndReplyDetailByUin(MomentUinFilterReq momentUinFilterReq) {
        if (null == momentUinFilterReq || null == momentUinFilterReq.getPageParamRequest()) {
            return ServiceResponseMessage.createByFailCodeMessage("请求的参数不能为空");
        }
        MomentDetailsUinRequest momentDetailsUinRequest = EDSUtil.toProtobufMessage(momentUinFilterReq);
        MomentsDetailsResult momentsDetailsResult = remoteMomentsService.fetchMomentsDetailByUin(momentDetailsUinRequest);
        return ServiceResponseMessage.createBySuccessCodeMessage(
                buildMomentsResponseDetails(momentsDetailsResult)
        );
    }

    @Override
    public ServiceResponseMessage fetchMomentAndReplyDetailByTopic(MomentTopicFilterReq momentTopicFilterReq) {
        if (null == momentTopicFilterReq || null == momentTopicFilterReq.getPageRequest()) {
            return ServiceResponseMessage.createByFailCodeMessage("请求的参数不能为空");
        }
        MomentDetailsTopicRequest momentDetailsTopicRequest = EDSUtil.toProtobufMessage(momentTopicFilterReq);
        MomentsDetailsResult momentsDetailsResult = remoteMomentsService.fetchMomentsDetailByTopic(momentDetailsTopicRequest);
        return ServiceResponseMessage.createBySuccessCodeMessage(
                buildMomentsResponseDetails(momentsDetailsResult)
        );
    }

    @Override
    public ServiceResponseMessage fetchMomentAndReplyDetailByClassify(MomentClassifyFilterReq momentClassifyFilterReq) {
        if (null == momentClassifyFilterReq || null == momentClassifyFilterReq.getPageRequest()) {
            return ServiceResponseMessage.createByFailCodeMessage("请求的参数不能为空");
        }
        MomentDetailsClassifyRequest momentDetailsClassifyRequest = EDSUtil.toProtobufMessage(momentClassifyFilterReq);
        MomentsDetailsResult momentsDetailsResult = remoteMomentsService.fetchMomentsDetailByClassify(momentDetailsClassifyRequest);
        return ServiceResponseMessage.createBySuccessCodeMessage(
                buildMomentsResponseDetails(momentsDetailsResult)
        );
    }

    private MomentsDetailsResp buildMomentsResponseDetails(MomentsDetailsResult momentsDetailsResult) {
        MomentsDetailsResp momentsDetailsResp = new MomentsDetailsResp();
        if (null != momentsDetailsResult) {
            PageInfoResp pageInfoResp = MessageUtil.messageToCommonPojo(momentsDetailsResult.getPageInfo(), PageInfoResp.class);
            List<MomentsDetailsResp.MomentContent> momentContentList = new ArrayList<>(momentsDetailsResult.getMomentContentDetailResultsCount());
            for (MomentContentDetailResult momentContentDetailResult : momentsDetailsResult.getMomentContentDetailResultsList()) {
                MomentsDetailsResp.MomentContent momentContent = MomentsDetailsResp.MomentContent.builder()
                        .momentId(momentContentDetailResult.getMomentId())
                        .publisher(accountService.pullSimpleProfileByUin(momentContentDetailResult.getPublisher()))
                        .originPublisher(accountService.pullSimpleProfileByUin(momentContentDetailResult.getOriginPublisher()))
                        .topic(momentContentDetailResult.getTopic())
                        .title(momentContentDetailResult.getTitle())
                        .classify(momentContentDetailResult.getClassify())
                        .content(momentContentDetailResult.getContent())
                        .pictures(momentContentDetailResult.getPicturesList())
                        .publishedDate(momentContentDetailResult.getPublishedDate())
                        .latitude(momentContentDetailResult.getLatitude())
                        .longitude(momentContentDetailResult.getLongitude())
                        .publishType(momentContentDetailResult.getPublishType().name())
                        .momentCommentList(buildMomentCommentByDetailResult(
                                momentContentDetailResult.getMomentCommentDetailResultsList()))
                        .build();
                momentContentList.add(momentContent);
            }
            momentsDetailsResp.setMomentContentList(momentContentList);
            momentsDetailsResp.setPageInfoResp(pageInfoResp);
        }
       return momentsDetailsResp;
    }

    @Override
    public ServiceResponseMessage fetchMomentAndReplyDetailByLocationCond(MomentLocationFilterReq momentLocationFilterReq) {
        if (null == momentLocationFilterReq || null == momentLocationFilterReq.getLocationReq() || null == momentLocationFilterReq.getPageParam()) {
            return ServiceResponseMessage.createByFailCodeMessage("请求的参数不能为空");
        }
        MomentDetailsLocationRequest momentDetailsLocationRequest = EDSUtil.toProtobufMessage(momentLocationFilterReq);
        MomentsDetailsResult momentsDetailsResult = remoteMomentsService.fetchMomentsDetailByLocation(momentDetailsLocationRequest);
        return ServiceResponseMessage.createBySuccessCodeMessage(
                buildMomentsResponseDetails(momentsDetailsResult)
        );
    }

    private List<MomentsDetailsResp.MomentComment> buildMomentCommentByDetailResult(List<MomentCommentDetailResult> momentCommentDetailResultsList){
        if(null == momentCommentDetailResultsList || momentCommentDetailResultsList.isEmpty()){
            return Collections.emptyList();
        }
        List<MomentsDetailsResp.MomentComment> momentCommentList = new ArrayList<>(momentCommentDetailResultsList.size());
        for (MomentCommentDetailResult momentCommentDetailResult : momentCommentDetailResultsList) {
            MomentsDetailsResp.MomentComment momentComment = MomentsDetailsResp.MomentComment.builder()
                    .commentId(momentCommentDetailResult.getCommentId())
                    .commentType(momentCommentDetailResult.getCommentType())
                    .content(momentCommentDetailResult.getContent())
                    .commentator(accountService.pullSimpleProfileByUin(momentCommentDetailResult.getCommentator()))
                    .commentDate(momentCommentDetailResult.getCommentDate())
                    .commentReplyList(buildMomentCommentReplyByDetailResult(momentCommentDetailResult.getCommentReplyDetailResultsList()))
                    .build();
            momentCommentList.add(momentComment);
        }
        return momentCommentList;
    }

    private List<MomentsDetailsResp.CommentReply> buildMomentCommentReplyByDetailResult(List<CommentReplyDetailResult> momentCommentReplyDetailResultsList){
        if(null == momentCommentReplyDetailResultsList || momentCommentReplyDetailResultsList.isEmpty()){
            return Collections.emptyList();
        }
        List<MomentsDetailsResp.CommentReply> momentCommentList = new ArrayList<>(momentCommentReplyDetailResultsList.size());
        for (CommentReplyDetailResult commentReplyDetailResult : momentCommentReplyDetailResultsList) {
            MomentsDetailsResp.CommentReply commentReply = MomentsDetailsResp.CommentReply.builder()
                    .replyId(commentReplyDetailResult.getReplyId())
                    .content(commentReplyDetailResult.getContent())
                    .replyBy(accountService.pullSimpleProfileByUin(commentReplyDetailResult.getReplyBy()))
                    .replyDate(commentReplyDetailResult.getReplyDate())
                    .build();
            momentCommentList.add(commentReply);
        }
        return momentCommentList;
    }


}
