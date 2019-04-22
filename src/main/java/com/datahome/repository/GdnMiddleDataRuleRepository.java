package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.GdnMiddleDataRuleDao;
import com.datahome.entity.GdnMiddleDataRuleEntity;

import java.util.List;

public interface GdnMiddleDataRuleRepository extends BaseDao<GdnMiddleDataRuleEntity, String>, GdnMiddleDataRuleDao {
    List<GdnMiddleDataRuleEntity> findByNameLike(String name);
}
