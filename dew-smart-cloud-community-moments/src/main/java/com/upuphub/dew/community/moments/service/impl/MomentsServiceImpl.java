package com.upuphub.dew.community.moments.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ACTIVITY_TYPE;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ERROR_CODE;
import com.upuphub.dew.community.moments.bean.dto.MomentsDetailsDTO;
import com.upuphub.dew.community.moments.bean.po.*;
import com.upuphub.dew.community.moments.component.SnowflakeId;
import com.upuphub.dew.community.moments.dao.MomentsPublishMomentsPublishDao;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.service.MomentsService;
import com.upuphub.dew.community.moments.utils.EdsUtil;
import com.upuphub.dew.community.moments.utils.GeoHashUtil;
import com.upuphub.dew.community.moments.utils.ObjectUtil;

import com.upuphub.dew.community.moments.utils.ReplaceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LeoWang
 */
@Service
public class MomentsServiceImpl implements MomentsService {
    @Resource
    MomentContentService momentContentService;
    @Resource
    SnowflakeId snowflakeId;
    @Resource
    MomentsPublishMomentsPublishDao momentsPublishMomentsPublishDao;

    @Override
    public long commitMomentDynamicContent(MomentDynamicContent dynamicContent) {
        Long momentDynamicId = momentContentService.searchMomentDynamicDraftIdByUin(dynamicContent.getUin());
        if (ObjectUtil.isEmpty(momentDynamicId)) {
            // 没有未提交的草稿,创建草稿记录
            momentDynamicId = snowflakeId.nextId();
            MomentDynamicPO momentDynamicContent = MessageUtil.messageToCommonPojo(dynamicContent, MomentDynamicPO.class);
            assert momentDynamicContent != null;
            momentDynamicContent.setMomentId(momentDynamicId);
            momentDynamicContent.setPictures(getPicsList(dynamicContent));
            int error = momentContentService.createDraftMomentDynamicContent(momentDynamicContent);
            if (error == MOMENTS_ERROR_CODE.SUCCESS.value()) {
                return momentDynamicId;
            } else {
                return MomentsConst.ERROR_CODE_COMMON_FAIL;
            }
        } else {
            // 存在未提交的草稿，执行更新
            MomentDynamicPO momentDynamicContent = MessageUtil.messageToCommonPojo(dynamicContent, MomentDynamicPO.class);
            assert momentDynamicContent != null;
            momentDynamicContent.setMomentId(momentDynamicId);
            momentDynamicContent.setPictures(getPicsList(dynamicContent));
            // 执行更新新资源操作
            int error = momentContentService.updateDraftMomentDynamicContent(momentDynamicContent);
            if (error == MOMENTS_ERROR_CODE.SUCCESS.value()) {
                return momentDynamicId;
            } else {
                return MomentsConst.ERROR_CODE_COMMON_FAIL;
            }
        }
    }

    @Override
    public MomentDynamicContent pullDraftMomentDynamicContent(long founder) {
        MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContent(founder);
        if (null == momentDynamic) {
            return MomentDynamicContent.newBuilder().build();
        }
        return EdsUtil.toProtobufMessage(momentDynamic);
    }

    @Override
    public int deleteDraftMomentDynamicContent(long founder) {
        int error = momentContentService.deleteDraftMomentDynamicContent(founder);
        if (error == 0) {
            return MomentsConst.ERROR_CODE_SUCCESS;
        } else {
            return MomentsConst.ERROR_CODE_COMMON_FAIL;
        }

    }

    @Override
    public DynamicsContentResult pullMomentDynamicPublishContent(DynamicHistoryRequest dynamicHistoryRequest) {
        return null;
    }

