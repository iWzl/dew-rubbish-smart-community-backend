package com.upuphub.dew.community.message.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.message.bean.po.MessageDetailsPO;
import com.upuphub.dew.community.message.component.SnowflakeId;
import com.upuphub.dew.community.message.service.MessageService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    SnowflakeId snowflakeId;
    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public MessagePayload persistMessage(MessagePayload messagePayload) {
        MessageDetailsPO messageDetail = new MessageDetailsPO();
        long createTime = System.currentTimeMillis();
        long messageId = snowflakeId.nextId();
        MessagePayload newMessagePayload = MessagePayload.newBuilder(messagePayload)
                .setMessageId(messageId)
                .setTimestamp(createTime).build();
        messageDetail.setSequenceId(messageId);
        messageDetail.setCreateTime(createTime);
        messageDetail.setMessage(MessageUtil.buildBase64Message(newMessagePayload));
        messageDetail.setRecipient(messagePayload.getReceiver());
        messageDetail.setSender(messagePayload.getSender());
        messageDetail.setStatus(0);
        messageDetail.setTimelineId(buildTimeLineId(messagePayload.getSender(),messagePayload.getReceiver()));
        mongoTemplate.save(messageDetail);
        return newMessagePayload;
    }

    @Override
    public List<String> fetchMessagePayloadsByTimeLine(String timeLineId, long sequenceId, boolean forward, int size) {
        Query messagePayloadQuery = new Query();
        messagePayloadQuery.addCriteria(Criteria.where("timeline_id").is(timeLineId));
        messagePayloadQuery.limit(size);
        if(0 != sequenceId){
            Sort sort = new Sort(Sort.Direction.ASC,"_id");
            if(forward){
                messagePayloadQuery.addCriteria(Criteria.where("_id").gt(sequenceId));
            }else {
                messagePayloadQuery.addCriteria(Criteria.where("_id").lt(sequenceId));
            }
            messagePayloadQuery.with(sort);
        }else {
            Sort sort = new Sort(Sort.Direction.DESC,"_id");
            messagePayloadQuery.with(sort);
        }
        List<MessageDetailsPO> messageDetailsList = mongoTemplate.find(messagePayloadQuery,MessageDetailsPO.class);
        return messageDetailsList.stream().map(MessageDetailsPO::getMessage).collect(Collectors.toList());
    }

    private String buildTimeLineId(long uin1, long uin2){
        if(uin1 > uin2){
            return String.format("%s-%s",uin2,uin1);
        }else {
            return String.format("%s-%s",uin1,uin2);
        }
    }
}
