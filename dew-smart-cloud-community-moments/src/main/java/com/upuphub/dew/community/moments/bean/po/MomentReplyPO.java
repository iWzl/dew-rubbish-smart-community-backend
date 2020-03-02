package com.upuphub.dew.community.moments.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 动态消息的回复结构
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */

@Data
@Document(collection = "moment_reply")
public class MomentReplyPO implements Serializable {
    /**
     * 回复消息的消息ID
     */
    @Id
    @Field("id")
    private String id;

    /**
     * 冗余存储,用户拉取回复时优化查询
     * 回复消息回复的评论消息ID
     */
    @Field("comment_id")
    @Indexed
    private String commentId;

    /**
     * 回复目标ID
     */
    @Field("reply_id")
    @Indexed
    private String replyId;

    /**
     * 回复类型 对回复回复/对评论回复
     */
    @Field("reply_type")
    private Integer replyType;

    /**
     * 回复消息的回复文字类型 例如 普通文本消息 链接消息 图片消息
     */
    @Field("content_type")
    private Integer contentType;

    /**
     * 回复消息的信息正文
     */
    @Field("content")
    private String content;

    /**
     * 回复消息的来源创建人
     */
    @Field("from_uin")
    private String fromUin;

    /**
     * 回复消息@的人
     */
    @Field("to_uin")
    private Long toUin;

    /**
     * 回复消息的回复时间
     */
    @Field("create_time")
    private Long createTime;
}
