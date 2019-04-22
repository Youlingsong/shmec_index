package com.datahome.dao.impl;

import com.datahome.dao.AccountDao;
import com.datahome.entity.AccountEntity;
import com.datahome.util.AccountUtil;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/8 18:45
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Resource
    private EntityManager entityManager;

    @Resource
    private AccountUtil accountUtil;

    @Override
    public List<AccountEntity> find_by_accountStatus_city_name(String name, String accountStatus, Integer cityid, String order, String sort, Integer pageSize, Integer pageNumber) {
        String hql = " from AccountEntity where 1=1  ";
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();

        hql = accountUtil.findHql(hql, name, accountStatus, cityid, paramsMap);
        hql = CommonUtil.order(hql, order, sort);

        Query query = entityManager.createQuery(hql);

        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        CommonUtil.page(query, pageNumber, pageSize);

        return query.getResultList();
    }

    @Override
    public List<AccountEntity> find_by_cityIds(List<Integer> cityIds) {
        return entityManager.createQuery(" from AccountEntity where cityEntity.id in (:cityIds)  and accountRoleEntity.level in ('1','5') and accountstatus = '1' ").setParameter("cityIds", cityIds).getResultList();
    }


    @Override
    public Integer findTotal_by_accountStatus_city_name(String name, String accountStatus, Integer cityId) {
        String hql = " select count(*) from AccountEntity where 1=1  ";
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();
        hql = accountUtil.findHql(hql, name, accountStatus, cityId, paramsMap);

        Query query = entityManager.createQuery(hql);

        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
