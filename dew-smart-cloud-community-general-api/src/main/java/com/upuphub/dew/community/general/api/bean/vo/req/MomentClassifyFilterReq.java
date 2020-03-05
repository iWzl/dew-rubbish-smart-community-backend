package com.upuphub.dew.community.general.api.bean.vo.req;


import com.upuphub.dew.community.general.api.bean.vo.common.PageParamRequest;
import lombok.Data;

@Data
public class MomentClassifyFilterReq {
    private int classify;
    private PageParamRequest pageParam;
}
