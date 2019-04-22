package com.datahome.dao.impl;

import com.datahome.dao.IndexGroupDao;
import com.datahome.entity.IndexGroupEntity;
import com.datahome.entity.IndexGroupIndexEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/13 16:00
 */
@Repository
public class IndexGroupDaoImpl implements IndexGroupDao {

    @Resource
    private EntityManager entityManager;


    @Override
    public void saveIndexGroupIndex(IndexGroupIndexEntity indexGroupIndexEntity) {
        entityManager.merge(indexGroupIndexEntity);
    }

    @Override
    public void deleteIndexGroupIndexBy_indexId(Integer indexId) {
        String sql = " delete from age06_indexgroup_index where indexId = :indexId ";
        entityManager.createNativeQuery(sql).setParameter("indexId", indexId).executeUpdate();
    }

    @Override
    public void deleteIndexGroupIndexBy_groupId(Integer groupId) {
        String sql = " delete from age06_indexgroup_index where indexGroupId = :groupId ";
        entityManager.createNativeQuery(sql).setParameter("groupId", groupId).executeUpdate();
    }

    @Override
    public List<IndexGroupEntity> findIndexGroupsByIndexId(Integer indexId) {
        String sql = "select g.* from age06_indexgroup_index i , age06_indexgroup g  where i.indexId = :indexId and i.indexGroupId = g.id ";
        return entityManager.createNativeQuery(sql, IndexGroupEntity.class).setParameter("indexId", indexId).getResultList();
    }
}
