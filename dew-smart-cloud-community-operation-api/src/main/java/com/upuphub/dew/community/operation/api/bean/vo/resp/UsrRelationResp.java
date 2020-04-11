package com.upuphub.dew.community.operation.api.bean.vo.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/8 23:20
 */

@Data
@ApiModel("用户关系详细属性信息")
public class UsrRelationResp {
    @ApiModelProperty("关系发起者的用户属性")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SimpleProfileResp sponsorProfile = null;
    @ApiModelProperty("关系接收者的用户属性")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SimpleProfileResp recipientProfile = null;
    @ApiModelProperty("关系关联类型")
    private int relationType;
    @ApiModelProperty("关系发起的渠道")
    private int relationSource;
    @ApiModelProperty("关系最后的更新时间")
    private Long refreshTime;

}
