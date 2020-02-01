package com.upuphub.dew.community.general.api.bean.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/2/1 16:41
 */

@Data
@ApiModel("Moment评论")
public class MomentCommentReq {
    @ApiModelProperty("MomentId")
    private Long momentId;
    @ApiModelProperty("评论的正文")
    private String content;
    @ApiModelProperty("评论的类型:普通评论/点赞评论")
    private COMMENT_TYPE commentType;

    public void setCommentType(int commentType) {
        this.commentType = COMMENT_TYPE.buildCommentTypeByTypeNumber(commentType);
    }

    public static enum COMMENT_TYPE{
        /**
         * 点赞
         */
        FAVORITE(1),

        /**
         * 评论
         */
        REPLY(2);
        private final int type;

        COMMENT_TYPE(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static COMMENT_TYPE buildCommentTypeByTypeNumber(int typeNumber){
            if(typeNumber > FAVORITE.getType() || typeNumber < REPLY.getType()) {
                return REPLY;
            }
            for (COMMENT_TYPE type :values()){
                if(typeNumber == type.getType()){
                    return type;
                }
            }
            return REPLY;
        }
    }
}
