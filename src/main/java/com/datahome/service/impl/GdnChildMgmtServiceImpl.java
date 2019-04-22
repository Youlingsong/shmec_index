package com.datahome.service.impl;

import com.datahome.bean.GdnChildMgmtBean;
import com.datahome.entity.GdnChildEntity;
import com.datahome.entity.GdnClassEntity;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.repository.GdnChildMgmtRepository;
import com.datahome.repository.GdnClassMgmtRepository;
import com.datahome.repository.GdnGardenMgmtRepository;
import com.datahome.service.GdnChildMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnChildMgmtServiceImpl implements GdnChildMgmtService {
    @Resource
    private GdnChildMgmtRepository gdnChildMgmtDao;
    @Resource
    private GdnGardenMgmtRepository gdnGardenMgmtDao;
    @Resource
    private GdnClassMgmtRepository gdnClassMgmtDao;
    @Override
    public String save(GdnChildMgmtBean gdnChildMgmtBean) {
        GdnChildEntity gdnChildEntity = new GdnChildEntity();
        //班级id
        String gdnClassid = gdnChildMgmtBean.getGdnClassid();
        //园所id
        String gdnGardenid =  gdnChildMgmtBean.getGdnGardenid();

        Optional<GdnClassEntity> optionGdnClassEntity = gdnClassMgmtDao.findById(gdnClassid);
        if (!optionGdnClassEntity.isPresent()) {
           return CommonUtil.format(4200, "班级不存在！,请确认班级后再保存");
        }
        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnGardenid);
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！,请确认园所后再保存");
        }

        CommonUtil.exchangeObj(gdnChildMgmtBean, gdnChildEntity);
        gdnChildEntity.setCreateTime(new Date());
        gdnChildEntity.setUpdateTime(new Date());
        gdnChildMgmtDao.save(gdnChildEntity);
        return CommonUtil.format(2000, "幼儿添加成功！");

    }

    @Override
    public String update(GdnChildMgmtBean gdnChildMgmtBean) {
        Optional<GdnChildEntity> optionGdnChildEntity= gdnChildMgmtDao.findById(gdnChildMgmtBean.getId());
        if (!optionGdnChildEntity.isPresent()) {
            return CommonUtil.format(4200, "幼儿不存在！");
        }

        Optional<GdnClassEntity> optionGdnClassEntity = gdnClassMgmtDao.findById(gdnChildMgmtBean.getGdnClassid());
        if (!optionGdnClassEntity.isPresent()) {
            return CommonUtil.format(4200, "班级不存在！,请确认班级后再保存");
        }
        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnChildMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！,请确认园所后再保存");
        }
        GdnChildEntity gdnChildEntity = optionGdnChildEntity.get();
        CommonUtil.exchangeObj(gdnChildMgmtBean,gdnChildEntity);
        gdnChildEntity.setUpdateTime(new Date());
        gdnChildMgmtDao.save(gdnChildEntity);
        return CommonUtil.format(2000, "幼儿更新成功！");
    }

    @Override
    public String find(GdnChildMgmtBean gdnChildMgmtBean) {
        Optional<GdnChildEntity> optionGdnChildEntity= gdnChildMgmtDao.findById(gdnChildMgmtBean.getId());
        if (!optionGdnChildEntity.isPresent()) {
            return CommonUtil.format(4200, "幼儿不存在！");
        }
        GdnChildEntity gdnChildEntity = optionGdnChildEntity.get();
        return CommonUtil.format(2000, gdnChildEntity);
    }

    @Override
    public String finds(GdnChildMgmtBean gdnChildMgmtBean) {
        List<GdnChildEntity> GdnChildEntities = gdnChildMgmtDao.findby_name_district_Level(gdnChildMgmtBean);
        return CommonUtil.format(2000, GdnChildEntities);
    }
}
