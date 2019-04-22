package com.datahome.service.impl;

import com.datahome.bean.IndexDataBean;
import com.datahome.entity.CityEntity;
import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexDataEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.repository.CityRepository;
import com.datahome.repository.IndexDataReposstory;
import com.datahome.repository.IndexRepository;
import com.datahome.service.IndexDataService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/15 14:34
 */

@Service
public class IndexDataServiceImpl implements IndexDataService {

    @Resource
    private IndexDataReposstory indexDataDao;

    @Resource
    private IndexRepository indexDao;

    @Resource
    private CityRepository cityDao;



    private String cityStatus = null;

    //查询前台指标
    @Override
    public String finds(IndexDataBean indexDataBean) {
        List<Integer> indexIds = indexDataBean.getIndexIds();
        List<String> years = indexDataBean.getYears();
        List<Integer> cityIds = indexDataBean.getCityIds();

        Collections.sort(years);

        //返回值
        List<String> yearList = new ArrayList(years);
        List<Object> cityOrDepartments = new ArrayList<>();


        Map<String, List<Map<String, Object>>> indexMap = null;
        List<Map<String, Object>> dataList = null;
        Map<String, Map<String, Map<String, String>>> indexCityData = new HashMap<>();
        List<Object> indexNames = new ArrayList<>();
        List<Map<String, Object>> indexInfos = new ArrayList<>();
        Map<String, Map<String, Object>> indexAverageMap = new HashMap<>();

        //查询
        List<IndexCityEntity> indexCityList = indexDataDao.findIndexCityIdList_cityIds_cityStatus_indexIds_indexStatus(cityIds, cityStatus, indexIds, "1");

        List<Integer> indexCityIds = new ArrayList<>();
        Map<Integer, IndexCityEntity> indexCityMap = new HashMap<>();

        for (IndexCityEntity indexCityEntity : indexCityList) {
            indexCityMap.put(indexCityEntity.getId(), indexCityEntity);
            indexCityIds.add(indexCityEntity.getId());
        }

        if (indexCityIds.size() == 0) {
            return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";
        }

        //初始化返回数据（地区、年份、指标的乘积）
        Map<String, Object> dataMap = initDataMap(indexAverageMap, indexIds, years, cityIds, cityOrDepartments, indexNames, indexInfos, indexCityData);


        //查询 有效数据
        List<IndexDataEntity> indexDataList = indexDataDao.findIndexDatas_indexCityIds_years_indexDataStatus_ids(indexCityIds, years, "1", null);

        for (IndexDataEntity indexData : indexDataList) {

            IndexCityEntity indexCityEntity = indexCityMap.get(indexData.getIndexCityEntity().getId());
            IndexEntity indexEntity = indexCityEntity.getIndexEntity();
            CityEntity cityEntity = indexCityEntity.getCityEntity();
            String cityName = null;
            String departmentName = null;
            if (cityEntity != null) {
                cityName = cityEntity.getCityName();
            }

            String indexName = indexEntity.getName() + "(" + indexEntity.getIndexUnitsEntity().getUnitsName() + ")";
            Double value = indexData.getValue();
            String year = indexData.getYear();

            indexMap = (Map<String, List<Map<String, Object>>>) dataMap.get(year);

            dataList = indexMap.get(indexName);
            for (Map<String, Object> m : dataList) {
                if ((m.containsValue(cityName) || m.containsValue(departmentName)) && !"null".equals(String.valueOf(value).trim())) {
                    m.put("value", value);
                }
            }

            //修改市、区县级数据
            Map<String, String> map = indexCityData.get(indexName).get(cityName);
            if (map == null) {
                map = indexCityData.get(indexName).get(departmentName);
            }
            map.put(year, String.valueOf(value));
        }

        //返回值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("indexNameList", indexNames);
        resultMap.put("indexInfos", indexInfos);
        resultMap.put("yearList", yearList);
        resultMap.put("cityList", cityOrDepartments);
        resultMap.put("indexAverageMap", indexAverageMap);
        resultMap.put("otherInfoList", dataMap.remove("otherInfoList"));
        resultMap.put("dataMap", dataMap);
        resultMap.put("indexCityData", indexCityData);
        return CommonUtil.format(2000, resultMap);
    }