    @Override
    public long pushMomentDynamicComment(MomentCommentRequest momentCommentRequest) {
        MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContentByMomentId(momentCommentRequest.getMomentId());
        if (null == momentDynamic || ObjectUtil.isEmpty(momentCommentRequest.getCommentBy())) {
            return MomentsConst.ERROR_CODE_NOT_EXISTS;
        }
        MomentCommentPO momentComment = MessageUtil.messageToCommonPojo(momentCommentRequest, MomentCommentPO.class);
        if (null == momentComment) {
            return MomentsConst.ERROR_CODE_COMMON_FAIL;
        }
        if (momentCommentRequest.getCommentType().equals(MOMENTS_COMMENT_TYPE.FAVORITE)) {
            List<MomentCommentPO> momentFavorCommentList =
                    momentContentService.searchMomentsCommentByMomentIdAndCommentTypeAndUin(
                            momentComment.getMomentId(), MOMENTS_COMMENT_TYPE.FAVORITE_VALUE,momentComment.getFromUin());
            if (!ObjectUtil.isEmpty(momentFavorCommentList)) {
                for (MomentCommentPO momentFavorComment : momentFavorCommentList) {
                    momentContentService.deleteMomentCommentByCommentId(momentFavorComment.getId());
                }
                return MomentsConst.ERROR_CODE_SUCCESS;
            }
        }
        if (momentComment.getFromUin() != momentCommentRequest.getCommentBy()) {
            notifyMomentActivityUin(momentComment.getMomentId(), momentCommentRequest.getCommentBy(),
                    Collections.singletonList(momentComment.getFromUin()), momentCommentRequest.getCommentType());
        }
        if (!ObjectUtil.isEmpty(momentComment.getContent())) {
            List<Long> notifyUinList = ReplaceUtil.getAtUinList(momentComment.getContent());
            notifyUinList.removeIf((notifyUin) -> notifyUin.equals(momentComment.getFromUin()));
            if (!ObjectUtil.isEmpty(notifyUinList)) {
                notifyMomentActivityUin(momentComment.getMomentId(), momentCommentRequest.getCommentBy(),
                        notifyUinList, momentCommentRequest.getCommentType());
            }
        }
        momentComment.setId(snowflakeId.nextId());
        momentComment.setContentType(EdsUtil.toCommentType(momentCommentRequest.getCommentType()));
        momentComment.setCreateTime(System.currentTimeMillis());
        return momentContentService.saveCommonMomentDetails(momentComment).getId();
    }

    @Override
    public long pushMomentDynamicCommentReply(MomentReplyRequest momentReplyRequest) {
        MomentReplyPO momentReplyInfo = MessageUtil.messageToCommonPojo(momentReplyRequest, MomentReplyPO.class);
        if (null == momentReplyInfo || ObjectUtil.isEmpty(momentReplyRequest.getContent())) {
            return MomentsConst.ERROR_CODE_COMMON_FAIL;
        }
        MomentCommentPO momentCommentInfo = momentContentService.searchMomentCommentByCommentId(momentReplyRequest.getCommentId());
        if (null == momentCommentInfo || ObjectUtil.isEmpty(momentReplyRequest.getReplyBy())) {
            return MomentsConst.ERROR_CODE_NOT_EXISTS;
        }
        MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContentByMomentId(momentCommentInfo.getMomentId());
        if (null == momentDynamic || ObjectUtil.isEmpty(momentReplyRequest.getReplyBy()) || momentCommentInfo.getContentType() == MOMENTS_COMMENT_TYPE.FAVORITE_VALUE) {
            return MomentsConst.ERROR_CODE_NOT_EXISTS;
        }
        if (momentCommentInfo.getFromUin() != momentReplyRequest.getReplyBy()) {
            notifyMomentActivityUin(momentCommentInfo.getMomentId(), momentReplyRequest.getReplyBy(),
                    Collections.singletonList(momentCommentInfo.getFromUin()), MOMENTS_ACTIVITY_TYPE.REPLY);
        }
        if (momentDynamic.getFounder() != momentReplyRequest.getReplyBy()) {
            notifyMomentActivityUin(momentCommentInfo.getMomentId(), momentReplyRequest.getReplyBy(),
                    Collections.singletonList(momentDynamic.getFounder()), MOMENTS_ACTIVITY_TYPE.REPLY);
        }
        if (!ObjectUtil.isEmpty(momentReplyRequest.getContent())) {
            List<Long> notifyUinList = ReplaceUtil.getAtUinList(momentReplyRequest.getContent());
            notifyUinList.removeIf((notifyUin) -> notifyUin.equals(momentReplyRequest.getReplyBy()));
            if (!ObjectUtil.isEmpty(notifyUinList)) {
                notifyMomentActivityUin(momentCommentInfo.getMomentId(), momentReplyRequest.getReplyBy(),
                        notifyUinList, MOMENTS_ACTIVITY_TYPE.AT);
            }
        }
        momentReplyInfo.setCreateTime(System.currentTimeMillis());
        momentReplyInfo.setId(snowflakeId.nextId());
        return momentContentService.saveCommonMomentDetails(momentReplyInfo).getId();
    }

