package com.upuphub.dew.community.operation.api.bean.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/8 23:20
 */
@Data
@ApiModel("用户Match上的关系列表返回")
public class MatchRelationResp {
    @ApiModelProperty("用户Match上的关系列表")
    private List<UsrRelationResp> usrMatchRelationRespList;
}
