package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:45
 */

@Data
@ApiModel("硬件设备的注册请求")
public class MachineRegisterReq {
    @NotBlank(message = "Mac地址不能为空")
    @ApiModelProperty("机器的Mac地址")
    private String machineMacAddress;
    @ApiModelProperty("机器名字")
    private String machineName;
    @ApiModelProperty("机器的类型标识")
    private Integer machineType;
    @ApiModelProperty("机器的版本信息")
    private String machineVersion;
    @ApiModelProperty("机器制造者信息")
    private String machineMaker;
}
