package com.upuphub.dew.community.moments.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.moments.bean.dto.MOMENTS_ERROR_CODE;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.component.SnowflakeId;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.service.MomentsService;

import com.upuphub.dew.community.moments.utils.EdsUtil;
import com.upuphub.dew.community.moments.utils.ObjectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MomentsServiceImpl implements MomentsService {
    @Resource
    MomentContentService momentContentService;
    @Resource
    SnowflakeId snowflakeId;

    @Override
    public int commitMomentDynamicContent(MomentDynamicContent dynamicContent) {
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
                return MomentsConst.ERROR_CODE_SUCCESS;
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
                return MomentsConst.ERROR_CODE_SUCCESS;
            }else {
                return MomentsConst.ERROR_CODE_COMMON_FAIL;
            }
        }
    }

    @Override
    public MomentDynamicContent pullDraftMomentDynamicContent(long founder) {
        MomentDynamicPO momentDynamicPO = momentContentService.searchMomentDynamicContent(founder);
        return EdsUtil.toProtobufMessage(momentDynamicPO);
    }

    @Override
    public int deleteDraftMomentDynamicContent(long founder) {
        momentContentService.deleteDraftMomentDynamicContent(founder);
        return MomentsConst.ERROR_CODE_SUCCESS;
    }

    private List<String> getPicsList(MomentDynamicContent dynamicContent){
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < dynamicContent.getPicturesCount() ; i++) {
            picList.add(dynamicContent.getPictures(i));
        }
        return picList;
    }
}
