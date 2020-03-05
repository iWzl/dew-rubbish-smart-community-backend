package com.upuphub.dew.community.moments.dao;

import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentsPublishMomentsPublishDao {
    /**
     * 添加发布Moment记录
     *
     * @param momentsPublishRecord 用户发布的Moments的发布审核记录信息
     * @return 受影响的行数,返回如果受影响的行数为1,插入正常
     */
    int insertMomentsPublishRecord(@Param("momentPublishPO")MomentsPublishPO momentsPublishRecord);

    /**
     * 根据GeoHash查询附近范围的值
     *
     * @param rangeGeoHash 范围的GeoHash
     * @return Moments发布详情
     */
    List<MomentsPublishPO> selectMomentPublishRecordByLocation(@Param("rangeGeoHash") String rangeGeoHash);


    /**
     * 查询Moment信息通过用户UIN
     *
     * @param uin 用户UIN
     * @return Moments发布详情
     */
    List<MomentsPublishPO> selectMomentPublishRecordByUin(@Param("uin") long uin);

    /**
     * 查询Moment信息通过用户Classify
     *
     * @param classify 分类
     * @return Moments发布详情
     */
    List<MomentsPublishPO> selectMomentPublishRecordByClassify(@Param("classify") int classify);

    /**
     * 查询Moment信息通过用户Topic
     *
     * @param topic 分类
     * @return Moments发布详情
     */
    List<MomentsPublishPO> selectMomentPublishRecordByTopic(@Param("topic") String topic);

    int deleteByPrimaryKey(Long id);

    int insertSelective(MomentsPublishPO record);

    MomentsPublishPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MomentsPublishPO record);

    int updateByPrimaryKey(MomentsPublishPO record);

}