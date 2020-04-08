package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.constant.RelationConst;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistResult;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistResults;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchUinRequest;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;
import com.upuphub.dew.community.general.api.bean.vo.resp.MatchRelationResp;
import com.upuphub.dew.community.general.api.bean.vo.resp.UsrRelationResp;
import com.upuphub.dew.community.general.api.service.AccountService;
import com.upuphub.dew.community.general.api.service.RelationService;
import com.upuphub.dew.community.general.api.service.remote.DewRelationService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    DewRelationService dewRelationService;
    @Autowired
    AccountService accountService;

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

    @Override
    public ServiceResponseMessage fetchPersistMatchRelation(Long uin) {
        RelationSearchUinRequest relationSearchUinRequest = RelationSearchUinRequest.newBuilder().setUin(uin).build();
        RelationPersistResults relationPersistResults = dewRelationService.fetchMatchRelationPersistResults(relationSearchUinRequest);
        MatchRelationResp matchRelationResp = new MatchRelationResp();
        if(null != relationPersistResults && !relationPersistResults.getRelationPersistResultsList().isEmpty()){
            List<UsrRelationResp> usrRelationRespList = new ArrayList<>(relationPersistResults.getRelationPersistResultsCount());
            for (RelationPersistResult relationPersistResult : relationPersistResults.getRelationPersistResultsList()) {
                UsrRelationResp usrRelationResp = new UsrRelationResp();
                if(relationPersistResult.getSponsor() != uin){
                    usrRelationResp.setRecipientProfile(accountService.pullSimpleProfileByUin(relationPersistResult.getSponsor()));
                }else {
                    usrRelationResp.setRecipientProfile(accountService.pullSimpleProfileByUin(relationPersistResult.getRecipient()));
                }
                usrRelationResp.setRefreshTime(relationPersistResult.getRefreshTime());
                usrRelationResp.setRelationSource(relationPersistResult.getRelationSource());
                usrRelationResp.setRelationType(relationPersistResult.getRelationType());
                usrRelationRespList.add(usrRelationResp);
            }
            matchRelationResp.setUsrMatchRelationRespList(usrRelationRespList);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功",matchRelationResp);
    }
}
