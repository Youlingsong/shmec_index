package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.NewsDao;
import com.datahome.entity.NewsEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/9 13:42
 */
public interface NewsRepository extends BaseDao<NewsEntity, Integer>, NewsDao {

    List<Integer> findNewsIdsByPublishFlag(String publishFlag);
}
