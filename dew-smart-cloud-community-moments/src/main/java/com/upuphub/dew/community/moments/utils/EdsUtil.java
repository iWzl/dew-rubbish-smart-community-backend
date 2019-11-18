package com.upuphub.dew.community.moments.utils;

import com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;

public class EdsUtil {
    public static MomentDynamicContent toProtobufMessage(MomentDynamicPO momentDynamicPO) {
        return MomentDynamicContent.newBuilder()
                .setUin(momentDynamicPO.getFounderUin())
                .setClassify(momentDynamicPO.getClassify())
                .setDynamic(momentDynamicPO.getContent())
                .setTopic(momentDynamicPO.getTopic())
                .setLongitude(momentDynamicPO.getLongitude())
                .setLatitude(momentDynamicPO.getLatitude())
                .addAllPictures(momentDynamicPO.getPictures())
                .setUpdateTime(momentDynamicPO.getUpdateTime())
                .build();
    }
}
