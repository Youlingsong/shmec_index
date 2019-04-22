package com.datahome.service;

import com.datahome.bean.IndexMgmtBean;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/10 15:51
 */
public interface IndexMgmtService {

    String save(IndexMgmtBean indexMgmtBean) throws Exception;

    String find(IndexMgmtBean indexMgmtBean);

    String finds(IndexMgmtBean indexMgmtBean);

    String update(IndexMgmtBean indexMgmtBean);

    String combox(IndexMgmtBean indexMgmtBean);

    String findALlIndex(IndexMgmtBean indexMgmtBean);

    String findIndex_by_cityId(IndexMgmtBean indexMgmtBean);

    String exportExcelIndex(IndexMgmtBean indexMgmtBean, HttpServletResponse response);

    String delete(IndexMgmtBean indexMgmtBean);
}
