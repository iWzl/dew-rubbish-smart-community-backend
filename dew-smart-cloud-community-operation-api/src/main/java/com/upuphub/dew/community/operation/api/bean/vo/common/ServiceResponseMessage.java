package com.upuphub.dew.community.operation.api.bean.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upuphub.dew.community.operation.api.utils.basic.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 消息的统一回复类,提供消息回复统一模板
 * 项目使用JSON,通过这种方式，构造通用的JSON返回包装类
 *
 * @author Leo Wang
 */
@ApiModel(value = "JsonMessage",description = "响应数据的统一返回")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseMessage implements Serializable {
    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @ApiModelProperty(value = "返回信息的元数据")
    private Meta meta;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "返回信息携带的数据")
    private Object data;

    /**
     * 隐藏构造器,规范化项目操作，通过内部的静态方法统一创建管理
     * @author Leo Wang
     */
    private ServiceResponseMessage(){
    }

    public static ServiceResponseMessage createBySuccessCodeMessage(){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS_NO_CONTENT.getCode(),"SUCCESS");
        return serviceResponseMessage;
    }

    public static ServiceResponseMessage createBySuccessCodeMessage(String msg){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS_NO_CONTENT.getCode(),msg);
        return serviceResponseMessage;
    }

    public static <T> ServiceResponseMessage createBySuccessCodeMessage(int resultCode, T data){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(resultCode,"SUCCESS");
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }

    public static <T> ServiceResponseMessage createBySuccessCodeMessage(T data){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS.getCode(),"SUCCESS");
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }


    public static  <T> ServiceResponseMessage createBySuccessCodeMessage(String msg, T data){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.SUCCESS.getCode(),msg);
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }


    public static ServiceResponseMessage createByFailCodeMessage(String msg){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(ResultCodeEnum.BAD_REQUEST.getCode(),msg);
        return serviceResponseMessage;
    }

    public static ServiceResponseMessage createByFailCodeMessage(int resultCode, String msg){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(resultCode,msg);
        return serviceResponseMessage;
    }


    public static <T> ServiceResponseMessage createGeneralMessage(int resultCode, String msg, T data){
        ServiceResponseMessage serviceResponseMessage = new ServiceResponseMessage();
        serviceResponseMessage.meta = new Meta(resultCode,msg);
        serviceResponseMessage.data = data;
        return serviceResponseMessage;
    }



    /**
     * 消息返回的元数据
     * 包含消息的返回码和提示信息
     *
     * @author Leo Wang
     */
    @ApiModel(value = "Meta",description = "消息返回的元数据")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Meta{
        @JsonProperty
        @ApiModelProperty(value = "消息的返回码",example = "200")
        private int code;
        @JsonProperty
        @ApiModelProperty(value = "返回信息的说明",example = "SUCCESS")
        private String msg;

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        private String debugMsg;

        private Meta(int resultCode, String message) {
            this.code = resultCode;
            this.msg = message;
        }
    }
}
