package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(description = "用户瞬间的动态创建正文")
public class MomentDynamicContentReq {
    @ApiModelProperty(value = "用户文章编辑的经度",example = "200.111")
    private double longitude;
    @ApiModelProperty(value = "用户文章编辑的纬度",example = "200.112")
    private double latitude;

    @NotBlank(message = "用户需要发送的Title不允许为空")
    @ApiModelProperty(value = "用户发送的消息Title",required = true,example = "青青子衿,悠悠我心")
    private String title;

    @NotBlank(message = "用户需要发动的动态消息正文不允许为空")
    @ApiModelProperty(value = "用户发送的动态消息正文",required = true,example = "青青子衿,悠悠我心")
    private String content;

    @ApiModelProperty(value = "用户上传的图片的")
    private List<String> pictures;

    @ApiModelProperty(value = "用户动态参与的话题",example = "#垃圾分类")
    private String topic;

    @Min(value = 0,message = "用户所属的话题分类必选-且有效")
    @ApiModelProperty(value = "用户动态所属的话题分类",example = "类别待定")
    private Integer classify;

}
