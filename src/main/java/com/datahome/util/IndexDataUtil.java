package com.datahome.util;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/21 17:52
 */

@Component
public class IndexDataUtil {

    public String findIndexDataSql(String sql, Integer indexId, List<Integer> cityIds, String year, List<String> indexDataStatus, LinkedHashMap<String, Object> params) {

        if (indexId != null) {
            sql += " and i2.id = :indexId or i2.parentId = :indexId ";
            params.put("indexId", indexId);
        }
        sql += " )  ";

        if (year != null) {
            sql += " and d.year = :year ";
            params.put("year", year);
        }

        if (cityIds != null) {
            sql += " and c.cityid in (:cityIds) ";
            params.put("cityIds", cityIds);
        }

        if (indexDataStatus != null) {
            sql += " and d.indexDataStatus in(:indexDataStatus) ";
            params.put("indexDataStatus", indexDataStatus);
        }
        return sql;
    }


    public String findIndexDataHql(String hql, List<Integer> indexCityIds, List<String> years, String indexDataStatus, List<Integer> ids, LinkedHashMap<String, Object> params) {
        if (indexCityIds != null) {
            hql += " and indexCityEntity.id in(:cityIds)  ";
            params.put("cityIds", indexCityIds);
        }
        if (years != null) {
            hql += " and year in (:years) ";
            params.put("years", years);
        }
        if (indexDataStatus != null) {
            hql += " and indexDataStatus = :indexDataStatus ";
            params.put("indexDataStatus", indexDataStatus);
        }
        if (ids != null) {
            hql += " and id in (:ids)";
            params.put("ids", ids);
        }

        return hql;
    }


}
