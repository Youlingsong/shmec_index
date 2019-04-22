package com.datahome.dao.impl;

import com.datahome.dao.IndexDataDao;
import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexDataEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.IndexDataUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description: 指标数据管理
 * @Date: Create in 2018/5/7 15:28
 */

@Repository
public class IndexDataDaoImpl implements IndexDataDao {


    @Resource
    private IndexDataUtil indexDataUtil;

    @Resource
    private EntityManager entityManager;

    @Resource
    private DataSource dataSource;


    @Override
    public Integer findTotal_by_indexId_cityIds_year_indexDataStatus(Integer indexId, List<Integer> cityIds, String year, List<String> indexDataStatus) {

        String sql = "  select COUNT(DISTINCT (d)) from age06_indexdata d ,age06_index_city c,age06_city t where  c.cityid = t.id and d.indexCityid=c.id and c.indexid in ( select i2.id from age06_index i2 where 1=1 ";
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();
        sql = indexDataUtil.findIndexDataSql(sql, indexId, cityIds, year, indexDataStatus, paramsMap);
        Query query = entityManager.createNativeQuery(sql);
        //修改值
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return Integer.parseInt(query.getSingleResult().toString());
    }

    //
    @Override
    public List<Object[]> find_by_indexId_cityIds_year_indexDataStatus(Integer indexId, List<Integer> cityIds, String year, List<String> indexDataStatus, String order, String sort, Integer pageSize, Integer pageNumber) {

        String sql = " select DISTINCT d.id, d.indexdatastatus,d.value,d.year,i1.name,i1.nodeids ,t.cityname,s.unitsName,d.savetime,d.departmentId  from age06_indexdata d ,age06_index_city c ,age06_index i1,age06_city t ,age06_indexunits s where  c.indexid=i1.id and c.cityid = t.id  "
                + " and d.indexCityid=c.id  and i1.unitsid =s.id and  c.indexid in ( select i2.id from age06_index i2 where 1=1   ";

        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();
        sql = indexDataUtil.findIndexDataSql(sql, indexId, cityIds, year, indexDataStatus, paramsMap) + " order by d.savetime ASC ";
        Query query = entityManager.createNativeQuery(sql);
        //修改值
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }

        CommonUtil.page(query, pageNumber, pageSize);

        return query.getResultList();
    }

    @Override
    public List<IndexCityEntity> findIndexCityIdList_cityIds_cityStatus_indexIds_indexStatus(List<Integer> cityIds, String cityStatus, List<Integer> indexIds, String indexStatus) {
        String hql = "  from  IndexCityEntity  where  1=1 ";
        if (cityIds != null) {
            hql += " and cityEntity.id in( :cityIds) ";
        }
        if (cityStatus != null) {
            hql += " and cityEntity.cityStatus = :cityStatus ";
        }
        if (indexIds != null) {
            hql += " and indexEntity.id in ( :indexIds)  ";
        }
        if (indexStatus != null) {
            hql += " and indexEntity.indexStatus = :indexStatus ";
        }
        Query query = entityManager.createQuery(hql);
        if (cityIds != null) {
            query.setParameter("cityIds", cityIds);
        }
        if (cityStatus != null) {
            query.setParameter("cityStatus", cityStatus);
        }
        if (indexIds != null) {
            query.setParameter("indexIds", indexIds);
        }
        if (indexStatus != null) {
            query.setParameter("indexStatus", indexStatus);
        }
        return query.getResultList();
    }

    @Override
    public void updateIndexDataStatusBy_indexNodeIds(String indexDataStatus, String nodeIds) {
        String sql = "  update age06_indexdata set indexdatastatus= :indexDataStatus where indexcityid in (select  c.id from age06_index_city c,age06_index i  where i.nodeIds like :nodeIds and i.id = c.indexid ) ";
        entityManager.createNativeQuery(sql).setParameter("indexDataStatus", indexDataStatus).setParameter("nodeIds", nodeIds + "%").executeUpdate();
    }

    @Override
    public void updateIndexDataStatusBy_cityId(String indexDataStatus, Integer cityId) {
        String sql = "  update age06_indexdata set indexdatastatus= :indexDataStatus where indexcityid in (select  c.id from age06_index_city c,age06_city t  where t.id in (select id from age06_city t2 where t2.parentid = :cityId  or id  = :cityId )  and t.id = c.cityid ) ";
        entityManager.createNativeQuery(sql).setParameter("indexDataStatus", indexDataStatus).setParameter("cityId", cityId).executeUpdate();
    }

    @Override
    public List<IndexDataEntity> findBy_IndexDataStatus_cityIds(String indexDataStatus, List<Integer> cityIds) {
        String sql = " select d.* from age06_indexdata d ,age06_index_city c,age06_index i  where d.indexCityid = c.id and c.indexid = i.id and c.cityid in (:cityid)  and d.indexDataStatus = :indexDataStatus order by d.year DESC ,value DESC ";
        return entityManager.createNativeQuery(sql, IndexDataEntity.class).setParameter("indexDataStatus", indexDataStatus).setParameter("cityid", cityIds).setParameter("indexDataStatus", indexDataStatus).getResultList();
    }

    @Override
    public void batchSave(List<Map<String, Object>> datas) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String sql = " insert into age06_indexdata (indexCityId,indexDatastatus,year,saveTime,value) values(?,?,?,?,?) ";
            preparedStatement = connection.prepareStatement(sql);
            for (Map<String, Object> map : datas) {
                preparedStatement.setInt(1, Integer.parseInt(map.get("indexCityId").toString()));
                preparedStatement.setString(2, map.get("indexDataStatus").toString());
                preparedStatement.setString(3, map.get("year").toString());
                preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
                preparedStatement.setDouble(5, Double.valueOf(map.get("value").toString()));
                preparedStatement.addBatch();
                count++;
                if (count % 1000 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
                System.out.println("count:" + count);
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void delete_by_indexcityid(IndexCityEntity indexCityEntity) {
        String sql = " delete from age06_indexdata where 1=1 and indexcityid = :indexcityid";
       entityManager.createNativeQuery(sql).setParameter("indexcityid", indexCityEntity.getId()).executeUpdate();

    }

    @Override
    public List<IndexDataEntity> findIndexDatas_indexCityIds_years_indexDataStatus_ids(List<Integer> indexCityIds, List<String> years, String indexDataStatus, List<Integer> ids) {
        String hql = " from IndexDataEntity where 1=1 ";
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap();
        hql = indexDataUtil.findIndexDataHql(hql, indexCityIds, years, indexDataStatus, ids, paramsMap);
        Query query = entityManager.createQuery(hql);

        //修改值
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();

            query.setParameter(key, value);
        }
        return query.getResultList();
    }

    @Override
    public Double findValueSum_indexId_year(Integer indexId, String year) {
        if (indexId == null || year == null) {
            return 0.0;
        }
        String sql = " select avg(cast(d.value as numeric)) from age06_index_city i ,age06_indexdata d,age06_city c  where i.id = d.indexcityid and c.id = i.cityid and c.levelcode != '0' and i.indexid = :indexId and d.year= :years  ";
        Query query = entityManager.createNativeQuery(sql);
        Object value = query.setParameter("indexId", indexId).setParameter("years", year).getSingleResult();
        if (value != null) {
            return Double.valueOf(value.toString().substring(0, 8));
        }
        return 0.0;
    }
}
