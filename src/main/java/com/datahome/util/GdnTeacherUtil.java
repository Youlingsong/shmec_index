package com.datahome.util;

import com.datahome.bean.GdnTeacherMgmtBean;

import java.util.LinkedHashMap;

public class GdnTeacherUtil {
    public static String findGdnAgencySql(String sql, GdnTeacherMgmtBean gdnTeacherMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnTeacherMgmtBean.getEmail())) {
            sql += "  and i.email = :email  ";
            params.put("email", gdnTeacherMgmtBean.getEmail());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnTeacherMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnTeacherMgmtBean.getName() + "%");
        }
        return sql;
    }
}
