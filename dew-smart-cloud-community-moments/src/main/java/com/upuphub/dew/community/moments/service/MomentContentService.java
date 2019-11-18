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
    int createDraftMomentDynamicContent(MomentDynamicPO momentDynamicContent);

    /**
     * 更新MomentDynamicContent正文信息
     *
     * @param momentDynamicContent 用户动态正文得详细信息
     * @return 处理得结果状态
     */
    int updateDraftMomentDynamicContent(MomentDynamicPO momentDynamicContent);

    /**
     * 删除用户动态信息结果
     *
     * @param founder 用户Uin
     * @return 删除用户动态草稿
     */
    int deleteDraftMomentDynamicContent(long founder);
}
