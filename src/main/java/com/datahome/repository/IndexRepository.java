package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.IndexDao;
import com.datahome.entity.IndexEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/9 9:59
 */
public interface IndexRepository extends BaseDao<IndexEntity, Integer>, IndexDao {

    List<IndexEntity> findAllByParentId(Integer parentId);

    List<IndexEntity> findAllByParentIdAndIndexStatus(Integer parentId, String indexStatus);

    List<IndexEntity> findAllByIndexStatus(String indexStatus);


}
