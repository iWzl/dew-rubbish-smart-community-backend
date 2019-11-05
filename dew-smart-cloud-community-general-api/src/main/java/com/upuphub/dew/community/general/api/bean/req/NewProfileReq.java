package com.upuphub.dew.community.general.api.bean.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel("新增用户添加")
public class NewProfileReq {
  @Email
  @ApiModelProperty(value = "邮箱",required = true,example = "Test@UpUpHub.com")
  private String email;
  @NotBlank
  @ApiModelProperty(value = "邮箱验证码",required = true,example = "XAF26A")
  private String code;
  @NotBlank
  @ApiModelProperty(value = "用户头像",required = true,example = "dsads265ds44fF64A.jpg")
  private String avatar;
  @NotBlank
  @ApiModelProperty(value = "用户名",required = true,example = "张天霸")
  private String name;
  @NotNull
  @ApiModelProperty(value = "用户生日 毫秒级时间戳",example = "908073413000")
  private Long birthday;
  @NotBlank
  @Pattern(regexp = "男|女",message = "性别只能是男/女")
  @ApiModelProperty(value = "性别",notes = "男/女",example = "女")
  private String gender;
  @Min(0)
  @Max(5)
  @ApiModelProperty(value = "ID类型",notes = "EMAIL-0",example = "0")
  private Integer idType;
  @NotNull
  @ApiModelProperty(value = "用户地理位置信息",notes = "用户当前的地理位置信息")
  private LocationReq locationReq;
}
