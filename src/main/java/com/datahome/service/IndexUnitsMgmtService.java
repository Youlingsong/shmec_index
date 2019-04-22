package com.datahome.service;

import com.datahome.bean.IndexUnitsMgmtBean;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 19:02
 */
public interface IndexUnitsMgmtService {

    String find(IndexUnitsMgmtBean indexUnitsMgmtBean);

    String finds(IndexUnitsMgmtBean indexUnitsMgmtBean);

    String save(IndexUnitsMgmtBean indexUnitsMgmtBean);

    String delete(IndexUnitsMgmtBean indexUnitsMgmtBean);

    String update(IndexUnitsMgmtBean indexUnitsMgmtBean);
}
