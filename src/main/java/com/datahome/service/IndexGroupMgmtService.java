package com.datahome.service;

import com.datahome.bean.IndexGroupMgmtBean;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 19:02
 */
public interface IndexGroupMgmtService {

    String find(IndexGroupMgmtBean indexGroupMgmtBean);

    String finds(IndexGroupMgmtBean indexGroupMgmtBean);

    String save(IndexGroupMgmtBean indexGroupMgmtBean);

    String delete(IndexGroupMgmtBean indexGroupMgmtBean);

    String update(IndexGroupMgmtBean indexGroupMgmtBean);
}