    @Override
    public int publishMomentDynamicContent(MomentDynamicPublish dynamicPublish) {
        long dynamicId = dynamicPublish.getDynamicId();
        if (dynamicId == 0 && dynamicPublish.getPublishType() == MOMENTS_DYNAMIC_PUBLISH_TYPE.REPRINT) {
            return MomentsConst.ERROR_CODE_PUBLISH_TYPE;
        }
        // 当DynamicId为空 并且发布类型不为转发类型,获取Moments信息
        MomentDynamicPO momentDynamic = null;
        if (dynamicId == 0) {
            momentDynamic = momentContentService.searchMomentDynamicContent(dynamicPublish.getPublishBy());
            if (null == momentDynamic) {
                return MomentsConst.ERROR_CODE_NOT_EXISTS;
            }
            // 从草稿中取出Moment,判断Moments中有没有@人的,有@人的，取出@人的信息
            String momentContent = momentDynamic.getContent();
            if (!ObjectUtil.isEmpty(momentContent)) {
                List<Long> notifyUinList = ReplaceUtil.getAtUinList(momentContent);
                if (!ObjectUtil.isEmpty(notifyUinList)) {
                    notifyMomentActivityUin(momentDynamic.getMomentId(),
                            dynamicPublish.getPublishBy(), notifyUinList, MOMENTS_ACTIVITY_TYPE.AT);
                }
            }
            dynamicId = momentDynamic.getMomentId();
        } else {
            momentDynamic = momentContentService.searchMomentDynamicContentByMomentId(dynamicId);
            if (null == momentDynamic) {
                return MomentsConst.ERROR_CODE_NOT_EXISTS;
            } else {
                if (dynamicPublish.getPublishType() == MOMENTS_DYNAMIC_PUBLISH_TYPE.REPRINT) {
                    Long notifyUin = momentDynamic.getFounder();
                    notifyMomentActivityUin(dynamicId, dynamicPublish.getPublishBy(),
                            Collections.singletonList(notifyUin), MOMENTS_ACTIVITY_TYPE.FORWARD);
                }
            }
        }
        MomentsPublishPO momentsPublish = EdsUtil.toCommonBean(momentDynamic, dynamicPublish);
        momentContentService.updateMomentDraftStatus(dynamicId, false);
        int error = momentsPublishMomentsPublishDao.insertMomentsPublishRecord(momentsPublish);
        if (error == EdsUtil.AFFECTED_ROWS_NUMBER_ONE) {
            return MomentsConst.ERROR_CODE_SUCCESS;
        }
        return MomentsConst.ERROR_CODE_COMMON_FAIL;
    }

    @Override
    public MomentsDetailsDTO fetchMomentsDetailByLocation(MomentDetailsLocationRequest momentDetailsLocationRequest) {
        String rangeGeoHash = null;
        if (0 != momentDetailsLocationRequest.getLatitude() && 0 != momentDetailsLocationRequest.getLongitude()) {
            rangeGeoHash = GeoHashUtil.buildGeoHashCityRange(
                    momentDetailsLocationRequest.getLatitude(), momentDetailsLocationRequest.getLongitude());
        }
        PageHelper.startPage(momentDetailsLocationRequest.getPageNum(), momentDetailsLocationRequest.getPageSize());
        List<MomentsPublishPO> momentsPublishList = momentsPublishMomentsPublishDao.selectMomentPublishRecordByLocation(rangeGeoHash);
        return buildMomentsDetailsResult(momentsPublishList);
    }

    @Override
    public MomentsDetailsDTO fetchMomentsDetailByUin(MomentDetailsUinRequest momentDetailsUinRequest) {
        PageHelper.startPage(momentDetailsUinRequest.getPageNum(), momentDetailsUinRequest.getPageSize());
        List<MomentsPublishPO> momentsPublishList = momentsPublishMomentsPublishDao
                .selectMomentPublishRecordByUin(momentDetailsUinRequest.getUin());
        return buildMomentsDetailsResult(momentsPublishList);
    }

