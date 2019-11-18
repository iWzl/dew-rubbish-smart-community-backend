package com.upuphub.dew.community.moments.service;

import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;

public interface MomentContentService {
    /**
     * 校验用户是否有未提交的MomentContent信息
     *
     * @param uin 用户Uin
     * @return 未提交的MomentContent得ID
     */
    Long searchMomentDynamicContentDraftId(long uin);


    /**
     * 校验用户是否有未提交的MomentContent信息
     *
     * @param uin 用户Uin
     * @return 未提交的MomentContent
     */
    MomentDynamicPO searchMomentDynamicContent(long uin);

    /**
     * 创建MomentDynamicContent正文信息
     *
     * @param momentDynamicContent 用户动态正文得详细信息
     * @return 处理得结果状态
     */
    int createMomentDynamicContent(MomentDynamicPO momentDynamicContent);

    /**
     * 更新MomentDynamicContent正文信息
     *
     * @param momentDynamicContent 用户动态正文得详细信息
     * @return 处理得结果状态
     */
    int updateMomentDynamicContent(MomentDynamicPO momentDynamicContent);
}
