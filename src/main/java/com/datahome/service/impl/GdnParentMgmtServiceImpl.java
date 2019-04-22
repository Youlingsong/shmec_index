package com.datahome.service.impl;

import com.datahome.bean.GdnChildParentMgmtBean;
import com.datahome.bean.GdnParentMgmtBean;
import com.datahome.entity.GdnChildEntity;
import com.datahome.entity.GdnChildParentEntity;
import com.datahome.entity.GdnParentEntity;
import com.datahome.repository.GdnChildMgmtRepository;
import com.datahome.repository.GdnChildParentMgmtRepository;
import com.datahome.repository.GdnParentMgmtRepository;
import com.datahome.service.GdnParentMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnParentMgmtServiceImpl implements GdnParentMgmtService {
    @Resource
    private GdnParentMgmtRepository gdnParentMgmtDao;

    @Resource
    private GdnChildMgmtRepository gdnChildMgmtDao;

    @Resource
    private GdnChildParentMgmtRepository gdnChildParentMgmtDao;
    @Override
    public String save(GdnParentMgmtBean gdnParentMgmtBean) {

        GdnParentEntity gdnParentEntity = new GdnParentEntity();
        CommonUtil.exchangeObj(gdnParentEntity,gdnParentMgmtBean);
        gdnParentEntity.setCreateTime(new Date());
        gdnParentEntity.setUpdateTime(new Date());
        gdnParentMgmtDao.save(gdnParentEntity);
        return CommonUtil.format(2000, "父母添加成功！");
    }

    @Override
    public String update(GdnParentMgmtBean gdnParentMgmtBean) {
        Optional<GdnParentEntity> optionGdnParentEntity= gdnParentMgmtDao.findById(gdnParentMgmtBean.getId());
        if (!optionGdnParentEntity.isPresent()) {
            return CommonUtil.format(4200, "父母不存在！");
        }
        GdnParentEntity gdnParentEntity = optionGdnParentEntity.get();
        CommonUtil.exchangeObj(gdnParentMgmtBean, gdnParentEntity);
        gdnParentEntity.setUpdateTime(new Date());
        gdnParentMgmtDao.save(gdnParentEntity);
        return CommonUtil.format(2000, "父母更新成功！");
    }

    @Override
    public String find(GdnParentMgmtBean gdnParentMgmtBean) {
        Optional<GdnParentEntity> optionGdnParentEntity = gdnParentMgmtDao.findById(gdnParentMgmtBean.getId());
        if (!optionGdnParentEntity.isPresent()) {
            return CommonUtil.format(4200, "父母不存在！");
        }
        GdnParentEntity gdnParentEntity = optionGdnParentEntity.get();
        return CommonUtil.format(2000, gdnParentEntity);
    }

    @Override
    public String finds(GdnParentMgmtBean gdnParentMgmtBean) {
        List<GdnParentEntity> gdnParentEntities = gdnParentMgmtDao.findby_name_district_Level(gdnParentMgmtBean);
        return CommonUtil.format(2000, gdnParentEntities);
    }

    @Override
    public String add(GdnChildParentMgmtBean gdnChildParentMgmtBean) {
        Optional<GdnChildEntity> optionGdnChildEntity = gdnChildMgmtDao.findById(gdnChildParentMgmtBean.getGdnchildid());
        if (!optionGdnChildEntity.isPresent()) {
            return CommonUtil.format(4200, "幼儿不存在！");
        }
        Optional<GdnParentEntity> optionGdnParentEntity = gdnParentMgmtDao.findById(gdnChildParentMgmtBean.getGdnparentid());
        if (!optionGdnParentEntity.isPresent()) {
            return CommonUtil.format(4200, "父母不存在！");
        }
        GdnChildParentEntity gdnChildParentEntity = new GdnChildParentEntity();
        CommonUtil.exchangeObj(gdnChildParentMgmtBean, gdnChildParentEntity);

        GdnChildEntity gdnChildEntity = optionGdnChildEntity.get();
        GdnParentEntity gdnParentEntity = optionGdnParentEntity.get();

        gdnChildParentEntity.setGdnParentEntity(gdnParentEntity);
        gdnChildParentEntity.setGdnChildEntity(gdnChildEntity);
        gdnChildParentEntity.setCreateTime(new Date());
        gdnChildParentEntity.setUpdateTime(new Date());
        gdnChildParentMgmtDao.save(gdnChildParentEntity);
        return  CommonUtil.format(2000, "父母幼儿中间表保存成功");
    }

    @Override
    public String serach(GdnChildParentMgmtBean gdnChildParentMgmtBean) {
        Optional<GdnChildParentEntity> optionGdnChildParentEntity = gdnChildParentMgmtDao.findById(gdnChildParentMgmtBean.getId());
        if (!optionGdnChildParentEntity.isPresent()) {
            return CommonUtil.format(4200, "父母幼儿中间表不存在！");
        }
        return CommonUtil.format(2000, optionGdnChildParentEntity.get());
    }
}
