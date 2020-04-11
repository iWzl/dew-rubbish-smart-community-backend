package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

@Data
@ApiModel("修稿NewsDetails信息")
public class NewsDetailModifyReq {
    @Min(1000)
    private Long newsId;
    private String title;
    private String category;
    private Integer newsType;
    private String payload;
    private List<String> frontCoverImages;
}
