package com.upuphub.dew.community.operation.api.bean.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/1/30 12:47
 */

@Builder
@Getter
@Setter
@ApiModel("Moment Id的标识返回")
public class MomentIdResp {
    @JsonProperty
    @ApiModelProperty("MomentId")
    private Long momentId;
}
