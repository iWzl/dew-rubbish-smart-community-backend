package com.upuphub.dew.community.general.api.bean.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class MomentsDetailsResp {

    List<MomentContent> momentContentList;

    @Data
    public static class MomentContent{
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
        private int publishType;
        private int likeNumber;
        List<MomentComment> momentCommentList;
    }

    @Data
    public static class MomentComment{
        private Long commentId;
        private int commentType;
        private String content;
        private SimpleProfileResp commentator;
        private Long commentDate;
        List<CommentReply> commentReplyList;
    }

    @Data
    public static class CommentReply{
        private Long replyId;
        private String content;
        private SimpleProfileResp replyBy;
        private Long replyDate;
    }
}
