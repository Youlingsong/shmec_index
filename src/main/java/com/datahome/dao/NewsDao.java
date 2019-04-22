package com.datahome.dao;

import com.datahome.entity.NewsClassesEntity;
import com.datahome.entity.NewsEntity;

import java.util.List;

public interface NewsDao {

    List<NewsEntity> find_by_classesId_publishFlag_title_status(List<Integer> classesIds, String publishFlag, String title, String status, String sort, String order, Integer pageSize, Integer pageNumber);

    Integer findTotal_by_classesId_publishFlag_title_status(List<Integer> classesIds, String publishFlag, String title, String status);

    List<NewsClassesEntity> findNewsClasses_by_parentId(Integer parentId, String classname);

    List<Integer> findAllChildrenClasses_by_nodeId(String nodeIds);

    NewsClassesEntity findNewsClasses_by_id(Integer id);

    void deleteNewsBy_nodeIds(String nodeIds);

    void saveNewsClasses(NewsClassesEntity newsClassesEntity);

    void updateNewsClasses(NewsClassesEntity newsClassesEntity);

    void deleteNewsClasses(NewsClassesEntity newsClassesEntity);
}
