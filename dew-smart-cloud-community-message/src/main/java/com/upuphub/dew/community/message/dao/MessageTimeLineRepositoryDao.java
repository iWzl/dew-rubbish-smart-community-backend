package com.upuphub.dew.community.message.dao;

import com.upuphub.dew.community.message.bean.po.MessageDetailsPO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LeoWang
 */
@Repository
public interface MessageTimeLineRepositoryDao extends PagingAndSortingRepository<MessageDetailsPO, String> {

}