    private Map<String, Object> initDataMap(Map<String, Map<String, Object>> indexAverageMap, List<Integer> indexIds, List<String> years, List<Integer> cityIds, List<Object> citys, List<Object> indexNameList, List<Map<String, Object>> indexInfos, Map<String, Map<String, Map<String, String>>> indexNameMap) {

        //查询所有的城市
        List<CityEntity> cityEntitieList = null;
        if (cityIds != null) {
            cityEntitieList = cityDao.findALLByCityStatusAndIdIn("1", cityIds);
        }

        //查询所有的指标
        List<IndexEntity> indexEntities = indexDao.find_by_idList_indexName_indexStatus_parentId(indexIds, null, "1", null, null, null, 99999, 1);

        Map<String, List<Map<String, Object>>> indexMap = null;
        Map<String, Object> avgeValueMap = null;
        List<Map<String, Object>> dataList = null;
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> map = null;

        for (IndexEntity indexEntity : indexEntities) {
            String indexN = indexEntity.getName() + "(" + indexEntity.getIndexUnitsEntity().getUnitsName() + ")";
            //指标名称 集合
            indexNameList.add(indexN);

            //指标基本信息 集合
            Map<String, Object> map2 = new HashMap<>();
            map2.put("indexName", indexN);
            map2.put("description", indexEntity.getDescription());
            map2.put("grossScore", indexEntity.getGrossScore());
            map2.put("evaluationStandards", indexEntity.getEvaluationStandards());
            indexInfos.add(map2);
        }

        List<Map<String, Object>> resultOtherInfoList = new ArrayList<>();

        for (String year : years) {
            indexMap = new HashMap<>();

            for (IndexEntity indexEntity : indexEntities) {
                String indexN = indexEntity.getName() + "(" + indexEntity.getIndexUnitsEntity().getUnitsName() + ")";

                //获取平均值  该平均值不包含全国数据
                if (indexAverageMap.containsKey(year)) {
                    avgeValueMap = indexAverageMap.get(year);
                } else {
                    avgeValueMap = new HashMap<>();
                }

                //查询该指标的平均值
                if (!avgeValueMap.containsKey(indexN)) {
                    avgeValueMap.put(indexN, indexDataDao.findValueSum_indexId_year(indexEntity.getId(), year));
                    indexAverageMap.put(year, avgeValueMap);
                }


                dataList = new ArrayList<>();

                //处理 市、区县 数据
                if (cityEntitieList != null) {
                    for (CityEntity cityEntity : cityEntitieList) {
                        String cityName = cityEntity.getCityName();
                        if (!citys.contains(cityName)) {
                            citys.add(cityName);
                        }


                        map = new HashMap<>();
                        map.put("cityId", cityEntity.getId());
                        map.put("cityName", cityName);
                        map.put("value", "");
                        dataList.add(map);


                        //初始化省份地图数据
                        Map<String, String> valueMap = new HashMap<>();
                        valueMap.put(year, "");
                        Map<String, Map<String, String>> cityMap = null;
                        if (!indexNameMap.containsKey(indexN)) {
                            cityMap = new HashMap<>();
                            cityMap.put(cityName, valueMap);
                            indexNameMap.put(indexN, cityMap);
                        } else {
                            cityMap = indexNameMap.get(indexN);
                            if (cityMap.containsKey(cityName)) {
                                Map<String, String> valueMap2 = cityMap.get(cityName);
                                if (!valueMap2.containsKey(year)) {
                                    valueMap2.put(year, "");
                                }
                            } else {
                                cityMap.put(cityName, valueMap);
                            }
                        }
                        indexNameMap.put(indexN, cityMap);

                    }
                }
                indexMap.put(indexN, dataList);
            }
            dataMap.put(year, indexMap);


            // 指标列表数据
            Map<String, Object> resultOtherInfo = new HashMap<>();
            List<Map<String, Object>> otherInfo = new ArrayList<>();
            Map<String, Object> otherInfoMap = null;
            if (cityEntitieList != null) {

                for (CityEntity cityEntity : cityEntitieList) {
                    otherInfoMap = new HashMap<>();
                    String cityName = cityEntity.getCityName();

                    //查询
                    List<IndexCityEntity> indexCityList = indexDataDao.findIndexCityIdList_cityIds_cityStatus_indexIds_indexStatus(Arrays.asList(cityEntity.getId()),"1", indexIds, "1");
                    List<Integer> indexCityIds = new ArrayList<>();
                    Map<Integer, IndexCityEntity> indexCityMap = new HashMap<>();
                    for (IndexCityEntity indexCityEntity : indexCityList) {
                        indexCityMap.put(indexCityEntity.getId(), indexCityEntity);
                        indexCityIds.add(indexCityEntity.getId());
                    }

                    List<IndexDataEntity> indexDataEntities = indexDataDao.findIndexDatas_indexCityIds_years_indexDataStatus_ids(indexCityIds, Arrays.asList(year), "1", null);

                    List<Map<String, Object>> indexList = new ArrayList<>();
                    for (IndexDataEntity indexDataEntity : indexDataEntities) {
                        Map<String, Object> indexDataMap = new HashMap<>();
                        indexDataMap.put("indexInfo", indexDataEntity.getIndexCityEntity().getIndexEntity().getName());
                        indexDataMap.put("indexScore", indexDataEntity.getValue());
                        indexList.add(indexDataMap);
                    }
                    otherInfoMap.put("cityName", cityName);
                    otherInfoMap.put("indexList", indexList);
                    otherInfo.add(otherInfoMap);
                }
            }

            resultOtherInfo.put("year", year);
            resultOtherInfo.put("otherInfo", otherInfo);
            resultOtherInfoList.add(resultOtherInfo);
        }
        dataMap.put("otherInfoList", resultOtherInfoList);

        return dataMap;
    }
}
