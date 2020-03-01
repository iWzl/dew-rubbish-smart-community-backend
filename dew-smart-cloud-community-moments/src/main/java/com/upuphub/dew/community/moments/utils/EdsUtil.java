package com.upuphub.dew.community.moments.utils;

import com.upuphub.dew.community.connection.protobuf.moments.MOMENTS_COMMENT_TYPE;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicPublish;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
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

    public static MomentsPublishPO toCommonBean(long dynamicId, MomentDynamicPublish dynamicPublish) {
        MomentsPublishPO momentsPublish = new MomentsPublishPO();
        momentsPublish.setDynamicId(dynamicId);
        momentsPublish.setGeohash(GeoHashUtil.buildGeoHash(dynamicPublish.getLatitude(), dynamicPublish.getLongitude()));
        momentsPublish.setlatitude(dynamicPublish.getLatitude());
        momentsPublish.setLongitude(dynamicPublish.getLongitude());
        momentsPublish.setLikeNumber(0);
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
}
