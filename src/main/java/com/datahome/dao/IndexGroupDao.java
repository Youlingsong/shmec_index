package com.datahome.dao;

import com.datahome.entity.IndexGroupEntity;
import com.datahome.entity.IndexGroupIndexEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/13 15:56
 */
public interface IndexGroupDao {

    void saveIndexGroupIndex(IndexGroupIndexEntity indexGroupIndexEntity);

    void deleteIndexGroupIndexBy_indexId(Integer indexId);

    void deleteIndexGroupIndexBy_groupId(Integer groupId);

    List<IndexGroupEntity> findIndexGroupsByIndexId(Integer indexId);
}
