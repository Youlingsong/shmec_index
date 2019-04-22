package com.datahome.dao.impl;

import com.datahome.dao.NewsDao;
import com.datahome.entity.NewsClassesEntity;
import com.datahome.entity.NewsEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.NewsUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @Resource
    private NewsUtil newsUtil;

    @Resource
    private EntityManager entityManager;


    @Override
    public List<NewsEntity> find_by_classesId_publishFlag_title_status(List<Integer> classesId, String publishFlag, String title, String status, String sort, String order, Integer pageSize, Integer pageNumber) {

        String hql = "from NewsEntity where 1=1  ";
        hql = newsUtil.findHql(hql, classesId, publishFlag, title, status);

        //排序
        if (order == null) order = " DESC ";
        if (sort == null) sort = " publishTime ";
        hql += "  order by priority ASC , " + sort + " " + order + "  ";

        Query query = entityManager.createQuery(hql);
        newsUtil.setQuery(query, classesId, publishFlag, title, status);
        //分页
        //分页
        CommonUtil.page(query, pageNumber, pageSize);

        return query.getResultList();
    }

    @Override
    public Integer findTotal_by_classesId_publishFlag_title_status(List<Integer> classesId, String publishFlag, String title, String status) {
        String hql = "select count(*)  from age06_news where 1=1 ";
        hql = newsUtil.findHql(hql, classesId, publishFlag, title, status);
        Query query = entityManager.createNativeQuery(hql);
        newsUtil.setQuery(query, classesId, publishFlag, title, status);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public List<NewsClassesEntity> findNewsClasses_by_parentId(Integer parentId, String classes) {
        String hql = " from NewsClassesEntity where 1=1 ";
        if (parentId != null) {
            hql += " and parentId =:parentId  ";
        }
        if (parentId != null && parentId == 0 && classes != null) {
            hql += " and classes =:classes ";
        }

        //排序
        hql = CommonUtil.order(hql, null, null);

        Query query = entityManager.createQuery(hql);
        if (parentId != null) {
            query.setParameter("parentId", parentId);
        }
        if (parentId != null && parentId == 0 && classes != null) {
            query.setParameter("classes", classes);
        }
        return query.getResultList();
    }

    @Override
    public List<Integer> findAllChildrenClasses_by_nodeId(String nodeIds) {
        String hql = " select id from  age06_newsclasses where nodeids like :nodeIds ";
        return entityManager.createNativeQuery(hql).setParameter("nodeIds", nodeIds + "%").getResultList();
    }


    @Override
    public NewsClassesEntity findNewsClasses_by_id(Integer id) {
        return entityManager.find(NewsClassesEntity.class, id);
    }

    @Override
    public void deleteNewsBy_nodeIds(String nodeIds) {
        String sql = " delete from  age06_news where classesId in ( select id from  age06_newsclasses where nodeids like :nodeIds ) ";
        entityManager.createNativeQuery(sql).setParameter("nodeIds", nodeIds + "%").executeUpdate();
    }

    @Override
    public void saveNewsClasses(NewsClassesEntity newsClassesEntity) {
        entityManager.persist(newsClassesEntity);
    }

    @Override
    public void updateNewsClasses(NewsClassesEntity newsClassesEntity) {
        entityManager.merge(newsClassesEntity);
    }

    @Override
    public void deleteNewsClasses(NewsClassesEntity newsClassesEntity) {
        entityManager.remove(newsClassesEntity);
    }
}
