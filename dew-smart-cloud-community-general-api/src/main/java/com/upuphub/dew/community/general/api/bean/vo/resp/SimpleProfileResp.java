package com.upuphub.dew.community.general.api.bean.vo.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/31 20:14
 */

@Data
@ApiModel("基本用户信息返回")
public class SimpleProfileResp implements Serializable {
    private String openId;
    private String name;
    private String avatar;
    private String signature;
    private String country;
    private String province;
    private String city;
    private Integer age;
}

