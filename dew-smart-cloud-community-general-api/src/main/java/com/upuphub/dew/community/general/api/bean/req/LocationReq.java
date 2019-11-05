package com.upuphub.dew.community.general.api.bean.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/1 20:35
 */
@Data
@ApiModel(description = "位置")
public class LocationReq {
    @ApiModelProperty(value = "街道",required = true,example = "天才大道")
    private String street;
    @ApiModelProperty(value = "县/区",required = true,example = "高新区")
    private String district;
    @ApiModelProperty(value = "城市",required = true,example = "成都")
    private String city;
    @ApiModelProperty(value = "省份",required = true,example = "四川")
    private String province;
    @ApiModelProperty(value = "国家",required = true,example = "中国")
    private String country;
    @ApiModelProperty(value = "经度",required = true,example = "321.2233")
    private Double longitude;
    @ApiModelProperty(value = "纬度",required = true,example = "321.2233")
    private Double latitude;
}
