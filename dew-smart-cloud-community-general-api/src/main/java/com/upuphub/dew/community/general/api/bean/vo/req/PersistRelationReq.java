package com.upuphub.dew.community.general.api.bean.vo.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("持久化用户关系请求")
public class PersistRelationReq {
    @Min(0)
    @ApiModelProperty("发起人UIN")
    private long sponsorUin;
    @Min(1000)
    @ApiModelProperty("接受人UIN")
    private long recipientUin;
    @ApiModelProperty("关系类型")
    @NotNull
    private RELATION_TYPE relationType;
    @ApiModelProperty("关系发起的渠道")
    @NotNull
    private RELATION_SOURCE relationSource;

    public void setRelationType(int relationType) {
        this.relationType = RELATION_TYPE.getInstance(relationType);
    }

    public void setRelationSource(int relationSource) {
        this.relationSource = RELATION_SOURCE.getInstance(relationSource);
    }

    public enum RELATION_SOURCE {
        DEFAULT(0),
        MOMENTS(1),
        SEARCH(2);

        private int type;

        RELATION_SOURCE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static RELATION_SOURCE getInstance(int value) {
            if (value >= 0) {
                switch (value) {
                    case 1:
                        return MOMENTS;
                    case 2:
                        return SEARCH;
                    default:
                        return DEFAULT;
                }
            }
            return DEFAULT;
        }
    }


    public enum RELATION_TYPE {
        NONE(0),
        BLACK(10),
        LIKE(20),
        TEMP(30),
        MATCH(40);

        private int type;

        RELATION_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static RELATION_TYPE getInstance(int value) {
            if (value >= 0) {
                switch (value) {
                    case 10:
                        return BLACK;
                    case 20:
                        return LIKE;
                    case 30:
                        return TEMP;
                    case 40:
                        return MATCH;
                    default:
                        return NONE;
                }
            }
            return NONE;
        }
    }

}
