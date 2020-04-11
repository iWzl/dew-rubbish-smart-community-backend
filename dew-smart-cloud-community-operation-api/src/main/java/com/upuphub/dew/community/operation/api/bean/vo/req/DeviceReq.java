package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/30 20:08
 */
@Data
@ApiModel(value = "DeviceReq",description = "登录设备信息")
public class DeviceReq {
    @NotBlank
    @ApiModelProperty(value = "设备IMEI号",required = true,example = "EC693E8E-E674-45B9-8120-BF260D9A45A6")
    private String imei;
    @NotBlank
    @ApiModelProperty(value = "设备型号",required = true,example = "Huawei-Mate20pro")
    private String systemModel;
    @NotBlank
    @ApiModelProperty(value = "APP版本信息",required = true,example = "beta.1.3.28.15561")
    private String appVersion;
    @NotBlank
    @ApiModelProperty(value = "设备名称",required = true,example = "小张的HUAWEI")
    private String devName;
    @NotBlank
    @ApiModelProperty(value = "系统版本",required = true,example = "Android 9.1.1")
    private String systemVersion;
    @NotBlank
    @Pattern(regexp = "Android|iOS|Web",message = "只能是Android|iOS|Web")
    @ApiModelProperty(value = "平台信息Android/Web/iOS",required = true,example = "Android")
    private String platform;
}
