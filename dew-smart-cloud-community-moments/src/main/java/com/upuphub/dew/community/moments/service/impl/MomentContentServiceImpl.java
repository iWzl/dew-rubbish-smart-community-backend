package com.upuphub.dew.community.moments.service.impl;

import com.upuphub.dew.community.moments.bean.po.MomentDynamicPO;
import com.upuphub.dew.community.moments.service.MomentContentService;
import com.upuphub.dew.community.moments.utils.ObjectUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MomentContentServiceImpl implements MomentContentService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Long searchMomentDynamicContentDraftId(long uin) {
        Map<String,Object> where = new HashMap<>();
        where.put("founderUin",uin);
        where.put("draft",true);
        MomentDynamicPO momentDynamicContent = mongoTemplate
                .findOne(createEasyQuery(Collections.singletonList("dynamicId"),where, 1)
                        .with(new Sort(Sort.Direction.DESC,"dynamicId")), MomentDynamicPO.class);
        if(!ObjectUtil.isEmpty(momentDynamicContent)){
            return momentDynamicContent.getDynamicId();
        }
        return 0L;
    }

    @Override
    public int createMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        System.out.println();
        return 0;
    }

    @Override
    public int updateMomentDynamicContent(MomentDynamicPO momentDynamicContent) {
        System.out.println();
        return 0;
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
}
