package com.datahome.util;

import com.datahome.bean.IntermediateRuleBean;

import java.util.LinkedHashMap;

public class IntermediateRuleUtil {
    public static String findIntermediateRuleSql(String sql, IntermediateRuleBean intermediateRuleBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(intermediateRuleBean.getName())) {
            sql += "  and i.name = :name  ";
            params.put("name", intermediateRuleBean.getName());
        }
        if (!CommonUtil.objectEmpty(intermediateRuleBean.getSqlsentence())) {
            sql += " and i.sqlsentence = :sqlsentence   ";
            params.put("sqlsentence", intermediateRuleBean.getSqlsentence());
        }
      return sql;
    }
}
