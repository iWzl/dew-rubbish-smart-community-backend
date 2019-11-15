package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
import com.upuphub.dew.community.general.api.service.MomentsService;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MomentsServiceImpl implements MomentsService {
    @Autowired
    DewMomentsService remoteMomentsService;

    @Override
    public ServiceResponseMessage postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq) {
       /* MomentDynamicContent momentDynamicContent = MessageUtil.buildMessageByBean(
                MomentDynamicContent.getDescriptor(),
                MomentDynamicContent.newBuilder(),
                momentDynamicContentReq);*/
        //remoteMomentsService.commitMomentDynamicContent(momentDynamicContent);
        return null;
    }
}
