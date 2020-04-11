package com.upuphub.dew.community.operation.api.bean.vo.resp;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户News信息返回的信息载体")
public class NewsDetailsResp {
    @ApiModelProperty("返回的News信息详细数据")
    private List<NewsDetail> newsDetailList;

    @Data
    @ApiModel("用户News信息的详细返回数据")
    public static class NewsDetail{
        @ProtobufField
        @ApiModelProperty("NewsId")
        private Long newsId;

        @ProtobufField
        @ApiModelProperty("新闻的标题")
        private String title;

        @ProtobufField
        @ApiModelProperty("新闻的创建时间戳")
        private Long createDate;

        @ProtobufField
        @ApiModelProperty("新闻的分类属性")
        private String category;

        @ProtobufField(ignore = true)
        @ApiModelProperty("新闻的作者信息")
        private SimpleProfileResp authorProfile;

        @ProtobufField(ignore = true)
        @ApiModelProperty("新闻的标识类型")
        private Integer newsType;

        @ProtobufField(ignore = true)
        @ApiModelProperty("新闻正文类型")
        private Integer payloadType;

        @ProtobufField
        @ApiModelProperty("新闻正文")
        private String payload;

        @ProtobufField(ignore = true)
        @ApiModelProperty("新闻的封面图片组合")
        private List<String> frontCoverImages;
    }
}
