package com.datahome.service.impl;

import com.datahome.bean.IndexDataMgmtBean;
import com.datahome.entity.IndexDataEntity;
import com.datahome.repository.IndexDataReposstory;
import com.datahome.service.IndexDataMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mackson on 2019/5/5 11:25
 */
@Service
public class IndexDataServiceImpl implements IndexDataMgmtService {

    @Resource
    private IndexDataReposstory indexDataDao;

    @Override
    public String find(IndexDataMgmtBean indexDataMgmtBean) {
        return null;
    }

    @Override
    public String finds(IndexDataMgmtBean indexDataMgmtBean) {
        return null;
    }

    @Override
    public String update(IndexDataMgmtBean indexDataMgmtBean) {
        return null;
    }

    @Transactional
    @Override
    public String saveIndexData(IndexDataMgmtBean indexDataMgmtBean) {
        IndexDataEntity indexDataEntity = new IndexDataEntity();
        CommonUtil.exchangeObj(indexDataMgmtBean,indexDataEntity);
        indexDataDao.save(indexDataEntity);
        return CommonUtil.format(2000, "添加成功！");
    }

    @Override
    public String exportExcelData(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
