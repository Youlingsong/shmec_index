package com.datahome.util;

import com.datahome.bean.GdnChildMgmtBean;

import java.util.LinkedHashMap;

public class GdnChildUtil {
    public static String findGdnAgencySql(String sql, GdnChildMgmtBean gdnChildMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnChildMgmtBean.getXX())) {
            sql += "  and i.xx = :xx  ";
            params.put("xx", gdnChildMgmtBean.getXX());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnChildMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnChildMgmtBean.getName() + "%");
        }
        return sql;
    }

}
