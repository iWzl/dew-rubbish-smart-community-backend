package com.upuphub.dew.community.general.api.bean.vo.req;

import com.upuphub.dew.community.general.api.bean.vo.common.PageParamRequest;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/1 16:22
 */

@Data
public class MomentUinFilterReq {
    private Long uin;
    private PageParamRequest pageParamRequest;
}