    @Override
    public MomentsDetailsDTO fetchMomentsDetailByClassify(MomentDetailsClassifyRequest momentDetailsClassifyRequest) {
        PageHelper.startPage(momentDetailsClassifyRequest.getPageNum(), momentDetailsClassifyRequest.getPageSize());
        List<MomentsPublishPO> momentsPublishList = momentsPublishMomentsPublishDao
                .selectMomentPublishRecordByClassify(momentDetailsClassifyRequest.getClassify());
        return buildMomentsDetailsResult(momentsPublishList);
    }

    @Override
    public MomentsDetailsDTO fetchMomentsDetailByTopic(MomentDetailsTopicRequest momentDetailsTopicRequest) {
        PageHelper.startPage(momentDetailsTopicRequest.getPageNum(), momentDetailsTopicRequest.getPageSize());
        List<MomentsPublishPO> momentsPublishList = momentsPublishMomentsPublishDao
                .selectMomentPublishRecordByTopic(momentDetailsTopicRequest.getTopic());
        return buildMomentsDetailsResult(momentsPublishList);
    }

    @Override
    public MomentContentDetailResult fetchMomentDetailByMomentId(long momentId) {
        if (0 != momentId) {
            MomentsPublishPO momentDynamicPO = momentsPublishMomentsPublishDao.selectMomentPublishRecordByDynamicId(momentId);
            if (null != momentDynamicPO) {
                return buildMomentsDetailsResult(Collections.singletonList(momentDynamicPO)).getMomentCommentDetailResults().get(0);
            }
        }
        return MomentContentDetailResult.newBuilder().build();
    }

    @Override
    public MomentActivityResult syncMomentActivityBySyncActivityId(SyncMomentActivityRequest syncMomentActivityRequest) {
        // todo Moment Activity 同步标识
        return null;
    }

    @Override
    public MomentActivityDetailsResult fetchMomentActivityBySyncActivityId(SyncMomentActivityRequest syncMomentActivityRequest) {
        //todo Moment Activity 同步消息拉取
        return null;
    }

    private MomentsDetailsDTO buildMomentsDetailsResult(List<MomentsPublishPO> momentsPublishList) {
        PageInfo<MomentsPublishPO> pageOfMomentsPublishList = new PageInfo<>(momentsPublishList);
        if (!pageOfMomentsPublishList.getList().isEmpty()) {
            return MomentsDetailsDTO.builder()
                    .momentCommentDetailResults(fetchMomentContentDetailByPublishList(pageOfMomentsPublishList.getList()))
                    .pageInfo(EdsUtil.toProtobufPageInfo(pageOfMomentsPublishList))
                    .build();
        }
        return MomentsDetailsDTO.builder()
                .momentCommentDetailResults(Collections.emptyList())
                .pageInfo(com.upuphub.dew.community.connection.protobuf.moments.PageInfo.newBuilder().build())
                .build();
    }

