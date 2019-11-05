package com.upuphub.dew.community.account.bean.po;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Map;

@Data
@Document(collection = "profile")
@CompoundIndexes({
        @CompoundIndex(name = "uin_profile", def = "{'uin': 1, 'profile': 1}"),
})
public class ProfilePO implements Serializable {
    @Id
    @Field("uin")
    private String uin;

    /* 公有Profile */
    @Field("profile")
    private Map<String,Object> profile;

    //创建时间
    @Field("create_time")
    private Long createTime;

    //更新时间
    @Field("update_time")
    private Long updateTime;
}
