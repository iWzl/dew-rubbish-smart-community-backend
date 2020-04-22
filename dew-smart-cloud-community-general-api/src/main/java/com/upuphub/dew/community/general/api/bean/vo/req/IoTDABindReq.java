package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("绑定物联网硬件设备请求")
public class IoTDABindReq {
    @NotBlank(message = "需要绑定的设备的MAC地址不能为空")
    @ApiModelProperty("需要绑定的设备的MAC地址")
    private String macAddress;
    @NotBlank(message = "绑定的Key不能为空")
    @ApiModelProperty("去除非合法绑定的Key")
    private String bindKey;
    @NotBlank(message = "硬件设备的别名不能为空")
    @ApiModelProperty("硬件设备的别名")
    private String nikeName;
}