    private List<MomentContentDetailResult> fetchMomentContentDetailByPublishList(List<MomentsPublishPO> momentsPublishList) {
        List<MomentContentDetailResult> momentContentDetailResults = new ArrayList<>(momentsPublishList.size());
        List<Long> momentsIdList = momentsPublishList.stream().map(MomentsPublishPO::getDynamicId).collect(Collectors.toList());
        Map<Long, MomentDynamicPO> momentDynamicMap = momentContentService.searchMomentDynamicContentByMomentIds(
                momentsIdList);
        if (momentDynamicMap.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Long, List<MomentCommentPO>> momentCommentListMap = momentContentService.searchMomentsCommentByMomentIds(momentsIdList);
        for (MomentsPublishPO momentsPublish : momentsPublishList) {
            MomentDynamicPO momentDynamic = momentDynamicMap.getOrDefault(momentsPublish.getDynamicId(), new MomentDynamicPO());
            MomentContentDetailResult momentContentDetailResult = MomentContentDetailResult.newBuilder()
                    .setMomentId(momentsPublish.getDynamicId())
                    .setPublisher(momentsPublish.getPublishBy())
                    .setLatitude(momentsPublish.getLatitude())
                    .setLongitude(momentsPublish.getLongitude())
                    .setPublishType(MOMENTS_DYNAMIC_PUBLISH_TYPE.valueOf(momentsPublish.getPublishType()))
                    .setPublishedDate(momentsPublish.getPublishTime())
                    .setOriginPublisher(momentDynamic.getFounder())
                    .setClassify(momentDynamic.getClassify())
                    .setContent(momentDynamic.getContent())
                    .setTopic(momentDynamic.getTopic())
                    .setTitle(momentDynamic.getTitle())
                    .addAllPictures(momentDynamic.getPictures())
                    .addAllMomentCommentDetailResults(fetchMomentCommentDetailByMomentId(momentCommentListMap.get(momentsPublish.getDynamicId())))
                    .build();
            momentContentDetailResults.add(momentContentDetailResult);
        }
        return momentContentDetailResults;
    }

    private List<MomentCommentDetailResult> fetchMomentCommentDetailByMomentId(List<MomentCommentPO> momentCommentList) {
        if (null == momentCommentList || ObjectUtil.isEmpty(momentCommentList)) {
            return Collections.emptyList();
        } else {
            List<MomentCommentDetailResult> momentCommentDetailResults = new ArrayList<>(momentCommentList.size());
            Map<Long, List<MomentReplyPO>> momentReplyList = momentContentService.searchMomentsCommentReplyByCommentIds(momentCommentList.stream().map(MomentCommentPO::getId).collect(Collectors.toList()));
            for (MomentCommentPO momentComment : momentCommentList) {
                MomentCommentDetailResult momentCommentDetailResult = MomentCommentDetailResult.newBuilder()
                        .setCommentId(momentComment.getId())
                        .setCommentType(momentComment.getContentType())
                        .setContent(momentComment.getContent())
                        .setCommentator(momentComment.getFromUin())
                        .setCommentDate(momentComment.getCreateTime())
                        .addAllCommentReplyDetailResults(fetchMomentCommentReplyDetailByCommentId(momentReplyList.get(momentComment.getId())))
                        .build();
                momentCommentDetailResults.add(momentCommentDetailResult);
            }
            return momentCommentDetailResults;
        }
    }

    private List<CommentReplyDetailResult> fetchMomentCommentReplyDetailByCommentId(List<MomentReplyPO> momentReplyList) {
        if (ObjectUtil.isEmpty(momentReplyList)) {
            return Collections.emptyList();
        } else {
            List<CommentReplyDetailResult> commentReplyDetailResults = new ArrayList<>(momentReplyList.size());
            for (MomentReplyPO momentReply : momentReplyList) {
                CommentReplyDetailResult commentReplyDetailResult = CommentReplyDetailResult.newBuilder()
                        .setReplyId(momentReply.getId())
                        .setContent(momentReply.getContent())
                        .setReplyBy(momentReply.getFromUin())
                        .setReplyDate(momentReply.getCreateTime())
                        .build();
                commentReplyDetailResults.add(commentReplyDetailResult);
            }
            return commentReplyDetailResults;
        }
    }

    private void notifyMomentActivityUin(Long momentId, Long byUin, List<Long> notifyUinList, MOMENTS_COMMENT_TYPE momentsCommentType) {
        switch (momentsCommentType) {
            case REPLY:
                notifyMomentActivityUin(momentId,
                        byUin, notifyUinList, MOMENTS_ACTIVITY_TYPE.COMMENT);
                break;
            case FAVORITE:
                notifyMomentActivityUin(momentId,
                        byUin, notifyUinList, MOMENTS_ACTIVITY_TYPE.FAVORITE);
                break;
            default:
                break;
        }
    }


    private void notifyMomentActivityUin(Long momentId, Long byUin, List<Long> notifyUinList, MOMENTS_ACTIVITY_TYPE activityType) {
        if (ObjectUtil.isEmpty(notifyUinList)) {
            return;
        }
        List<MomentActivityPO> momentActivityList = new ArrayList<>(notifyUinList.size());
        for (Long notifyUin : notifyUinList) {
            momentActivityList.add(MomentActivityPO.builder()
                    .activityId(snowflakeId.nextId())
                    .activityTypeNumber(activityType.value())
                    .momentId(momentId)
                    .isSync(false)
                    .forUin(notifyUin)
                    .byUin(byUin)
                    .updateTime(System.currentTimeMillis())
                    .build()
            );
        }
        momentActivityList.forEach(momentActivity -> momentContentService.saveMomentActivity(momentActivity));
    }

    private List<String> getPicsList(MomentDynamicContent dynamicContent) {
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < dynamicContent.getPicturesCount(); i++) {
            picList.add(dynamicContent.getPictures(i));
        }
        return picList;
    }
}
