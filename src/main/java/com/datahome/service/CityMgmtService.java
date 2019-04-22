package com.datahome.service;

import com.datahome.bean.CityMgmtBean;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 15:07
 */
public interface CityMgmtService {

    String initCity() throws Exception;

    String save(CityMgmtBean cityMgmtBean);

    String combox(CityMgmtBean cityMgmtBean);

    String findAll(CityMgmtBean cityMgmtBean);

    String update(CityMgmtBean cityMgmtBean);
}
