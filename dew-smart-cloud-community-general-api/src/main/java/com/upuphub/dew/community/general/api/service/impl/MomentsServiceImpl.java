package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MomentsConst;
import com.upuphub.dew.community.connection.protobuf.moments.Founder;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentDynamicContentResp;
import com.upuphub.dew.community.general.api.service.MomentsService;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import com.upuphub.dew.community.general.api.utils.basic.ResultMessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MomentsServiceImpl implements MomentsService {
    @Autowired
    DewMomentsService remoteMomentsService;

    @Override
    public ServiceResponseMessage postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq) {
        MomentDynamicContent momentDynamicContent = EDSUtil.toProtobufMessage(momentDynamicContentReq);
        int error = remoteMomentsService.commitMomentDynamicContent(momentDynamicContent).getCode();
        if(error == MomentsConst.ERROR_CODE_SUCCESS){
            return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.UPDATE_MOMENTS_DYNAMIC_SUCCESS);
        }
        return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.CALL_RPC_MOMENTS_SVR_ERROR);
    }

    @Override
    public MomentDynamicContentResp pullDraftMomentDynamicContent() {
        MomentDynamicContent momentDynamicContent = remoteMomentsService.pullDraftMomentDynamicContent(Founder.newBuilder().setFounder(HttpUtil.getUserUin()).build());
        return EDSUtil.toHttpVoBean(momentDynamicContent);
    }
}
