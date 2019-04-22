package com.datahome.dao.impl;

import com.datahome.dao.IndexDao;
import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.IndexUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description: 指标体系管理
 * @Date: Create in 2018/5/7 15:29
 */

@Repository
public class IndexDaoImpl implements IndexDao {


    @Resource
    private EntityManager entityManager;

    @Override
    public Integer findTotal_by_idList_indexName_indexStatus_parentId(List<Integer> allowReadIdList, String indexName, String indexStatus, Integer parentId) {
        String sql = " select count(*) from age06_index i where 1=1  ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        sql = IndexUtil.findIndexSql(sql, allowReadIdList, indexName, indexStatus, parentId, params);
        Query query = entityManager.createNativeQuery(sql);

        //修改值
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }


    @Override
    public List<IndexEntity> find_by_idList_indexName_indexStatus_parentId(List<Integer> allowReadIdList, String indexName, String indexStatus, Integer parentId, String order, String sort, Integer pageSize, Integer pageNumber) {
        String sql = "  select * from age06_index i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = IndexUtil.findIndexSql(sql, allowReadIdList, indexName, indexStatus, parentId, params);
        //排序
        sql = CommonUtil.order(sql, "ASC", sort);
        Query query = entityManager.createNativeQuery(sql, IndexEntity.class);

        //分页
        CommonUtil.page(query, pageNumber, pageSize);

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }

    @Override
    public void updateIndexStatus_by_nodeIds(String nodesIds, String indexStatus) {
        String sql = " update age06_index set indexStatus = :indexStatus where  nodeIds like :nodesIds ";
        entityManager.createNativeQuery(sql).setParameter("indexStatus", indexStatus).setParameter("nodesIds", nodesIds + "%").executeUpdate();
    }

    @Override
    public List<IndexEntity> finds_by_ids_unitsId_indexName(List<Integer> ids, Integer unitsId, String indexName) {
        String hql = " from IndexEntity where 1=1  ";
        if (ids != null && ids.size() > 0) {
            hql += " and  id in (:ids)  ";
        }
        if (unitsId != null) {
            hql += " and  indexUnitsEntity.id = :unitsId  ";
        }
        if (indexName != null) {
            hql += " and  name = :indexName  ";
        }
        Query query = entityManager.createQuery(hql);
        if (ids != null && ids.size() > 0) {
            query.setParameter("ids", ids);
        }
        if (unitsId != null) {
            query.setParameter("unitsId", unitsId);
        }
        if (indexName != null) {
            query.setParameter("indexName", indexName);
        }
        return query.getResultList();
    }

    @Override
    public List<IndexCityEntity> findIndexCity_by_cityId_cityStatus_indexId_indexStatus(Integer cityId, String cityStatus, Integer indexId, String indexStatus) {
        String hql = " from IndexCityEntity where 1=1  ";

        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        hql = IndexUtil.findIndexCitySql(hql, cityId, cityStatus, indexId, indexStatus, params);

        Query query = entityManager.createQuery(hql);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public void saveIndexCity(IndexCityEntity indexCityEntity) {
        entityManager.persist(indexCityEntity);
    }

    @Override
    public Integer findCountBy_indexStatus_ids(String indexStatus, List<Integer> ids) {
        return Integer.parseInt(entityManager.createNativeQuery("select count(*) from age06_index where indexStatus = :indexStatus and id in (:ids)").setParameter("indexStatus", indexStatus).setParameter("ids", ids).getSingleResult().toString());
    }

    @Override
    public List<IndexEntity> findAllIndexs() {
        return entityManager.createQuery("from IndexEntity order by saveTime ASC ", IndexEntity.class).getResultList();
    }

    @Override
    public List<IndexEntity> findByIdOrodeIdorparentid(Integer id) {
        String sql = " select * from age06_index where  nodeids like  :nodesIds or id= :Id or parentid= :parentId ";
       return entityManager.createNativeQuery(sql,IndexEntity.class).setParameter("nodesIds", "%"+id+"%").setParameter("Id",id).setParameter("parentId",id).getResultList();
    }
}
