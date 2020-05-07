package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("查询消息内容")
public class PersistMessageSearchReq {
    private String timeLineId;
    private Long sequenceId;
    private boolean forward;
    private Integer size;
}
