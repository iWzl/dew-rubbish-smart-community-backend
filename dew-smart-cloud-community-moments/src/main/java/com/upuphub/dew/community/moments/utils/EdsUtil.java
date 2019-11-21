package com.upuphub.dew.community.moments.utils;

import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicPublish;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;

/**
 * @author Leo Wang
 */
public class EdsUtil {
    public static MomentDynamicContent toProtobufMessage(MomentDynamicPO momentDynamic) {
        return MomentDynamicContent.newBuilder()
                .setUin(momentDynamic.getFounderUin())
                .setClassify(momentDynamic.getClassify())
                .setDynamic(momentDynamic.getContent())
                .setTopic(momentDynamic.getTopic())
                .setLongitude(momentDynamic.getLongitude())
                .setLatitude(momentDynamic.getLatitude())
                .addAllPictures(momentDynamic.getPictures())
                .setUpdateTime(momentDynamic.getUpdateTime())
                .setDynamicContentId(momentDynamic.getDynamicId())
                .build();
    }

    public static MomentsPublishPO toCommonBean(long dynamicId, MomentDynamicPublish dynamicPublish) {
        MomentsPublishPO momentsPublish = new MomentsPublishPO();
        momentsPublish.setDynamicId(dynamicId);
        momentsPublish.setGeohash(GeoHashUtil.buildGeoHash(dynamicPublish.getLatitude(),dynamicPublish.getLongitude()));
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
}
