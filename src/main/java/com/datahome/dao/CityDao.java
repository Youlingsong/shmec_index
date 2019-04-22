package com.datahome.dao;

import com.datahome.entity.CityEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 14:57
 */
public interface CityDao {

    List<Integer> findAllChildrenIdBy_nodeIds_cityStatus(String nodeIds, List<String> cityStatus);

    List<CityEntity> findAllChildrenBy_nodeIds_cityStatus(String nodeIds, List<String> cityStatus);

    Integer findCountby_ids_cityStatus(List<Integer> cityIds, String cityStatus);

    void updateCityStatus_by_parentId(Integer parentId, String cityStatus);

}
