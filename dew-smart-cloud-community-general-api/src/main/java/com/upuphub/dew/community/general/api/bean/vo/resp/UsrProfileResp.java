package com.upuphub.dew.community.general.api.bean.vo.resp;

import com.upuphub.dew.community.general.api.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:26
 */
@Data
@ApiModel("用户信息返回")
public class UsrProfileResp {
    private String name;
    private String avatar;
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;
    private Integer age;
    private Long birthday;
    private String profession;
    private String gender;
    private String signature;
    private Integer level;
    private String aboutMe;
    private String school;
    private String company;
    private String degree;
    private String language;
    private String backgroundImage;
}
