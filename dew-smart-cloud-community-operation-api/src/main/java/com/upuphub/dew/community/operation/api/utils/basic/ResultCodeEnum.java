package com.upuphub.dew.community.operation.api.utils.basic;

/**
 * 消息返回码的统一定义
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/27 11:06
 */
public enum ResultCodeEnum {

    //请求成功,伴随返回值
    SUCCESS(1000),
    //请求成功，没有返回值
    SUCCESS_NO_CONTENT(1001),
    //请求成功，重置内容
    SUCCESS_RESET_CONTENT(1002),

    //客户端请求的语法错误
    BAD_REQUEST(2000),
    //参数错误
    UNAUTHORIZED(2001),
    //用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR(2002),
    // 邮箱验证失败
    EMAIL_VERIFY_ERROR(2003),
    // 账号已经存在
    ACCOUNT_ALWAYS_EXISTS(2004),
    // 未知错误
    UNKNOWN(2100),
    // RPC ERROR
    RPC_SVR_ERROR_ACCOUNT(2101)
    ;



    private int code;
    ResultCodeEnum(int code){
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
}
