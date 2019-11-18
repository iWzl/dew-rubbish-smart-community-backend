package com.upuphub.dew.community.moments.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.service.MomentContentService;
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
import java.util.*;

@Slf4j
@Service
public class MomentContentServiceImpl implements MomentContentService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Long searchMomentDynamicContentDraftId(long uin) {
        Map<String,Object> where = new HashMap<>();
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER,uin);
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT,true);
        MomentDynamicPO momentDynamicContent = mongoTemplate
                .findOne(createEasyQuery(Collections.singletonList(MongoKeysConst.MOMENTS_DYNAMIC_ID),where, 1)
                        .with(new Sort(Sort.Direction.DESC,MongoKeysConst.MOMENTS_DYNAMIC_ID)), MomentDynamicPO.class);
        if(!ObjectUtil.isEmpty(momentDynamicContent)){
            return momentDynamicContent.getDynamicId();
        }
        return 0L;
    }

    @Override
    public int createMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        momentDynamicContent.setCreateTime(System.currentTimeMillis());
        momentDynamicContent.setUpdateTime(System.currentTimeMillis());
        momentDynamicContent.setDraft(true);
        mongoTemplate.save(momentDynamicContent);
        return 0;
    }

    @Override
    public int updateMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        momentDynamicContent.setUpdateTime(System.currentTimeMillis());
        Map<String, Object> where = new LinkedHashMap<>();
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_ID, momentDynamicContent.getDynamicId());
        where.put(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER,momentDynamicContent.getFounderUin());
        mongoTemplate.updateFirst(createEasyQuery(null,where,null),
                createEasyUpdate(ObjectUtil.beanToMap(momentDynamicContent,dynamicIgnoreSet())),MomentDynamicPO.class);
        return 0;
    }

    private Set<String> dynamicIgnoreSet(){
        Set<String> ignoreSet = new HashSet<>();
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_ID);
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_FOUNDER);
        ignoreSet.add(MongoKeysConst.MOMENTS_DYNAMIC_DRAFT);
        return ignoreSet;
    }

    /**
     * 根据Key和条件,返回条件筛选条件
     *
     * @param keys 需要查询的Key
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
            where.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        }
        if (!ObjectUtil.isEmpty(limit)) {
            query.limit(limit);
        }
        return query;
    }
    /**
     * 创建基础的数据更新条件
     * @param updateData  更新的条件
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
