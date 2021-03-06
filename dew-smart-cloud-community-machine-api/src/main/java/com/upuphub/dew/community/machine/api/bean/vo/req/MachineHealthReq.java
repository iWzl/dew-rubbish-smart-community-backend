package com.upuphub.dew.community.machine.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 13:07
 */

@Data
@ApiModel("设备健康上报")
public class MachineHealthReq {
    @ApiModelProperty("CPU温度")
    private double cpuTemperature;
    @ApiModelProperty("CPU使用率")
    private double cpuUsageRate;
    @ApiModelProperty("内存大小")
    private Integer memorySize;
    @ApiModelProperty("已经使用的内存大小")
    private Integer usedMemorySize;
    @ApiModelProperty("已经使用的内存大小")
    private Integer freeMemorySize;
    @ApiModelProperty("硬盘大小")
    private String hardDiskSize;
    @ApiModelProperty("已经使用的硬盘大小")
    private String usedHardDiskSize;
    @ApiModelProperty("已经使用的硬盘大小")
    private String freeDiskSize;
    @ApiModelProperty("已经使用的硬盘大小")
    private String diskUseRate;
    @ApiModelProperty("CPU核心数量")
    private int cpuCoreCount;
    @ApiModelProperty("系统属性描述")
    private String systemName;
}
