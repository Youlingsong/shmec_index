package com.datahome.util;

import com.datahome.middledata.GdnMiddleDataModel;

import java.util.LinkedHashMap;

public class GdnMiddleDataUtil {
    public static String findGdnAgencySql(String sql, GdnMiddleDataModel gdnMiddleDataModel, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnMiddleDataModel.getKey())) {
            sql += "  and i.key = :key  ";
            params.put("key", gdnMiddleDataModel.getKey());
        }
//
        if (!CommonUtil.isEmptyString(gdnMiddleDataModel.getRemake())) {
            sql += " and i.remake like :remake ";
            params.put("remake", "%" + gdnMiddleDataModel.getRemake()+ "%");
        }
        return sql;
    }
}
