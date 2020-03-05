package com.upuphub.dew.community.general.api.bean.vo.req;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leo Wang
 * @version 1.0
 */

@Data
public class MomentTopicFilterReq {
    private String topic;
    private PageRequest pageRequest;

}
