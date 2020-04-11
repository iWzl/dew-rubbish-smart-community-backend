package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/31 20:06
 */
@Data
@ApiModel(description = "用户UID")
public class UidReq {
    @ApiModelProperty(value = "用户UID",required = true,example = "123456")
    private String uid;
}
