package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.GdnScoringTaskDao;
import com.datahome.entity.GdnScoringTaskEntity;

import java.util.List;

public interface GdnScoringTaskRepository extends BaseDao<GdnScoringTaskEntity,String>, GdnScoringTaskDao {


    List<GdnScoringTaskEntity> findByNameLike(String s);
}
