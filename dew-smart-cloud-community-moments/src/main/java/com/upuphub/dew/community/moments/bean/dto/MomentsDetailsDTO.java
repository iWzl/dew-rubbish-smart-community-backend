package com.upuphub.dew.community.moments.bean.dto;
import com.upuphub.dew.community.connection.protobuf.moments.MomentContentDetailResult;
import com.upuphub.dew.community.connection.protobuf.moments.PageInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MomentsDetailsDTO {
    private List<MomentContentDetailResult> momentCommentDetailResults;
    private PageInfo pageInfo;
}
