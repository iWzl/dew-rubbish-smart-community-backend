package com.upuphub.dew.community.operation.api.bean.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/1 16:04
 */

@Data
@ApiModel("拉取查询条件的分页参数属性信息")
public class PageParamRequest implements Serializable {
    @Min(1)
    @ApiModelProperty(value = "每页的数量",required = true)
    private int pageSize;
    @Min(1)
    @ApiModelProperty(value = "当前的页码数",required = true)
    private int pageNum;
    @ApiModelProperty(value = "分页的限制可能会使用的限制Key",required = false)
    private String limitKey;
}
