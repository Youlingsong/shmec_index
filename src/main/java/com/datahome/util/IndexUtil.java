package com.datahome.util;

import com.datahome.entity.IndexEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description: 指标管理
 * @Date: Create in 2018/5/14 14:20
 */
public class IndexUtil {

    public static String findIndexSql(String sql, List<Integer> idList, String indexName, String indexStatus, Integer parentId, LinkedHashMap<String, Object> params) {

        if (parentId != null) {
            sql += "  and i.parentId = :parentId  ";
            params.put("parentId", parentId);
        }
        if (idList != null && idList.size() > 0) {
            sql += " and i.id in (:idList)  ";
            params.put("idList", idList);
        }

        if (indexName != null) {
            sql += " and i.name like :indexName ";
            params.put("indexName", "%" + indexName + "%");
        }
        if (indexStatus != null) {
            sql += " and i.indexStatus = :indexStatus ";
            params.put("indexStatus", indexStatus);
        }
        return sql;
    }


    public static String findIndexCitySql(String hql, Integer cityId, String cityStatus, Integer indexId, String indexStatus, LinkedHashMap<String, Object> params) {
        if (indexId != null) {
            hql += " and indexId = :indexId ";
            params.put("indexId", indexId);
        }
        if (cityId != null) {
            hql += " and cityEntity.id  = :cityId ";
            params.put("cityId", cityId);
        }
        if (cityStatus != null) {
            hql += " and cityEntity.cityStatus  = :cityStatus ";
            params.put("cityStatus", cityStatus);
        }
        if (indexStatus != null) {
            hql += " and indexEntity.indexStatus  = :indexStatus ";
            params.put("indexStatus", indexStatus);
        }
        return hql;
    }

    // 获取指标的相关信息
    public static Map<String, Object> getIndexEntityMap(IndexEntity indexEntity) {
        Map<String, Object> resultMap = new HashMap<>();
        if (indexEntity != null) {
            resultMap.put("unitsName", indexEntity.getIndexUnitsEntity().getUnitsName());
            resultMap.put("unitsId", indexEntity.getIndexUnitsEntity().getId());
            resultMap.put("name", indexEntity.getName());
            resultMap.put("indexStatus", indexEntity.getIndexStatus());
            resultMap.put("id", indexEntity.getId());
            resultMap.put("parentId", indexEntity.getParentId());
            resultMap.put("nodeIds", indexEntity.getNodeIds());
            resultMap.put("evaluationStandards", indexEntity.getEvaluationStandards());
        }
        return resultMap;
    }
}
