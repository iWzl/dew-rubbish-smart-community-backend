package com.upuphub.dew.community.moments.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.upuphub.dew.community.connection.annotation.ProtobufField;
import com.upuphub.dew.community.connection.common.JsonHelper;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.protobuf.push.MomentSyncActivityNotify;
import com.upuphub.dew.community.moments.bean.po.MomentActivityPO;
import com.upuphub.dew.community.moments.bean.po.MomentCommentPO;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.bean.po.MomentReplyPO;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.service.MqttSenderService;
import com.upuphub.dew.community.moments.utils.MongoKeysConst;
import com.upuphub.dew.community.moments.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Moment正文文本绑定的服务
 *
 * @author LeoWang
 */
@Slf4j
@Service
public class MomentContentServiceImpl implements MomentContentService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private MqttSenderService mqttSenderService;

    @Override
    public Long searchMomentDynamicDraftIdByUin(long uin) {
        Map<String, Object> where = new HashMap<>(2);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER, uin);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT, true);
        MomentDynamicPO momentDynamicContent = mongoTemplate
                .findOne(createEasyQuery(Collections.singletonList(MongoKeysConst.MOMENTS_DYNAMIC_ID), where, 1)
                        .with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_DYNAMIC_ID)), MomentDynamicPO.class);
        if (!ObjectUtil.isEmpty(momentDynamicContent)) {
            return momentDynamicContent.getMomentId();
        }
        return 0L;
    }

    @Override
    public MomentDynamicPO searchMomentDynamicContent(long uin) {
        Map<String, Object> where = new HashMap<>(2);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER, uin);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT, true);
        MomentDynamicPO momentDynamicContent = mongoTemplate
                .findOne(createEasyQuery(mongoSelectKeysList(MomentDynamicPO.class, Collections.emptySet()), where, 1)
                        .with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_DYNAMIC_ID)), MomentDynamicPO.class);
        if (!ObjectUtil.isEmpty(momentDynamicContent)) {
            return momentDynamicContent;
        }
        return null;
    }



    @Override
    public MomentDynamicPO searchMomentDynamicContentByMomentId(long momentId) {
        Map<String, Object> where = Collections.singletonMap(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentId);
        MomentDynamicPO momentDynamicContent = mongoTemplate
                .findOne(createEasyQuery(mongoSelectKeysList(MomentDynamicPO.class, Collections.emptySet()), where, 1)
                        .with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_DYNAMIC_ID)), MomentDynamicPO.class);
        if (!ObjectUtil.isEmpty(momentDynamicContent)) {
            return momentDynamicContent;
        }
        return null;
    }

    @Override
    public Map<Long, MomentDynamicPO> searchMomentDynamicContentByMomentIds(List<Long> momentsIdList) {
        Query momentSearchQuery = new Query();
        momentSearchQuery.addCriteria(Criteria.where("_id").in(momentsIdList));
        momentSearchQuery.with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_DYNAMIC_ID));
        List<MomentDynamicPO> momentDynamicList = mongoTemplate.find(momentSearchQuery,MomentDynamicPO.class);
        if(!ObjectUtil.isEmpty(momentDynamicList)){
            return momentDynamicList.stream().collect(Collectors.toMap(MomentDynamicPO::getMomentId, (moment)->moment));
        }
        return Collections.emptyMap();
    }

    @Override
    public MomentCommentPO searchMomentCommentByCommentId(long commentId) {
        Map<String, Object> where = Collections.singletonMap("_id", commentId);
        MomentCommentPO momentCommentInfo = mongoTemplate.findOne(createEasyQuery(mongoSelectKeysList(MomentCommentPO.class, Collections.emptySet()), where, 1)
                .with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_COMMENT_ID)), MomentCommentPO.class);
        if (!ObjectUtil.isEmpty(momentCommentInfo)) {
            return momentCommentInfo;
        }
        return null;
    }

    @Override
    public Map<Long, List<MomentCommentPO>> searchMomentsCommentByMomentIds(List<Long> momentsIdList) {
        if(null == momentsIdList || momentsIdList.isEmpty()){
            return Collections.emptyMap();
        }
        Query momentCommentQuery = new Query();
        momentCommentQuery.addCriteria(Criteria.where(MongoKeysConst.MOMENTS_DYNAMIC_ID).in(momentsIdList));
        momentCommentQuery.with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_COMMENT_ID));
        List<MomentCommentPO> momentCommentInfos = mongoTemplate.find(momentCommentQuery,MomentCommentPO.class);
        if(!ObjectUtil.isEmpty(momentCommentInfos)){
            Map<Long, List<MomentCommentPO>> momentsCommentMap = new HashMap<>();
            for (MomentCommentPO momentCommentInfo : momentCommentInfos) {
                if(momentsCommentMap.containsKey(momentCommentInfo.getMomentId())){
                    momentsCommentMap.get((momentCommentInfo.getMomentId())).add(momentCommentInfo);
                }else {
                    List<MomentCommentPO> momentCommentList = new ArrayList<>();
                    momentCommentList.add(momentCommentInfo);
                    momentsCommentMap.put(momentCommentInfo.getMomentId(),momentCommentList);
                }
            }
            return momentsCommentMap;
        }
        return Collections.emptyMap();
    }

    @Override
    public List<MomentCommentPO> searchMomentsCommentByMomentId(Long momentId) {
        Map<String, Object> where = Collections.singletonMap(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentId);
        return searchMomentsCommentByWhereMap(where);
    }

    @Override
    public List<MomentCommentPO> searchMomentsCommentByMomentIdAndCommentType(Long momentId, int typeValue) {
        Map<String, Object> where = new HashMap<>();
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentId);
        where.put("content_type",typeValue);
        return searchMomentsCommentByWhereMap(where);
    }

    @Override
    public List<MomentCommentPO> searchMomentsCommentByMomentIdAndCommentTypeAndUin(Long momentId, int typeValue, Long fromUin) {
        Map<String, Object> where = new HashMap<>();
        where.put("from_uin", fromUin);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentId);
        where.put("content_type",typeValue);
        return searchMomentsCommentByWhereMap(where);
    }

    private List<MomentCommentPO> searchMomentsCommentByWhereMap(Map<String, Object> where) {
        List<MomentCommentPO> momentCommentInfos = mongoTemplate.find(createEasyQueryNoResetKeys(mongoSelectKeysList(MomentCommentPO.class, Collections.emptySet()), where,null)
                .with(new Sort(Sort.Direction.DESC, MongoKeysConst.MOMENTS_COMMENT_ID)), MomentCommentPO.class);
        if (!ObjectUtil.isEmpty(momentCommentInfos)) {
            return momentCommentInfos;
        }
        return null;
    }

    @Override
    public List<MomentReplyPO> searchMomentsCommentReplyByCommentId(Long commentId) {
        Map<String, Object> where = Collections.singletonMap("comment_id", commentId);
        List<MomentReplyPO> momentReplyList = mongoTemplate.find(createEasyQueryNoResetKeys(mongoSelectKeysList(MomentReplyPO.class, Collections.emptySet()), where, null)
                .with(new Sort(Sort.Direction.DESC, "create_time")), MomentReplyPO.class);
        if (!ObjectUtil.isEmpty(momentReplyList)) {
            return momentReplyList;
        }
        return null;
    }

    @Override
    public Map<Long, List<MomentReplyPO>> searchMomentsCommentReplyByCommentIds(List<Long> momentCommentIdList) {
        if(ObjectUtil.isEmpty(momentCommentIdList)){
            return Collections.emptyMap();
        }
        Query momentReplyQuery = new Query();
        momentReplyQuery.addCriteria(Criteria.where("comment_id").in(momentCommentIdList));
        momentReplyQuery.with(new Sort(Sort.Direction.DESC, "create_time"));
        List<MomentReplyPO> momentReplyList = mongoTemplate.find(momentReplyQuery,MomentReplyPO.class);
        if(momentReplyList.isEmpty()){
            return Collections.emptyMap();
        }else {
            Map<Long, List<MomentReplyPO>> momentsReplyMap = new HashMap<>();
            for (MomentReplyPO momentReplyInfo : momentReplyList) {
                if(momentsReplyMap.containsKey(momentReplyInfo.getCommentId())){
                    momentsReplyMap.get((momentReplyInfo.getCommentId())).add(momentReplyInfo);
                }else {
                    List<MomentReplyPO> newMomentReplyList = new ArrayList<>();
                    newMomentReplyList.add(momentReplyInfo);
                    momentsReplyMap.put(momentReplyInfo.getCommentId(),newMomentReplyList);
                }
            }
            return momentsReplyMap;
        }
    }

    @Override
    public int createDraftMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        momentDynamicContent.setCreateTime(System.currentTimeMillis());
        momentDynamicContent.setUpdateTime(System.currentTimeMillis());
        momentDynamicContent.setDraft(true);
        mongoTemplate.save(momentDynamicContent);
        return 0;
    }

    @Override
    public <T> T saveCommonMomentDetails(T commonMomentDetail) {
        commonMomentDetail = mongoTemplate.save(commonMomentDetail);
        log.debug("Save Common Moment Detail [{}]", JsonHelper.allToJson(commonMomentDetail));
        return commonMomentDetail;
    }

    @Override
    public long saveMomentActivity(MomentActivityPO momentActivity) {
        momentActivity = mongoTemplate.save(momentActivity);
        MomentSyncActivityNotify
                momentSyncActivityNotify = MomentSyncActivityNotify.newBuilder()
                .setActivityId(momentActivity.getActivityId())
                .setActivityType(momentActivity.getActivityTypeNumber())
                .setForUin(momentActivity.getForUin())
                .setFromUin(momentActivity.getByUin())
                .setMomentId(momentActivity.getMomentId())
                .build();
        String payload = MessageUtil.buildBase64MqttMessage(MqttConst.TAG_MOMENT_SYNC_ACTIVITY,momentSyncActivityNotify);
        mqttSenderService.sendToMqtt(MqttConst.TOPIC_MOMENTS,payload);
        log.info("Notify Account Sync Activity");
        return momentActivity.getActivityId();
    }


    @Override
    public int updateDraftMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        momentDynamicContent.setUpdateTime(System.currentTimeMillis());
        Map<String, Object> where = new LinkedHashMap<>();
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentDynamicContent.getMomentId());
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER, momentDynamicContent.getFounder());
        mongoTemplate.updateFirst(createEasyQuery(null, where, 1),
                createEasyUpdate(ObjectUtil.beanToMap(momentDynamicContent, dynamicIgnoreSet())), MomentDynamicPO.class);
        return 0;
    }

    @Override
    public long updateMomentDraftStatus(long dynamicId, boolean isDraft) {
        Map<String, Object> updateParams = new HashMap<>(2);
        updateParams.put(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT, isDraft);
        updateParams.put(MongoKeysConst.MOMENTS_DYNAMIC_UPDATE_TIME, System.currentTimeMillis());
        UpdateResult updateResult = mongoTemplate.updateFirst(createEasyQuery(null, Collections.singletonMap(
                MongoKeysConst.MOMENTS_DYNAMIC_ID, dynamicId
                ), 1),
                createEasyUpdate(updateParams), MomentDynamicPO.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public int deleteDraftMomentDynamicContent(long founder) {
        Map<String, Object> where = new HashMap<>(2);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER, founder);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT, true);
        mongoTemplate.remove(createEasyQuery(null, where, null), MomentDynamicPO.class);
        return 0;
    }

    @Override
    public void deleteMomentCommentByCommentId(Long momentCommentId) {
        Map<String, Object> where = Collections.singletonMap("_id", momentCommentId);
        mongoTemplate.remove(createEasyQuery(null, where, null), MomentCommentPO.class);
    }

    private Set<String> dynamicIgnoreSet() {
        Set<String> ignoreSet = new HashSet<>();
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_ID);
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER);
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT);
        return ignoreSet;
    }

    private List<String> mongoSelectKeysList(Class<?> clazz, Set<String> ignoreSet) {
        List<String> mongoKeysList = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            org.springframework.data.mongodb.core.mapping.Field mongoField
                    = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
            if (null != mongoField && !"".equals(mongoField.value())) {
                if (ignoreSet.contains(mongoField.value())) {
                    continue;
                }
                mongoKeysList.add(mongoField.value());
            } else {
                if (ignoreSet.contains(field.getName())) {
                    continue;
                }
                mongoKeysList.add(field.getName());
            }
        }
        return mongoKeysList;
    }

    /**
     * 根据Key和条件,返回条件筛选条件
     *
     * @param keys  需要查询的Key
     * @param where 查询的限制条件
     * @param limit 查询的限制数量
     * @return 查询条件的返回结果
     */
    private Query createEasyQuery(List<String> keys, Map<String, Object> where, Integer limit) {
        Query query = new Query();
        if (!ObjectUtil.isEmpty(keys)) {
            keys.forEach(key -> query.fields().include(key));
        }
        if (!ObjectUtil.isEmpty(where)) {
            where.forEach((key, value) -> query.addCriteria(Criteria.where(resetMomentWhereParamKey(key)).is(value)));
        }
        if (!ObjectUtil.isEmpty(limit)) {
            query.limit(limit);
        }
        return query;
    }

    /**
     * 根据Key和条件,返回条件筛选条件
     *
     * @param keys  需要查询的Key
     * @param where 查询的限制条件
     * @param limit 查询的限制数量
     * @return 查询条件的返回结果
     */
    private Query createEasyQueryNoResetKeys(List<String> keys, Map<String, Object> where, Integer limit) {
        Query query = new Query();
        if (!ObjectUtil.isEmpty(keys)) {
            keys.forEach(key -> query.fields().include(key));
        }
        if (!ObjectUtil.isEmpty(where)) {
            where.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        }
        if (!ObjectUtil.isEmpty(limit)) {
            query.limit(limit);
        }
        return query;
    }

    private String resetMomentWhereParamKey(String key) {
        return (MongoKeysConst.MOMENTS_DYNAMIC_ID.equalsIgnoreCase(key)
                || MongoKeysConst.MOMENTS_ACTIVITY_ID.equalsIgnoreCase(key)) ? "_id" : key;
    }

    /**
     * 创建基础的数据更新条件
     *
     * @param updateData 更新的条件
     * @return 更新组合条件
     */
    private Update createEasyUpdate(Map<String, Object> updateData) {
        Update update = new Update();
        if (!ObjectUtil.isEmpty(updateData)) {
            updateData.forEach(update::set);
        }
        return update;
    }

}
