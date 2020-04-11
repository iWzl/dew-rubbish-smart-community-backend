package com.upuphub.dew.community.operation.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/1/30 13:35
 */

@Data
@ApiModel("用户Moment发布信息")
public class MomentsPublishReq {
    @ApiModelProperty("需要发布的MomentId")
    private long momentId;
    @ApiModelProperty("Moment发布时的经度")
    private double longitude;
    @ApiModelProperty("Moment发布时的纬度")
    private double latitude;
    @ApiModelProperty("Moment的发布类型 0:默认 1：原创 2：转发")
    private PUBLISH_TYPE publishType;

    public void setPublishType(int publishType) {
        this.publishType = PUBLISH_TYPE.buildPublishTypeByTypeNumber(publishType);
    }

    public static enum PUBLISH_TYPE{
        /**
         * 普通发布,未声明原创
         */
        ORDINARY(0),

        /**
         * 原创发布
         */
        ORIGINAL(1),

        /**
         * 转发发表
         */
        REPRINT(2);
        private final int type;

        PUBLISH_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static PUBLISH_TYPE buildPublishTypeByTypeNumber(int typeNumber){
            if(typeNumber > REPRINT.getType() || typeNumber < ORDINARY.getType()) {
                return ORDINARY;
            }
            for (PUBLISH_TYPE type :values()){
                if(typeNumber == type.getType()){
                    return type;
                }
            }
            return ORDINARY;
        }
    }
}
