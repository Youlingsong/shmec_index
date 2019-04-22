package com.datahome.util;

import com.datahome.bean.GdnClassMgmtBean;

import java.util.LinkedHashMap;

public class GdnClassUtil {
    public static String findGdnAgencySql(String sql, GdnClassMgmtBean gdnClassMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnClassMgmtBean.getNickName())) {
            sql += "  and i.nickName = :nickName ";
            params.put("nickName", gdnClassMgmtBean.getNickName());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
        if (!CommonUtil.isEmptyString(gdnClassMgmtBean.getName())) {
            sql += " and i.name like :name ";
            params.put("name", "%" + gdnClassMgmtBean.getName() + "%");
        }
        return sql;
    }
}
