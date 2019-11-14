package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(description = "桶名称/文件Key")
public class QiNiuTokenReq {
    @NotBlank(message="桶名称不能为空")
    @ApiModelProperty(value = "桶名称",required = true,example = "dew")
    private String bucketName;
    @ApiModelProperty(value = "文件Key",required = true,example = "[\"fileKey1\",\"fileKey2\"]")
    @NotNull(message = "文件Key不能为空")
    private List<String> fileKeys;
}
