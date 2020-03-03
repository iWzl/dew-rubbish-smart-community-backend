package com.upuphub.dew.community.moments.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ACTIVITY_TYPE;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ERROR_CODE;
import com.upuphub.dew.community.moments.bean.po.*;
import com.upuphub.dew.community.moments.component.SnowflakeId;
import com.upuphub.dew.community.moments.dao.MomentsPublishMomentsPublishDao;
import com.upuphub.dew.community.moments.exception.MomentNotExistException;
import com.upuphub.dew.community.moments.exception.MomentParamException;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.service.MomentsService;
import com.upuphub.dew.community.moments.utils.EdsUtil;
import com.upuphub.dew.community.moments.utils.ObjectUtil;

import com.upuphub.dew.community.moments.utils.ReplaceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return momentContentService.saveMomentComment(momentComment);
    }

    @Override
    public long pushMomentDynamicCommentReply(MomentReplyRequest momentReplyRequest) {
        MomentCommentPO momentCommentInfo = momentContentService.searchMomentCommentByCommentId(momentReplyRequest.getCommentId());
        if (null == momentCommentInfo || ObjectUtil.isEmpty(momentReplyRequest.getReplyBy())) {
            return MomentsConst.ERROR_CODE_NOT_EXISTS;
        }
        MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContentByMomentId(momentCommentInfo.getMomentId());
        if (null == momentDynamic || ObjectUtil.isEmpty(momentReplyRequest.getReplyBy()) || momentCommentInfo.getContentType() == MOMENTS_COMMENT_TYPE.FAVORITE_VALUE) {
            return MomentsConst.ERROR_CODE_NOT_EXISTS;
        }
        if (momentCommentInfo.getFromUin() != momentReplyRequest.getReplyBy()) {
          /*  notifyMomentActivityUin(momentCommentInfo.getMomentId(), momentCommentRequest.getCommentBy(),
                    Collections.singletonList(momentCommentInfo.getFromUin()), momentCommentRequest.getCommentType());*/
        }
        if (momentDynamic.getFounder() != momentReplyRequest.getReplyBy()) {
            /*notifyMomentActivityUin(momentComment.getMomentId(), momentCommentRequest.getCommentBy(),
                    Collections.singletonList(momentComment.getFromUin()), momentCommentRequest.getCommentType());*/
        }
        //MomentReplyPO momentReplyPO
        System.out.println(momentReplyRequest);
        return 0;
    }

    @Override
    public int publishMomentDynamicContent(MomentDynamicPublish dynamicPublish) {
        long dynamicId = dynamicPublish.getDynamicId();
        if (dynamicId == 0 && dynamicPublish.getPublishType() == MOMENTS_DYNAMIC_PUBLISH_TYPE.REPRINT) {
            return MomentsConst.ERROR_CODE_PUBLISH_TYPE;
        }
        // 当DynamicId为空 并且发布类型不为转发类型,获取Moments信息
        if (dynamicId == 0) {
            MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContent(dynamicPublish.getPublishBy());
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
                    System.out.println(notifyUinList);
                }
            }
            dynamicId = momentDynamic.getMomentId();
        } else {
            MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContentByMomentId(dynamicId);
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
        MomentsPublishPO momentsPublish = EdsUtil.toCommonBean(dynamicId, dynamicPublish);
        momentContentService.updateMomentDraftStatus(dynamicId, false);
        int error = momentsPublishMomentsPublishDao.insertMomentsPublishRecord(momentsPublish);
        if (error == EdsUtil.AFFECTED_ROWS_NUMBER_ONE) {
            return MomentsConst.ERROR_CODE_SUCCESS;
        }
        return MomentsConst.ERROR_CODE_COMMON_FAIL;
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
