package com.upuphub.dew.community.general.api.bean.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("支持的用户信息修改")
public class ProfileModifyReq {
  @ApiModelProperty(value = "用户头像名",example = "dsads265ds44fF64A.jpg")
  private String portrait;
  @ApiModelProperty(value = "用户名",example = "张天霸")
  private String name;
  @ApiModelProperty(value = "城市",example = "成都")
  private String city;
  @ApiModelProperty(value = "工作",example = "Java程序员")
  private String work;
  @ApiModelProperty(value = "年龄",example = "21")
  private Integer age;
  @ApiModelProperty(value = "分类宣言",example = "我的命,我说了算")
  private String content;
  @ApiModelProperty(value = "性别",notes = "数字编码 0-女 1-男",example = "0")
  private Integer gender;
}
