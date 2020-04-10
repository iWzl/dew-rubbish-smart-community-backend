package com.upuphub.dew.community.news.dao;

import com.upuphub.dew.community.news.bean.po.NewsDetailPO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepositoryDao extends PagingAndSortingRepository<NewsDetailPO, Long> {

}
