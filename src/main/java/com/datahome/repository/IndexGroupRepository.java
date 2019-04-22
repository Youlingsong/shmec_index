package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.IndexGroupDao;
import com.datahome.entity.IndexGroupEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/13 15:03
 */
public interface IndexGroupRepository extends BaseDao<IndexGroupEntity, Integer>, IndexGroupDao {

    IndexGroupEntity findByName(String groupName);

    List<IndexGroupEntity> findByIdIn(List<Integer> indexGroupIds);
}
