package com.upuphub.dew.community.moments.bean.po;


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
    private String dynamicId;

    /**
     * 消息动态的产生者
     */
    @Field("founder")
    @Indexed
    private Long founderUin;

    /**
     * 消息动态的Topic
     */
    @Field("topic")
    @Indexed
    private String topic;

    /**
     * 动态消息的分类信息
     */
    @Field("classify")
    private Integer classify;

    /**
     * 动态消息的正文
     */
    @Field("content")
    private String content;

    /**
     * 动态消息的图片列表
     */
    @Field("pics")
    private List<String> picList;

    /**
     * 动态消息的创建时间
     */
    @Field("create_time")
    private Long createTime;

    /**
     * 动态消息的更新时间
     */
    @Field("update_time")
    private Long updateTime;
}

