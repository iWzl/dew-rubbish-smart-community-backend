package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/1 17:02
 */

@Data
@ApiModel("回复Moment评论请求内容")
public class MomentReplyReq {
    @ApiModelProperty("Moment回复的内容")
    private String content;
    @ApiModelProperty("回复的评论ID")
    private Long commentId;

}
