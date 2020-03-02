package com.upuphub.dew.community.general.api.bean.vo.req;

import com.upuphub.dew.community.general.api.bean.vo.common.PageParamRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/1 16:01
 */
@Data
@ApiModel("Moment按照地理位置拉取的条件")
public class MomentLocationFilterReq {
    @ApiModelProperty("拉取Moment时可能会有的地理位置参数")
    private LocationReq locationReq;
    @Valid
    @ApiModelProperty("Moment拉取的分页条件")
    private PageParamRequest pageParam;
}
