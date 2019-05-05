package com.datahome.service.impl;

import com.datahome.bean.IndexGroupMgmtBean;
import com.datahome.entity.IndexGroupEntity;
import com.datahome.repository.IndexGroupRepository;
import com.datahome.repository.IndexRepository;
import com.datahome.service.IndexGroupMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @Author xl
 * @Description: 指标单位管理
 * @Date: Create in 2018/5/4 19:11
 */

@Service
public class IndexGroupMgmtServiceImpl implements IndexGroupMgmtService {

    @Resource
    private IndexGroupRepository indexGroupDao;

    @Resource
    private IndexRepository indexDao;

    @Override
    public String find(IndexGroupMgmtBean indexGroupMgmtBean) {

        Optional<IndexGroupEntity> optionalIndexUnitsEntity = indexGroupDao.findById(indexGroupMgmtBean.getId());
        if (!optionalIndexUnitsEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        return CommonUtil.format(2000, optionalIndexUnitsEntity.get());
    }

    @Override
    public String finds(IndexGroupMgmtBean indexGroupMgmtBean) {
        return CommonUtil.format(2000, indexGroupDao.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(IndexGroupMgmtBean indexGroupMgmtBean) {

        String unitsName = indexGroupMgmtBean.getGroupName();
        if (indexGroupDao.findByName(unitsName) != null) {
            return CommonUtil.format(4200, "分组名称已存在！");
        }

        IndexGroupEntity indexGroupEntity = new IndexGroupEntity();
        indexGroupEntity.setName(unitsName);
        indexGroupEntity.setSaveTime(new Date());
        indexGroupDao.save(indexGroupEntity);

        //添加分组与指标的中间表


        return CommonUtil.format(2000, "添加成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(IndexGroupMgmtBean indexGroupMgmtBean) {
        Optional<IndexGroupEntity> optionalIndexGroupEntity = indexGroupDao.findById(indexGroupMgmtBean.getId());
        if (!optionalIndexGroupEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }

        IndexGroupEntity indexGroupEntity = optionalIndexGroupEntity.get();
        indexGroupDao.deleteIndexGroupIndexBy_groupId(indexGroupEntity.getId());

        //删除指标分组
        indexGroupDao.delete(indexGroupEntity);

        return CommonUtil.format(2000, "删除成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(IndexGroupMgmtBean indexGroupMgmtBean) {
        Optional<IndexGroupEntity> optionalIndexGroupEntity = indexGroupDao.findById(indexGroupMgmtBean.getId());
        if (!optionalIndexGroupEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexGroupEntity indexGroupEntity = optionalIndexGroupEntity.get();

        String groupName = indexGroupMgmtBean.getGroupName();

        IndexGroupEntity indexGroupEntity1 = indexGroupDao.findByName(groupName);
        if (indexGroupEntity1 != null && indexGroupEntity1.getId().equals(indexGroupEntity.getId())) {
            return CommonUtil.format(4200, "分组名称已存在！");
        }

        indexGroupEntity.setName(groupName);
        indexGroupDao.save(indexGroupEntity);

        return CommonUtil.format(2000, "修改成功！");
    }
}
