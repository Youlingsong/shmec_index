package com.datahome.dao.impl;

import com.datahome.dao.CityDao;
import com.datahome.entity.CityEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 14:58
 */

@Repository
public class CityDaoImpl implements CityDao {

    @Resource
    private EntityManager entityManager;

    @Override
    public List<Integer> findAllChildrenIdBy_nodeIds_cityStatus(String nodeIds, List<String> cityStatus) {
        String sql = " select id from age06_city a where a.cityStatus in (:cityStatus)   and a.nodeIds like :nodeIds ";
        return entityManager.createNativeQuery(sql).setParameter("nodeIds", nodeIds+"%").setParameter("cityStatus", cityStatus).getResultList();
    }

    @Override
    public List<CityEntity> findAllChildrenBy_nodeIds_cityStatus(String nodeIds, List<String> cityStatus) {
        String sql = " select * from age06_city a where a.cityStatus in (:cityStatus)  and a.nodeIds like :nodeIds ";
        return entityManager.createNativeQuery(sql, CityEntity.class).setParameter("nodeIds", nodeIds+"%").setParameter("cityStatus", cityStatus).getResultList();
    }

    @Override
    public Integer findCountby_ids_cityStatus(List<Integer> cityIds, String cityStatus) {
        String sql = " select count(*) from age06_city where  id  in (:cityIds ) and cityStatus = :cityStatus ";
        return Integer.parseInt(entityManager.createNativeQuery(sql).setParameter("cityIds", cityIds).setParameter("cityStatus", cityStatus).getSingleResult().toString());
    }

    @Override
    public void updateCityStatus_by_parentId(Integer parentId, String cityStatus) {
        String sql = " update age06_city set cityStatus= :cityStatus where parentId = :parentId";
        entityManager.createNativeQuery(sql).setParameter("cityStatus", cityStatus).setParameter("parentId", parentId).executeUpdate();
    }
}
