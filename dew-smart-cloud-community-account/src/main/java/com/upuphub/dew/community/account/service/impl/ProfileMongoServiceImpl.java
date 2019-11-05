package com.upuphub.dew.community.account.service.impl;

import com.upuphub.dew.community.account.bean.po.ProfilePO;
import com.upuphub.dew.community.account.service.ProfileMongoService;
import com.upuphub.dew.community.account.utils.ObjectUtil;
import com.upuphub.profile.annotation.ProfileService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 21:00
 */

@ProfileService("ProfileMongoService")
public class ProfileMongoServiceImpl implements ProfileMongoService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Map<String, Object> pullProfile(Long uin, List<String> profileKeys) {
        return pullMongoProfile(uin,profileKeys);
    }

    @Override
    public Integer pushProfile(Long uin, Map<String, Object> paramsMap) {
        if (checkProfileByUin(uin)) {
            Map<String, Object> where = new LinkedHashMap<>();
            where.put("uin", getAccountUinKey(uin));
            mongoTemplate.updateFirst(createEasyQuery(null, where, null), createEasyUpdate(paramsMap), ProfilePO.class);
            return paramsMap.size();
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public Integer initProfile(Long uin, Map<String, Object> paramsMap) {
        // 提交保存Profile信息
        // 如果提交的Uin不存在，进行保存操作
        if(!checkProfileByUin(uin)){
            mongoTemplate.save(map2ProfileBeanWithUin(uin, paramsMap,true));
        }
        return 1;
    }

    /**
     * 转换入参为需要的MongoDB的Pojo对象
     *
     * @param uin 用户Uin
     * @param paramsMap 用户默认参数的Mao
     * @param isNew 是否是新创建的账号
     * @return 转换后的Pojo对象
     */
    private ProfilePO map2ProfileBeanWithUin(Long uin, Map<String, Object> paramsMap, boolean isNew) {
        ProfilePO baseMongoProfile = new ProfilePO();
        baseMongoProfile.setUin(getAccountUinKey(uin));
        baseMongoProfile.setProfile(paramsMap);
        if(isNew){
            baseMongoProfile.setCreateTime(System.currentTimeMillis());
        }
        baseMongoProfile.setUpdateTime(System.currentTimeMillis());
        return baseMongoProfile;
    }

    @Override
    public boolean checkProfileByUin(long uin) {
        return !ObjectUtil.isEmpty(mongoTemplate.findOne(createEasyQuery(Collections.singletonList("uin"), Collections.singletonMap("uin",getAccountUinKey(uin)), 1), ProfilePO.class));
    }

    @Override
    public void cleanProfileByUin(Long uin) {
        ProfilePO profile = new ProfilePO();
        profile.setUin(getAccountUinKey(uin));
        mongoTemplate.remove(profile).getDeletedCount();
    }

    /**
     * 从MongoDB中查询Profile信息
     * @param uin 用户Uin
     * @param originalMongoKeys 查询到的Profile信息集合
     * @return 用户Profile信息
     */
    private Map<String,Object> pullMongoProfile(long uin,List<String> originalMongoKeys){
        if (!originalMongoKeys.isEmpty()) {
            ProfilePO profileEntity = pullProfileByUin(uin, originalMongoKeys);
            // 如果查询到的结果为空，直接返回
            if (null == profileEntity) {
                return Collections.emptyMap();
            } else {
                // 如果获取的数据不为空,将查询到的结果放回到返回的Map中
                if (null != profileEntity.getProfile()) {
                    return profileEntity.getProfile();
                }
            }
        }
        return Collections.emptyMap();
    }

    /**
     * 查询指定集合的Profile信息
     *
     * @param uin  用户uin
     * @param keys 需要查询的用户的Key
     * @return 查询到的uin的指定profile属性的值
     */
    private ProfilePO pullProfileByUin(long uin, List<String> keys) {
        // 组装查询条件
        Map<String, Object> where = new LinkedHashMap<>();
        where.put("uin", getAccountUinKey(uin));
        return mongoTemplate.findOne(createEasyQuery(keys, where, 1), ProfilePO.class);
    }


    /**
     * 根据Key和条件,返回条件筛选条件
     * @param keys 需要查询的Key
     * @param where 查询的限制条件
     * @param limit 查询的限制数量
     * @return 查询条件的返回结果
     */
    private Query createEasyQuery(List<String> keys, Map<String, Object> where, Integer limit) {
        Query query = new Query();
        if (!ObjectUtil.isEmpty(keys)) {
            keys.forEach(key -> query.fields().include(getProfileKey(key)));
        }
        if (!ObjectUtil.isEmpty(where)) {
            where.forEach((key, value) -> {
                if("uin".equals(key)){
                    query.addCriteria(Criteria.where("uin").is(value));
                }else {
                    query.addCriteria(Criteria.where(getProfileKey(key)).is(value));
                }
            });
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
            updateData.forEach((key, value) -> update.set(getProfileKey(key), value));
        }
        return update;
    }
    /**
     * 转换用户Profile属性的Key
     *
     * @param key 用户的基础属性
     * @return 转换后的用户属性的Key
     */
    private String getProfileKey(String key) {
        return String.format("profile.%s", key);
    }

    /**
     * 统一管理获取账户Uin的Key
     *
     * @param uin 用户Uin
     * @return 转换后的用户Uin的key
     */
    private String getAccountUinKey(Long uin) {
        return String.format("dew:uin:%s", uin);
    }



}
