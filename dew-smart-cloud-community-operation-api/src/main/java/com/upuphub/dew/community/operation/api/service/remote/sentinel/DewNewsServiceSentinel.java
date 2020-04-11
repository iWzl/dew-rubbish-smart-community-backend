package com.upuphub.dew.community.operation.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.operation.api.service.remote.DewNewsService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:41
 */
@Component
public class DewNewsServiceSentinel implements DewNewsService {
    @Override
    public NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest) {
        return null;
    }

    @Override
    public RpcResultCode buildNewsDetail(NewsDetailRequest newsDetailRequest) {
        return null;
    }

    @Override
    public RpcResultCode modifyNewsDetail(NewsDetailModifyRequest newsDetailModifyRequest) {
        return null;
    }

    @Override
    public RpcResultCode deleteNewsDetail(NewsIdRequest newsIdRequest) {
        return null;
    }

    @Override
    public NewsDetailsResult fetchNewsDetailByNewsId(NewsIdRequest newsIdRequest) {
        return null;
    }
}
