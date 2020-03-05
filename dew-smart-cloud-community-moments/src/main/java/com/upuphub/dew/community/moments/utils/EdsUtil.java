package com.upuphub.dew.community.moments.utils;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.bean.po.MomentReplyPO;
import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;

/**
 * @author Leo Wang
 */
public class EdsUtil {

    public static final Integer AFFECTED_ROWS_NUMBER_ONE = 1;

    public static MomentDynamicContent toProtobufMessage(MomentDynamicPO momentDynamic) {
        return MomentDynamicContent.newBuilder()
                .setUin(momentDynamic.getFounder())
                .setClassify(momentDynamic.getClassify())
                .setTitle(momentDynamic.getTitle())
                .setContent(null == momentDynamic.getContent() ? "" : momentDynamic.getContent())
                .setTopic(null == momentDynamic.getTopic() ? "" : momentDynamic.getTopic())
                .setLongitude(momentDynamic.getLongitude())
                .setLatitude(momentDynamic.getLatitude())
                .addAllPictures(momentDynamic.getPictures())
                .setUpdateTime(momentDynamic.getUpdateTime())
                .setDynamicContentId(momentDynamic.getMomentId())
                .build();
    }

    public static MomentsPublishPO toCommonBean(MomentDynamicPO momentDynamic, MomentDynamicPublish dynamicPublish) {
        MomentsPublishPO momentsPublish = new MomentsPublishPO();
        momentsPublish.setDynamicId(momentDynamic.getMomentId());
        momentsPublish.setGeoHash(GeoHashUtil.buildGeoHash(dynamicPublish.getLatitude(), dynamicPublish.getLongitude()));
        momentsPublish.setLatitude(dynamicPublish.getLatitude());
        momentsPublish.setLongitude(dynamicPublish.getLongitude());
        momentsPublish.setClassify(momentDynamic.getClassify());
        momentsPublish.setTopic(momentDynamic.getTopic());
        momentsPublish.setPublishBy(dynamicPublish.getPublishBy());
        momentsPublish.setPublishTime(System.currentTimeMillis());
        momentsPublish.setPublishType(dynamicPublish.getPublishType().name());
        momentsPublish.setUpdateTime(System.currentTimeMillis());
        momentsPublish.setPublishStatus(0);
        return momentsPublish;
    }

    public static Integer toCommentType(MOMENTS_COMMENT_TYPE commentTypeValue) {
        switch (commentTypeValue){
            case FAVORITE:
                return com.upuphub.dew.community.moments.bean.dto.MOMENTS_COMMENT_TYPE.FAVORITE.value();
            case REPLY:
                return com.upuphub.dew.community.moments.bean.dto.MOMENTS_COMMENT_TYPE.COMMENT.value();
            default:
                return com.upuphub.dew.community.moments.bean.dto.MOMENTS_COMMENT_TYPE.ERROR_TYPE.value();
        }
    }

    public static PageInfo toProtobufPageInfo(com.github.pagehelper.PageInfo pageInfo) {
        return (PageInfo) MessageUtil.buildMessageByBean(PageInfo.getDescriptor(),
                                PageInfo.newBuilder(),pageInfo);
    }
}
