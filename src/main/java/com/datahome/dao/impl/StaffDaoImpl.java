package com.datahome.dao.impl;

import com.datahome.dao.StaffDao;
import com.datahome.entity.StaffEntity;
import com.datahome.entity.StaffRoleEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/8 18:45
 */
@Repository
public class StaffDaoImpl implements StaffDao {

    @Resource
    private EntityManager entityManager;

    @Override
    public List<StaffRoleEntity> findStaffRoleBy_Id(Integer roleId) {
        String hql = " from StaffRoleEntity where id = :roleId ";
        return entityManager.createQuery(hql).setParameter("roleId", roleId).getResultList();
    }

    @Override
    public List<StaffRoleEntity> findStaffRoles() {
        String hql = " from StaffRoleEntity  ";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<StaffEntity> findsBy_cityIds_status(List<Integer> cityIds, String status) {
        String hql = " from StaffEntity where cityId in (:cityId) ";
        if (status != null) {
            hql += " and status = :status ";
        }
        Query query = entityManager.createQuery(hql);
        query.setParameter("cityId", cityIds);
        if (status != null) {
            query.setParameter("status", status);
        }
        return query.getResultList();
    }
}
