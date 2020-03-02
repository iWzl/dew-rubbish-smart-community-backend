package com.upuphub.dew.community.moments.bean.po;


import com.upuphub.dew.community.connection.annotation.ProtobufField;
import com.upuphub.dew.community.moments.utils.MongoKeysConst;
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
@Document(collection = "moment_content")
@CompoundIndexes({
        @CompoundIndex(name = "moment_classify", def = "{'moment_id': 1, 'classify': 1}"),
        @CompoundIndex(name = "founder_classify", def = "{'founder': 1, 'classify': 1}")
})
public class MomentDynamicPO implements Serializable {
    /**
     * 消息动态的ID
     */
    @Id
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_ID)
    @ProtobufField(ignore = true)
    private Long momentId;

    /**
     * 消息动态的生产者
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER)
    @Indexed
    @ProtobufField("uin")
    private Long founder;

    /**
     * 标记是否未完成
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT)
    @Indexed
    @ProtobufField(ignore = true)
    private boolean isDraft;

    /**
     * 消息动态的Topic
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_TOPIC)
    @Indexed
    @ProtobufField("topic")
    private String topic;


    /**
     * 消息动态的Topic
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_TITLE)
    @Indexed
    @ProtobufField("title")
    private String title;

    /**
     * 动态消息的分类信息
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_CLASSIFY)
    @ProtobufField("classify")
    private Integer classify;

    /**
     * 动态消息的正文
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_CONTENT)
    @ProtobufField("content")
    private String content;

    /**
     * 动态消息的图片列表
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_PICTURES)
    @ProtobufField(ignore = true)
    private List<String> pictures;

    /**
     * 动态消息的创建时间
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_CREATE_TIME)
    @ProtobufField(ignore = true)
    private Long createTime;

    /**
     * 纬度
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_LATITUDE)
    @ProtobufField("latitude")
    private Double latitude;

    /**
     * 经度
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_LONGITUDE)
    @ProtobufField("longitude")
    private Double longitude;


    /**
     * 动态消息的更新时间
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_UPDATE_TIME)
    @ProtobufField(ignore = true)
    private Long updateTime;
}

