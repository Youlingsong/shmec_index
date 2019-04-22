package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.GdnMiddleDataDao;
import com.datahome.entity.GdnMiddleDataEntity;

import java.util.List;

public interface GdnMiddleDataRepository extends BaseDao<GdnMiddleDataEntity,String>, GdnMiddleDataDao {


    List<GdnMiddleDataEntity> findByRemakeLike(String s);
}
