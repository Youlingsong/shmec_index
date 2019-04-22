package com.datahome.util;

import com.datahome.bean.GdnAgencyMgmtBean;

import java.util.LinkedHashMap;

public class GdnAgencyUtil {
    public static String findGdnAgencySql(String sql, GdnAgencyMgmtBean gdnAgencyMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
            sql += "  and i.district = :district  ";
            params.put("district", gdnAgencyMgmtBean.getDistrict());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnAgencyMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnAgencyMgmtBean.getName() + "%");
        }
      return sql;
    }
}
