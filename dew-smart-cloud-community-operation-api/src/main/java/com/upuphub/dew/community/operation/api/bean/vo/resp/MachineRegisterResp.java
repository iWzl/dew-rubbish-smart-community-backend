package com.upuphub.dew.community.operation.api.bean.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:56
 */

@Data
@ApiModel("机器注册请求返回")
public class MachineRegisterResp {
    @ApiModelProperty("绑定的Key")
    private String bindKey;

    public MachineRegisterResp(String bindKey) {
        this.bindKey = bindKey;
    }
}
