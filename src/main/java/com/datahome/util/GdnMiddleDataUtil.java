package com.datahome.util;

import com.datahome.bean.GdnMiddleDataBean;

import java.util.LinkedHashMap;

public class GdnMiddleDataUtil {
    public static String findGdnAgencySql(String sql, GdnMiddleDataBean gdnMiddleDataBean, LinkedHashMap<String, Object> params) {
        if (!CommonUtil.isEmptyString(gdnMiddleDataBean.getMiddledataruleid())) {
            sql += "  and i.middledataruleid = :middledataruleid ";
            params.put("middledataruleid", gdnMiddleDataBean.getMiddledataruleid());
        }
//
        if (!CommonUtil.isEmptyString(gdnMiddleDataBean.getRemake())) {
            sql += " and i.remake like :remake ";
            params.put("remake", "%" + gdnMiddleDataBean.getRemake()+ "%");
        }

        if (gdnMiddleDataBean.getCityid()!=null) {
            sql += " and i.cityid = :cityid ";
            params.put("cityid",  gdnMiddleDataBean.getCityid());
        }

        if (gdnMiddleDataBean.getDatabatchid()!=null) {
            sql += " and i.databatchid = :databatchid ";
            params.put("databatchid",  gdnMiddleDataBean.getDatabatchid());
        }

        return sql;
    }
}
