package com.upuphub.dew.community.operation.api.bean.vo.resp;

import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:34
 */
@Data
public class UsrStatusFlagResp {
    boolean emailVerifiedFlag;
    boolean disableFlag;
    boolean needMoreInfoFlag;
}
