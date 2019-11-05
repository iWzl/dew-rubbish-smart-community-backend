package com.upuphub.dew.community.general.api.bean.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/31 21:03
 */
@Data
@ApiModel("用户忘记密码携带参数")
public class PasswordReq {
    @NotBlank(message = "邮箱验证码不能为空")
    @ApiModelProperty(value = "邮箱验证吗",required = true,example = "EDS8TA")
    private String code;
    @Email(message = "需要是正确的邮箱格式")
    @ApiModelProperty(value = "用户邮箱",required = true,example = "用户邮箱")
    private String email;
    @NotBlank(message = "用户需改修改的成的新密码")
    @ApiModelProperty(value = "用户新密码",required = true,example = "NewPassword")
    private String password;
}
