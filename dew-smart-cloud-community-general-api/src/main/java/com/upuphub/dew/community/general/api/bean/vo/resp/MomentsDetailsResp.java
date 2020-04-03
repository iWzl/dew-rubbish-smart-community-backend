package com.upuphub.dew.community.general.api.bean.vo.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MomentsDetailsResp implements Serializable {
    private List<MomentContent> momentContentList;
    private PageInfoResp pageInfoResp;

    @Data
    @Builder
    public static class MomentContent implements Serializable{
        private long momentId;
        private SimpleProfileResp publisher;
        private SimpleProfileResp originPublisher;
        private String topic;
        private String title;
        private Integer classify;
        private String content;
        private List<String> pictures;
        private Long publishedDate;
        private Double latitude;
        private Double longitude;
        private String publishType;
        List<MomentComment> momentCommentList;
    }

    @Data
    @Builder
    public static class MomentComment implements Serializable{
        private Long commentId;
        private int commentType;
        private String content;
        private SimpleProfileResp commentator;
        private Long commentDate;
        List<CommentReply> commentReplyList;
    }

    @Data
    @Builder
    public static class CommentReply implements Serializable{
        private Long replyId;
        private String content;
        private SimpleProfileResp replyBy;
        private Long replyDate;
    }
}
