package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/30 20:03
 */
@Data
@ApiModel(value = "UserAndPassword",description = "用户名和密码")
public class UsernameAndPasswordReq {

    @NotBlank(message="用户名不能为空")
    @Length(min = 4,message = "用户名大于4位")
    @ApiModelProperty(value = "用户输入账户名",required = true,example = "SystemAdmin@upuphub.com")
    private String userName;

    @NotBlank(message="用户名不能为空")
    @Length(min = 6,max = 16,message = "密码长度为6-16位")
    @ApiModelProperty(value = "用户密码",required = true,example = "123456")
    private String password;

    @ApiModelProperty(value = "是否是注册",required = true,example = "false")
    private boolean register;

    @Max(value = 4,message = "ID类型[0-4]")
    @Min(value = 0,message = "ID类型[0-4]")
    @ApiModelProperty(value = "Id类型[0-EMAIL/1-phone/2-QQ/3-微博/4-Other(保留)]",required = true,example = "0")
    private int idType;

    @NotNull
    @ApiModelProperty(value = "设备信息",required = true)
    @Valid
    private DeviceReq deviceInfo;

}
