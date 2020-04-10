package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.general.api.service.remote.DewNewsService;
import org.springframework.stereotype.Component;

@Component
public class DewNewsSentinel implements DewNewsService {
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
