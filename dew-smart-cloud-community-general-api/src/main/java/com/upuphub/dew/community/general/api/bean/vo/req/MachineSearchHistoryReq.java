package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel("时间筛选范围")
public class MachineSearchHistoryReq {
    @ApiModelProperty(value = "需要检索的设备Mac地址 可选",required = false)
    private String macAddress;
    @Min(value = 1,message = "开始的时间不允许为空")
    @ApiModelProperty(value = "开始的时间 毫秒",required = true)
    private Long startTime;
    @Min(value = 1,message = "结束的时间不允许为空")
    @ApiModelProperty(value = "结束的时间 毫秒",required = true)
    private Long endTime;
}
