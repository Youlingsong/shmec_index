package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.entity.IndexUnitsEntity;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/9 15:03
 */
public interface IndexUnitsRepository  extends BaseDao<IndexUnitsEntity, Integer> {

    IndexUnitsEntity findByUnitsName(String unitsName);
}
