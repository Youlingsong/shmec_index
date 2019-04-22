package com.datahome.service.impl;

import com.datahome.bean.IndexUnitsMgmtBean;
import com.datahome.entity.IndexEntity;
import com.datahome.entity.IndexUnitsEntity;
import com.datahome.repository.IndexRepository;
import com.datahome.repository.IndexUnitsRepository;
import com.datahome.service.IndexUnitsMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author xl
 * @Description: 指标单位管理
 * @Date: Create in 2018/5/4 19:11
 */

@Service
public class IndexUnitsMgmtServiceImpl implements IndexUnitsMgmtService {

    @Resource
    private IndexUnitsRepository indexUnitsDao;

    @Resource
    private IndexRepository indexDao;

    @Override
    public String find(IndexUnitsMgmtBean indexUnitsMgmtBean) {

        Optional<IndexUnitsEntity> optionalIndexUnitsEntity = indexUnitsDao.findById(indexUnitsMgmtBean.getId());
        if (!optionalIndexUnitsEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        return CommonUtil.format(2000, optionalIndexUnitsEntity.get());
    }

    @Override
    public String finds(IndexUnitsMgmtBean indexUnitsMgmtBean) {
        return CommonUtil.format(2000, indexUnitsDao.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(IndexUnitsMgmtBean indexUnitsMgmtBean) {

        String unitsName = indexUnitsMgmtBean.getUnitsName();
        if (indexUnitsDao.findByUnitsName(unitsName) != null) {
            return CommonUtil.format(4200, "单位名称已存在！");
        }

        IndexUnitsEntity indexUnitsEntity = new IndexUnitsEntity();
        indexUnitsEntity.setUnitsName(unitsName);
        indexUnitsEntity.setSaveTime(new Date());
        indexUnitsDao.save(indexUnitsEntity);

        return CommonUtil.format(2000, "添加成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(IndexUnitsMgmtBean indexUnitsMgmtBean) {
        Optional<IndexUnitsEntity> optionalIndexUnitsEntity = indexUnitsDao.findById(indexUnitsMgmtBean.getId());
        if (!optionalIndexUnitsEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexUnitsEntity indexUnitsEntity = optionalIndexUnitsEntity.get();
        List<IndexEntity> list = indexDao.finds_by_ids_unitsId_indexName(null, indexUnitsEntity.getId(), null);
        if (list != null && list.size() > 0) {
            return CommonUtil.format(4200, "该数据单位已被使用，无法删除！");
        }

        //删除计量单位
        indexUnitsDao.delete(indexUnitsEntity);

        return CommonUtil.format(2000, "删除成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(IndexUnitsMgmtBean indexUnitsMgmtBean) {
        Optional<IndexUnitsEntity> optionalIndexUnitsEntity = indexUnitsDao.findById(indexUnitsMgmtBean.getId());
        if (!optionalIndexUnitsEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexUnitsEntity indexUnitsEntity = optionalIndexUnitsEntity.get();


        String unitsName = indexUnitsMgmtBean.getUnitsName();

        if (indexUnitsDao.findByUnitsName(unitsName) != null) {
            return CommonUtil.format(4200, "计量单位名称已存在！");
        }

        indexUnitsEntity.setUnitsName(unitsName);
        indexUnitsDao.save(indexUnitsEntity);

        return CommonUtil.format(2000, "修改成功！");
    }
}
