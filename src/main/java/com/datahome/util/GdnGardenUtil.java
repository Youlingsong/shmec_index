package com.datahome.util;

import com.datahome.bean.GdnGardenMgmtBean;

import java.util.LinkedHashMap;

public class GdnGardenUtil {
    public static String findGdnAgencySql(String sql, GdnGardenMgmtBean gdnGardenMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnGardenMgmtBean.getDistrict())) {
            sql += "  and i.district = :district  ";
            params.put("district", gdnGardenMgmtBean.getDistrict());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnGardenMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnGardenMgmtBean.getName() + "%");
        }
        return sql;
    }

}
