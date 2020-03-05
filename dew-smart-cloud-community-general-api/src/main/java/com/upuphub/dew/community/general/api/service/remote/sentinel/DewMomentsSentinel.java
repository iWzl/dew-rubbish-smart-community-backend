package com.upuphub.dew.community.general.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.general.api.exception.RpcServiceConnectionException;
import com.upuphub.dew.community.general.api.service.remote.DewMomentsService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class DewMomentsSentinel implements DewMomentsService {

    @Override
    public DynamicIdResult commitMomentDynamicContent(MomentDynamicContent dynamicContent) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentDynamicContent pullDraftMomentDynamicContent(Founder founder) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public RpcResultCode deleteDraftMomentDynamicContent(Founder founder) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public RpcResultCode publishMomentDynamicContent(MomentDynamicPublish dynamicContent) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public DynamicsContentResult pullMomentDynamicPublishContent(DynamicHistoryRequest dynamicHistoryRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentCommentResult pushMomentDynamicComment(MomentCommentRequest momentCommentRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentReplyResult pushMomentDynamicCommentReply(MomentReplyRequest momentReplyRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentsDetailsResult fetchMomentsDetailByLocation(MomentDetailsLocationRequest momentDetailsLocationRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentsDetailsResult fetchMomentsDetailByUin(MomentDetailsUinRequest momentDetailsUinRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentsDetailsResult fetchMomentsDetailByClassify(MomentDetailsClassifyRequest momentDetailsClassifyRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }

    @Override
    public MomentsDetailsResult fetchMomentsDetailByTopic(MomentDetailsTopicRequest momentDetailsTopicRequest) {
        throw new RpcServiceConnectionException("Call Rpc Moments Model Error");
    }
}
