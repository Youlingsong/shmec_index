package com.datahome.service;

import com.datahome.bean.IndexBean;

/**
 * @Author xl
 * @Description: 前台指标 查询
 * @Date: Create in 2018/5/18 9:13
 */
public interface IndexService {

    String finds(IndexBean indexBean);

    String findsRootIndexs();
}
