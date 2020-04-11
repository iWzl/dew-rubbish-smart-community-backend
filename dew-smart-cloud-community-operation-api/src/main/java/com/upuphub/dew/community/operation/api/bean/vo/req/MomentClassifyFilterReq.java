package com.upuphub.dew.community.operation.api.bean.vo.req;


import com.upuphub.dew.community.operation.api.bean.vo.common.PageParamRequest;
import lombok.Data;

@Data
public class MomentClassifyFilterReq {
    private int classify;
    private PageParamRequest pageParam;
}
