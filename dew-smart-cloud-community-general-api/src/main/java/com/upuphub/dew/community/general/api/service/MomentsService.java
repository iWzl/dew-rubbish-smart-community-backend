package com.upuphub.dew.community.general.api.service;


import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
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
     * @return 发送的状态
     * @param momentDynamicContentReq momentDynamicContent
     */
    ServiceResponseMessage postMomentDynamicContent(MomentDynamicContentReq momentDynamicContentReq);

    /**
     * 拉取用户正在编辑的Content正文记录
     *
     * @return 用户正在编辑动态正文记录
     */
    MomentDynamicContentResp pullDraftMomentDynamicContent();
}
