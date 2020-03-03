package com.upuphub.dew.community.moments.service;

import com.upuphub.dew.community.moments.bean.po.MomentActivityPO;
import com.upuphub.dew.community.moments.bean.po.MomentCommentPO;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;

public interface MomentContentService {
    /**
     * 校验用户是否有未提交的MomentContent信息
     *
     * @param uin 用户Uin
     * @return 未提交的MomentContent得ID
     */
    Long searchMomentDynamicDraftIdByUin(long uin);


    /**
     * 校验用户是否有未提交的MomentContent信息
     *
     * @param uin 用户Uin
     * @return 未提交的MomentContent
     */
    MomentDynamicPO searchMomentDynamicContent(long uin);

    /**
     * 根据Moments查询用户的Moment信息(检验Moments是否存在)
     *
     * @param momentId MomentsID
     * @return MomentsId
     */
    MomentDynamicPO searchMomentDynamicContentByMomentId(long momentId);

    /**
     * 根据评论ID查询评论信息
     *
     * @param commentId 评论ID
     * @return 评论的详细信息
     */
    MomentCommentPO searchMomentCommentByCommentId(long commentId);

    /**
     * 创建MomentDynamicContent正文信息
     *
     * @param momentDynamicContent 用户动态正文得详细信息
     * @return 处理得结果状态
     */
    int createDraftMomentDynamicContent(MomentDynamicPO momentDynamicContent);


    /**
     * 保存用户Activity信息
     *
     * @param momentActivity 保存用户Activity信息
     * @return 处理得结果状态
     */
    long saveMomentActivity(MomentActivityPO momentActivity);

    /**
     * 保存用户的Moment评论
     *
     * @param momentComment Moment评论
     * @return moment评论结果
     */
    long saveMomentComment(MomentCommentPO momentComment);

    /**
     * 更新标记用户moment草稿标识
     *
     * @param dynamicId Moment需要标记非草稿的MomentID
     * @param isDraft Moments是否是草稿的标识
     * @return 处理得结果状态
     */
    long updateMomentDraftStatus(long dynamicId, boolean isDraft);

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
