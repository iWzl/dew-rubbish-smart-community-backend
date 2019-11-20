package com.upuphub.dew.community.moments.dao;

import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentsPublishMomentsPublishDao {
    int deleteByPrimaryKey(Long id);

    int insert( MomentsPublishPO record);

    int insertSelective(MomentsPublishPO record);

    MomentsPublishPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MomentsPublishPO record);

    int updateByPrimaryKey(MomentsPublishPO record);
}