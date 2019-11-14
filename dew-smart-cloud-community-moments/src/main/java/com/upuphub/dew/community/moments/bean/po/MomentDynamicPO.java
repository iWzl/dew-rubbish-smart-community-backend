package com.upuphub.dew.community.moments.bean.po;


import com.upuphub.dew.community.connection.annotation.ProtobufMapper;
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
@Document(collection = "dynamic")
@CompoundIndexes({
        @CompoundIndex(name = "dynamic_classify", def = "{'dynamicId': 1, 'classify': 1}"),
        @CompoundIndex(name = "founder_classify", def = "{'founder': 1, 'classify': 1}")
})
public class MomentDynamicPO implements Serializable {
    /**
     * 消息动态的ID
     */
    @Id
    @Field("id")
    private Long dynamicId;

    /**
     * 消息动态的生产者
     */
    @Field("founder")
    @Indexed
    @ProtobufMapper("uin")
    private Long founderUin;

    /**
     * 标记是否未完成
     */
    @Field("draft")
    @Indexed
    private boolean isDraft;

    /**
     * 消息动态的Topic
     */
    @Field("topic")
    @Indexed
    @ProtobufMapper("topic")
    private String topic;

    /**
     * 动态消息的分类信息
     */
    @Field("classify")
    @ProtobufMapper("dynamic")
    private Integer classify;

    /**
     * 动态消息的正文
     */
    @Field("content")
    @ProtobufMapper("dynamic")
    private String content;

    /**
     * 动态消息的图片列表
     */
    @Field("pictures")
    @ProtobufMapper("pictures")
    private List<String> pictures;

    /**
     * 动态消息的创建时间
     */
    @Field("create_time")
    @ProtobufMapper("createTime")
    private Long createTime;

    /**
     * 纬度
     */
    @Field("latitude")
    @ProtobufMapper("latitude")
    private Double latitude;

    /**
     * 经度
     */
    @Field("longitude")
    @ProtobufMapper("longitude")
    private Double longitude;


    /**
     * 动态消息的更新时间
     */
    @Field("update_time")
    private Long updateTime;
}

