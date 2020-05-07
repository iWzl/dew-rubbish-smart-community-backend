package com.upuphub.dew.community.message.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "message_time_line")
@CompoundIndexes({
        @CompoundIndex(name = "message_time_line", def = "{timeline_id:1,_id:1}", unique = true)
})
public class MessageDetailsPO {
    @Id
    private Long sequenceId;

    @Field("timeline_id")
    private String timelineId;

    @Indexed
    @Field("sender")
    private Long sender;

    @Indexed
    @Field("recipient")
    private Long recipient;

    @Indexed
    @Field("create_time")
    private Long createTime;

    @Field("message")
    private String message;

    @Field("status")
    private Integer status;
}
