package com.upuphub.dew.community.moments.bean.po;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
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
    @ProtobufField(ignore = true)
    private Long id;

    /**
     * 回复消息回复的评论消息ID
     */
    @Field("comment_id")
    @Indexed
    @ProtobufField("commentId")
    private Long commentId;

    /**
     * 回复消息的信息正文
     */
    @Field("content")
    @ProtobufField("content")
    private String content;

    /**
     * 回复消息的来源创建人
     */
    @Field("from_uin")
    @ProtobufField("replyBy")
    private Long fromUin;

    /**
     * 回复消息的回复时间
     */
    @Field("create_time")
    @ProtobufField(ignore = true)
    private Long createTime;
}
