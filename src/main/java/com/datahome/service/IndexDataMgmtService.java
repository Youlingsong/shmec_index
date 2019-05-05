package com.datahome.service;

import com.datahome.bean.IndexDataMgmtBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/21 15:05
 */
public interface IndexDataMgmtService {

    String find(IndexDataMgmtBean indexDataMgmtBean);

    String finds(IndexDataMgmtBean indexDataMgmtBean);

    String update(IndexDataMgmtBean indexDataMgmtBean);

    String saveIndexData(IndexDataMgmtBean indexDataMgmtBean);

    String exportExcelData(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest request, HttpServletResponse response);

    String delete(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest request, HttpServletResponse response);
}
