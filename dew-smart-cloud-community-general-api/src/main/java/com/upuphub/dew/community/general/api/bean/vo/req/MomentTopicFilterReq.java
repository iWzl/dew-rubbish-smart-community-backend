package com.upuphub.dew.community.general.api.bean.vo.req;

import com.upuphub.dew.community.general.api.bean.vo.common.PageParamRequest;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 */

@Data
public class MomentTopicFilterReq {
    private String topic;
    private PageParamRequest pageParam;

}
