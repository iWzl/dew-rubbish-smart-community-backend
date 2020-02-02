package com.upuphub.dew.community.moments.bean.po;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import com.upuphub.dew.community.moments.utils.MongoKeysConst;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 动态消息的评论结构
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */

@Data
@Document(collection = "moment_comment")
public class MomentCommentPO implements Serializable {
    /**
     * 动态消息的评论ID
     */
    @Id
    @Field("id")
    @ProtobufField(ignore = true)
    private Long id;

    /**
     * 绑定动态消息的ID
     */
    @Field(MongoKeysConst.MOMENTS_DYNAMIC_ID)
    @ProtobufField("momentId")
    @Indexed
    private Long momentId;

    /**
     * 评论消息的类型 例如普通文本消息 链接消息 图片消息
     */
    @ProtobufField(ignore = true)
    @Field("content_type")
    private Integer contentType;

    /**
     * 评论消息的正文
     */
    @Field("content")
    @ProtobufField("content")
    private String content;

    /**
     * 评论消息的来源Uin
     */
    @Field("from_uin")
    @ProtobufField("commentBy")
    private Long fromUin;

    /**
     * 评论消息的创建时间
     */
    @ProtobufField(ignore = true)
    @Field("create_time")
    private Long createTime;
}
