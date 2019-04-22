package com.datahome.dao;

import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexDataEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/7 15:26
 */
public interface IndexDataDao {

    Integer findTotal_by_indexId_cityIds_year_indexDataStatus(Integer indexId, List<Integer> cityId, String year, List<String> indexDataStatus);

    List<Object[]> find_by_indexId_cityIds_year_indexDataStatus(Integer indexId, List<Integer> cityIds, String year, List<String> indexDataStatus, String order, String sort, Integer pageSize, Integer pageNumber);

    List<IndexCityEntity> findIndexCityIdList_cityIds_cityStatus_indexIds_indexStatus(List<Integer> cityIds, String cityStatus, List<Integer> indexIds, String indexStatus);

    List<IndexDataEntity> findIndexDatas_indexCityIds_years_indexDataStatus_ids(List<Integer> indexCityIds, List<String> years, String indexDataStatus, List<Integer> ids);

    Double findValueSum_indexId_year(Integer indexId, String year);

    void updateIndexDataStatusBy_indexNodeIds(String indexDataStatus, String nodeIds);

    void updateIndexDataStatusBy_cityId(String indexDataStatus, Integer cityId);

    List<IndexDataEntity> findBy_IndexDataStatus_cityIds(String indexDataStatus, List<Integer> cityIds);

    void batchSave(List<Map<String, Object>> datas);

    void delete_by_indexcityid(IndexCityEntity indexCityEntity);
}
