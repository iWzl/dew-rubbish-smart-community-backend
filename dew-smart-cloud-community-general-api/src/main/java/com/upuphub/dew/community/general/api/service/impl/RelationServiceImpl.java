package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.constant.RelationConst;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;
import com.upuphub.dew.community.general.api.service.RelationService;
import com.upuphub.dew.community.general.api.service.remote.DewRelationService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    DewRelationService dewRelationService;

    @Override
    public ServiceResponseMessage persistRelation(PersistRelationReq persistRelationReq) {
        RelationPersistRequest relationPersistRequest = EDSUtil.toProtobufMessage(persistRelationReq);
        int error = dewRelationService.persistRelation(relationPersistRequest).getCode();
        if(error == RelationConst.ERROR_CODE_SUCCESS){
            return ServiceResponseMessage.createBySuccessCodeMessage();
        }else {
            return ServiceResponseMessage.createByFailCodeMessage("Persist Relation Error");
        }
    }
}
