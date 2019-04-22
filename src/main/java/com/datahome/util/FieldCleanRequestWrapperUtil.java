package com.datahome.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 字段清理Util
 * 除首尾空格、\t、\v、\f
 * 将""替换为null
 * 转义html
 */

public class FieldCleanRequestWrapperUtil extends HttpServletRequestWrapper {

    public FieldCleanRequestWrapperUtil(HttpServletRequest request) {
        super(request);
    }


    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!name.endsWith("Bean")) return CommonUtil.cleanField(value, false);
        return value;
    }

    public String[] getParameterValues(String name) {
        //获取之前的values
        String[] values = super.getParameterValues(name);

        //判断
        if (values == null) return null;

        if (!name.endsWith("Bean")) return getNewValues(values);

        return values;
    }

    public Map<String, String[]> getParameterMap() {
        //获取之前的Map
        Map<String, String[]> map = super.getParameterMap();

        //判断
        if (map == null) return null;

        //复写
        Map<String, String[]> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {

            String name = entry.getKey();
            String[] values = entry.getValue();

            if (!name.endsWith("Bean")) newMap.put(name, getNewValues(values));
            else newMap.put(name, values);
        }

        return newMap;
    }

    //复写values
    private String[] getNewValues(String[] values) {
        String[] newValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            newValues[i] = CommonUtil.cleanField(value, false);
        }

        return newValues;
    }
}
