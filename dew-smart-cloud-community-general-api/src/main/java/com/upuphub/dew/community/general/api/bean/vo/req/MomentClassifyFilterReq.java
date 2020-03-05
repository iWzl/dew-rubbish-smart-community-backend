package com.upuphub.dew.community.general.api.bean.vo.req;


import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class MomentClassifyFilterReq {
    private int classify;
    private PageRequest pageRequest;
}
