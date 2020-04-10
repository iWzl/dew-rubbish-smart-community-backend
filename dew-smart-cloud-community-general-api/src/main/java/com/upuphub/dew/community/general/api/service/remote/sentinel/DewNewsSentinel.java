package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResults;
import com.upuphub.dew.community.connection.protobuf.news.NewsSyncWithPageSizeRequest;
import com.upuphub.dew.community.general.api.service.remote.DewNewsService;
import org.springframework.stereotype.Component;

@Component
public class DewNewsSentinel implements DewNewsService {
    @Override
    public NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest) {
        return null;
    }
}
