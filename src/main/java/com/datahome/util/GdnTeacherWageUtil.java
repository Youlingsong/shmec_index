package com.datahome.util;

import com.datahome.bean.GdnTeacherWageMgmtBean;

import java.util.LinkedHashMap;

public class GdnTeacherWageUtil {
    public static String findGdnAgencySql(String sql, GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnTeacherWageMgmtBean.getSNDKH())) {
            sql += "  and i.SNDKH = :SNDKH  ";
            params.put("SNDKH", gdnTeacherWageMgmtBean.getSNDKH());
        }
//        if (CommonUtil.isEmptyString(gdnAgencyMgmtBean.getDistrict())) {
//            sql += " and i.id in (:idList)  ";
//            params.put("idList", idList);
//        }
//
//        if (!CommonUtil.isEmptyString(gdnTeacherWageMgmtBean.getName())) {
//            sql += " and i.name like :name ";
//            params.put("name", "%" + gdnTeacherWageMgmtBean.getName() + "%");
//        }
        return sql;
    }
}
