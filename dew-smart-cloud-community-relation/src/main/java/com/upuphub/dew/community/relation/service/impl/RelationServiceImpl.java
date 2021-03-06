package com.upuphub.dew.community.relation.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.constant.RelationConst;
import com.upuphub.dew.community.connection.protobuf.relation.RELATION_TYPE;
import com.upuphub.dew.community.connection.protobuf.relation.RelationMqttMessage;
import com.upuphub.dew.community.relation.bean.dto.RelationPersistResultDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationRequestDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationSearchDTO;
import com.upuphub.dew.community.relation.bean.po.RelationDetailPO;
import com.upuphub.dew.community.relation.dao.RelationPositiveDao;
import com.upuphub.dew.community.relation.service.MqttSenderService;
import com.upuphub.dew.community.relation.service.RelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Resource
    RelationPositiveDao relationPositiveDao;
    @Resource
    MqttSenderService mqttSenderService;

    @Override
    @Transactional()
    public int persistRelation(RelationRequestDTO relationRequest) {
        RelationDetailPO historyPositiveRelationDetail =
                relationPositiveDao.selectHistoryRelationPositiveDetailByUin(relationRequest.getSponsor(), relationRequest.getRecipient());
        RelationDetailPO historyReverseRelationDetail =
                relationPositiveDao.selectHistoryRelationReverseDetailByUin(relationRequest.getSponsor(), relationRequest.getRecipient());
        if (null == historyPositiveRelationDetail && null == historyReverseRelationDetail) {
            RelationDetailPO relationPositiveDetail = new RelationDetailPO();
            BeanUtils.copyProperties(relationRequest, relationPositiveDetail);
            relationPositiveDao.insertNewRelation(relationPositiveDetail);
            fireRelationChange(relationRequest.getSponsor(),relationRequest.getRecipient(),relationRequest.getRelationType(),relationRequest.getRelationSource());
        } else if (null != historyPositiveRelationDetail && null == historyReverseRelationDetail) {
            if (historyPositiveRelationDetail.getRelationType() != RELATION_TYPE.MATCH_VALUE) {
                relationPositiveDao.updateRelationTypeByUinAndTypeNumber(relationRequest.getSponsor(), relationRequest.getRecipient(), relationRequest.getRelationType());
                fireRelationChange(relationRequest.getSponsor(),relationRequest.getRecipient(),relationRequest.getRelationType(),relationRequest.getRelationSource());
            }
        } else {
            if (historyReverseRelationDetail.getRelationType() == RELATION_TYPE.LIKE_VALUE
                    && relationRequest.getRelationType() == RELATION_TYPE.LIKE_VALUE) {
                relationPositiveDao.updateRelationTypeByUinAndTypeNumber(relationRequest.getRecipient(), relationRequest.getSponsor(), RELATION_TYPE.MATCH_VALUE);
                fireRelationChange(relationRequest.getSponsor(),relationRequest.getRecipient(),RELATION_TYPE.MATCH_VALUE,relationRequest.getRelationSource());
            } else if (historyReverseRelationDetail.getRelationType() != RELATION_TYPE.MATCH_VALUE && relationRequest.getRelationType() == RELATION_TYPE.LIKE_VALUE) {
                relationPositiveDao.updateReversionRelationType(relationRequest.getSponsor(), relationRequest.getRecipient(), RELATION_TYPE.LIKE_VALUE);
                fireRelationChange(relationRequest.getSponsor(),relationRequest.getRecipient(),RELATION_TYPE.LIKE_VALUE,relationRequest.getRelationSource());
            } else if (historyReverseRelationDetail.getRelationType() != RELATION_TYPE.MATCH_VALUE) {
                relationPositiveDao.updateRelationTypeByUinAndTypeNumber(relationRequest.getRecipient(), relationRequest.getSponsor(), relationRequest.getRelationType());
                fireRelationChange(relationRequest.getSponsor(),relationRequest.getRecipient(),relationRequest.getRelationType(),relationRequest.getRelationSource());
            }
        }
        return RelationConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public List<RelationPersistResultDTO> fetchRelationPersistResults(RelationSearchDTO relationSearch) {
        List<RelationDetailPO> relationDetailsList = relationPositiveDao.selectRelationDetailsByUinAndRelationType(relationSearch.getSearchUin(), relationSearch.getRelationTypes(), relationSearch.isReverse());
        return buildRelationPersistResultList(relationDetailsList);
    }

    @Override
    public List<RelationPersistResultDTO> fetchMatchRelationPersistResults(long uin) {
        List<RelationDetailPO> relationDetailsList = relationPositiveDao.selectMatchRelationDetailsByUin(uin);
        return buildRelationPersistResultList(relationDetailsList);
    }

    private List<RelationPersistResultDTO> buildRelationPersistResultList(List<RelationDetailPO> relationDetailsList) {
        if (null != relationDetailsList && !relationDetailsList.isEmpty()) {
            List<RelationPersistResultDTO> relationPersistResultList = new ArrayList<>(relationDetailsList.size());
            for (RelationDetailPO relationDetail : relationDetailsList) {
                RelationPersistResultDTO relationPersistResult = new RelationPersistResultDTO();
                BeanUtils.copyProperties(relationDetail, relationPersistResult);
                relationPersistResultList.add(relationPersistResult);
            }
            return relationPersistResultList;
        }
        return Collections.emptyList();
    }

    private void fireRelationChange(long fromUin, long toUin, int relationType, int relationSource) {
        String payload = MessageUtil.buildBase64MqttMessage(MqttConst.TAG_RELATION_SYNC_RELATION,RelationMqttMessage.newBuilder()
                .setSponsor(fromUin)
                .setRecipient(toUin)
                .setRelationTypeValue(relationType)
                .setRelationSourceValue(relationSource)
                .setRefreshTime(System.currentTimeMillis()).build());
        mqttSenderService.sendToMqtt(1,payload);
    }
}
