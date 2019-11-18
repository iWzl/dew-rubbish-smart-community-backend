package com.upuphub.dew.community.moments.service;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;

public interface MomentsService {
    /**
     * 提交动态消息的草稿消息
     *
     * @param dynamicContent 动态消息
     * @return 动态消息的草稿构建结果
     */
    int commitMomentDynamicContent(MomentDynamicContent dynamicContent);

    /**
     * 获取草稿的动态消息
     *
     * @param founder 消息发送者
     * @return 消息的正文信息
     */
    MomentDynamicContent pullDraftMomentDynamicContent(long founder);
}
