package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/25 15:32
 */

@Data
@ApiModel("时间筛选范围")
public class DateRangeReq {
    @Min(value = 1,message = "时间筛选范围的开始时间不可为空")
    @ApiModelProperty("时间筛选范围的开始时间")
    private Long startTime;
    @Min(value = 1,message = "时间筛选时间的结束时间不可为空")
    @ApiModelProperty("时间筛选时间的结束时间")
    private Long endTime;
}
