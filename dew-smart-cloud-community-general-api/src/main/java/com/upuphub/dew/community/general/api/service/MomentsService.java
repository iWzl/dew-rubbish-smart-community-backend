package com.upuphub.dew.community.general.api.service;


import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface MomentsService {
    /**
     * 提交用户瞬间动态得Content
     *
     * @return 发送的状态
     * @param momentDynamicContentReq momentDynamicContent
     */
    public ServiceResponseMessage postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq);

}
