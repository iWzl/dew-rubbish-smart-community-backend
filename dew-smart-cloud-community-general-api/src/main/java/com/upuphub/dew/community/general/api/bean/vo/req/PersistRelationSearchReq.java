package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ApiModel("指定关系的查询条件")
public class PersistRelationSearchReq {
    @ApiModelProperty("指定关系类型")
    private List<RELATION_TYPE> relationTypeList;

    @ApiModelProperty("查询正向好友关系还是反向好友关系")
    private boolean isReverse;

    public void setRelationTypeList(List<Integer> relationTypeList) {
        this.relationTypeList = relationTypeList.stream().map(RELATION_TYPE::getInstance).collect(Collectors.toList());
    }

    public enum RELATION_TYPE {
        NONE(0),
        BLACK(10),
        LIKE(20),
        TEMP(30),
        MATCH(40);

        private final int type;

        RELATION_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static PersistRelationSearchReq.RELATION_TYPE getInstance(int value) {
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
