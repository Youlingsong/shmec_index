package com.datahome.service;

import com.datahome.bean.OpenTimeMgmtBean;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/16 14:49
 */
public interface OpenTimeMgmtService {

    String find(OpenTimeMgmtBean openTimeMgmtBean);

    String update(OpenTimeMgmtBean openTimeMgmtBean);
}
