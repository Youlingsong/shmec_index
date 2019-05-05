package com.datahome.dao;

import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/7 15:21
 */
public interface IndexDao {

    List<IndexEntity> find_by_idList_indexName_indexStatus_parentId(List<Integer> allowReadIdList, String indexName, String indexStatus, Integer parentId, String order, String sort, Integer pageSize, Integer pageNumber);

    Integer findTotal_by_idList_indexName_indexStatus_parentId(List<Integer> allowReadIdList, String indexName, String indexStatus, Integer parentId);

    void updateIndexStatus_by_nodeIds(String nodesIds, String indexStatus);

    List<IndexEntity> finds_by_ids_unitsId_indexName(List<Integer> ids, Integer unitsId, String indexName);

    List<IndexCityEntity> findIndexCity_by_cityId_indexId(Integer cityId, Integer indexId);

    void saveIndexCity(IndexCityEntity indexCityEntity);

    Integer findCountBy_indexStatus_ids(String indexStatus, List<Integer> ids);

    List<IndexEntity> findAllIndexs();


    List<IndexEntity> findByIdOrodeIdorparentid(Integer id);
}
