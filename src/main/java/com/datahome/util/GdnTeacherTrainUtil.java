package com.datahome.util;

import com.datahome.bean.GdnTeacherTrainMgmtBean;

import java.util.LinkedHashMap;

public class GdnTeacherTrainUtil {
    public static String findGdnAgencySql(String sql, GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnTeacherTrainMgmtBean.getLevel())) {
            sql += "  and i.level = :level  ";
            params.put("level", gdnTeacherTrainMgmtBean.getLevel());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnTeacherTrainMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnTeacherTrainMgmtBean.getName() + "%");
        }
        return sql;
    }
}
