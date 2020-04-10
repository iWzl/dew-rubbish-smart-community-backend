package com.upuphub.dew.community.news.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "news_repository")
public class NewsDetailPO {
    @Id
    private Long newsId;

    @Field("title")
    private String title;

    @Field("create_date")
    private Long createDate;

    @Field("category")
    private String category;

    @Field("create_by")
    private Long createBy;

    @Field("news_type")
    private Integer newsType;

    @Field("payload_type")
    private Integer payloadType;

    @Field("payload")
    private String payload;

    @Field("front_cover_images")
    private List<String> frontCoverImages;
}
