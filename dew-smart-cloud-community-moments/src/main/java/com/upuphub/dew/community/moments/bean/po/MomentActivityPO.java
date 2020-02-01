package com.upuphub.dew.community.moments.bean.po;


import com.upuphub.dew.community.connection.annotation.ProtobufField;
import com.upuphub.dew.community.moments.utils.MongoKeysConst;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * 动态消息的消息正文信息
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */


@Data
@Builder
@Document(collection = "moment_activity")
public class MomentActivityPO implements Serializable {
    /**
     * 消息ACTIVITY的ID
     */
    @Id
    @Field(MongoKeysConst.MOMENTS_ACTIVITY_ID)
    @ProtobufField(ignore = true)
    private Long activityId;

    /**
     * ACTIVITY绑定的UIN
     */
    @Field(MongoKeysConst.MOMENTS_ACTIVITY_FOR)
    @Indexed
    @ProtobufField("forUin")
    private Long forUin;

    /**
     * ACTIVITY绑定的UIN
     */
    @Field(MongoKeysConst.MOMENTS_ACTIVITY_BY)
    @Indexed
    @ProtobufField("byUin")
    private Long byUin;

    /**
     * ACTIVITY是否完成同步的标识
     */
    @Field(MongoKeysConst.MOMENTS_ACTIVITY_SYNC)
    @Indexed
    private boolean isSync;

    /**
     * 与ACTIVITY关联的MOMENTS
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_ID)
    @ProtobufField("momentId")
    private Long momentId;

    /**
     * 与ACTIVITY关联的MOMENTS
     */
    @Field(MongoKeysConst.MOMENTS_ACTIVITY_TYPE)
    @ProtobufField("activityType")
    private int activityTypeNumber ;

    /**
     * 动态消息的更新时间
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_UPDATE_TIME)
    @ProtobufField(ignore = true)
    private Long updateTime;
}

