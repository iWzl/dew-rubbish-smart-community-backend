package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("新News详细请求")
public class NewsDetailReq {
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "分类标识不能为空")
    private String category;
    @Min(value = 0,message = "NewsType COMMON 0 BANNER")
    @Max(value = 1,message = "NewsType COMMON 0 BANNER")
    private Integer newsType;
    @Min(value = 1,message = "TEXT = 1 URL = 2 MARKDOWN = 3 HTML = 4")
    @Max(value = 4,message = "TEXT = 1 URL = 2 MARKDOWN = 3 HTML = 4")
    private Integer payloadType;
    @NotBlank(message = "正文不能为空")
    private String payload;
    @NotNull
    private List<String> frontCoverImages;
}
