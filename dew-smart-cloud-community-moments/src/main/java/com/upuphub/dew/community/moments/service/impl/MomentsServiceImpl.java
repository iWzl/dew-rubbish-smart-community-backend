package com.upuphub.dew.community.moments.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ERROR_CODE;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;
import com.upuphub.dew.community.moments.component.SnowflakeId;
import com.upuphub.dew.community.moments.dao.MomentsPublishMomentsPublishDao;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.service.MomentsService;
import com.upuphub.dew.community.moments.utils.EdsUtil;
import com.upuphub.dew.community.moments.utils.ObjectUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        long momentDynamicId = momentContentService.searchMomentDynamicContentDraftId(dynamicContent.getUin());
        if(ObjectUtil.isEmpty(momentDynamicId)){
            // 没有未提交的草稿,创建草稿记录
            momentDynamicId = snowflakeId.nextId();
            MomentDynamicPO momentDynamicContent =  MessageUtil.messageToCommonPojo(dynamicContent,MomentDynamicPO.class);
            assert momentDynamicContent != null;
            momentDynamicContent.setDynamicId(momentDynamicId);
            momentDynamicContent.setPictures(getPicsList(dynamicContent));
            int error =  momentContentService.createDraftMomentDynamicContent(momentDynamicContent);
            if(error == MOMENTS_ERROR_CODE.SUCCESS.value()){
                return momentDynamicId;
            }else {
                return MomentsConst.ERROR_CODE_COMMON_FAIL;
            }
        }else{
            // 存在未提交的草稿，执行更新
            MomentDynamicPO momentDynamicContent =  MessageUtil.messageToCommonPojo(dynamicContent,MomentDynamicPO.class);
            assert momentDynamicContent != null;
            momentDynamicContent.setDynamicId(momentDynamicId);
            momentDynamicContent.setPictures(getPicsList(dynamicContent));
            // 执行更新新资源操作
            int error = momentContentService.updateDraftMomentDynamicContent(momentDynamicContent);
            if(error == MOMENTS_ERROR_CODE.SUCCESS.value()){
                return momentDynamicId;
            }else {
                return MomentsConst.ERROR_CODE_COMMON_FAIL;
            }
        }
    }

    @Override
    public MomentDynamicContent pullDraftMomentDynamicContent(long founder) {
        MomentDynamicPO momentDynamic = momentContentService.searchMomentDynamicContent(founder);
        if(null == momentDynamic){
            return MomentDynamicContent.newBuilder().build();
        }
        return EdsUtil.toProtobufMessage(momentDynamic);
    }

    @Override
    public int deleteDraftMomentDynamicContent(long founder) {
        int error = momentContentService.deleteDraftMomentDynamicContent(founder);
        if(error == 0){
            return MomentsConst.ERROR_CODE_SUCCESS;
        }else {
            return MomentsConst.ERROR_CODE_COMMON_FAIL;
        }

    }

    @Override
    public int publishMomentDynamicContent(MomentDynamicPublish dynamicPublish) {
        long dynamicId = dynamicPublish.getDynamicId();
        if(dynamicId == 0){
            dynamicId = momentContentService.searchMomentDynamicContentDraftId(dynamicPublish.getPublishBy());
        }
        MomentsPublishPO momentsPublish = EdsUtil.toCommonBean(dynamicId,dynamicPublish);
        int error = momentsPublishMomentsPublishDao.insert(momentsPublish);
        if(error == 1){
            return MomentsConst.ERROR_CODE_SUCCESS;
        }
        return MomentsConst.ERROR_CODE_COMMON_FAIL;
    }

    @Override
    public DynamicsContentResult pullMomentDynamicPublishContent(DynamicHistoryRequest dynamicHistoryRequest) {
        return null;
    }

    private List<String> getPicsList(MomentDynamicContent dynamicContent){
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < dynamicContent.getPicturesCount() ; i++) {
            picList.add(dynamicContent.getPictures(i));
        }
        return picList;
    }
}
