package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.CityDao;
import com.datahome.entity.CityEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/9 9:44
 */
public interface CityRepository extends BaseDao<CityEntity, Integer>, CityDao {

    List<CityEntity> findByParentIdAndCityStatusIn(Integer parentId, List<String> cityStatus);

    CityEntity findByCityNameAndParentId(String cityName, Integer parentId);

    CityEntity findByCityName(String cityName);

    List<CityEntity> findByCityStatus(String CityStatus);

    List<CityEntity> findByCityStatusAndId(String cityStatus, Integer id);

    List<CityEntity> findALLByCityStatusAndIdIn(String cityStatus, List<Integer> citys);

    List<CityEntity> findAllByCityStatusInOrderBySaveTime(List<String> cityStatus);
}
