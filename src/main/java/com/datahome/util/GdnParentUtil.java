package com.datahome.util;

import com.datahome.bean.GdnParentMgmtBean;

import java.util.LinkedHashMap;

public class GdnParentUtil {
    public static String findGdnAgencySql(String sql, GdnParentMgmtBean gdnParentMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnParentMgmtBean.getEmail())) {
            sql += "  and i.email = :email  ";
            params.put("email", gdnParentMgmtBean.getEmail());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnParentMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnParentMgmtBean.getName() + "%");
        }
        return sql;
    }

}
