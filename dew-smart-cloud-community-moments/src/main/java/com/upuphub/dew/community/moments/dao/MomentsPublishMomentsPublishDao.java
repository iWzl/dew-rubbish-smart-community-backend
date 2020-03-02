package com.upuphub.dew.community.moments.dao;

import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentsPublishMomentsPublishDao {
    /**
     * 添加发布Moment记录
     *
     * @param momentsPublishRecord 用户发布的Moments的发布审核记录信息
     * @return 受影响的行数,返回如果受影响的行数为1,插入正常
     */
    int insertMomentsPublishRecord(@Param("momentPublishPO")MomentsPublishPO momentsPublishRecord);

    int deleteByPrimaryKey(Long id);

    int insertSelective(MomentsPublishPO record);

    MomentsPublishPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MomentsPublishPO record);

    int updateByPrimaryKey(MomentsPublishPO record);
}