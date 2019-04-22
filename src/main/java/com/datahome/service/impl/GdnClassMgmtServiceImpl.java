package com.datahome.service.impl;

import com.datahome.bean.GdnClassMgmtBean;
import com.datahome.entity.GdnClassEntity;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.repository.GdnClassMgmtRepository;
import com.datahome.repository.GdnGardenMgmtRepository;
import com.datahome.service.GdnClassMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnClassMgmtServiceImpl implements GdnClassMgmtService {
    @Resource
    private GdnClassMgmtRepository gdnClassMgmtDao;

    @Resource
    private GdnGardenMgmtRepository gdnGardenMgmtDao;
    @Override
    public String save(GdnClassMgmtBean gdnClassMgmtBean) {
        GdnClassEntity gdnClassEntity = new GdnClassEntity();
        //园所id
        String gdnGardenid =  gdnClassMgmtBean.getGdnGardenid();

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnGardenid);
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！,请确认园所后再保存");
        }
        CommonUtil.exchangeObj(gdnClassEntity, gdnClassMgmtBean);
        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();
        gdnClassEntity.setGdnGardenEntity(gdnGardenEntity);
        gdnClassEntity.setCreateTime(new Date());
        gdnClassEntity.setUpdateTime(new Date());
        gdnClassMgmtDao.save(gdnClassEntity);
        return CommonUtil.format(2000, "班级添加成功！");
    }

    @Override
    public String update(GdnClassMgmtBean gdnClassMgmtBean) {
        //园所id
        String gdnGardenid =  gdnClassMgmtBean.getGdnGardenid();

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnGardenid);
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！,请确认园所后再更新");
        }
        Optional<GdnClassEntity> optionGdnClassEntity = gdnClassMgmtDao.findById(gdnClassMgmtBean.getId());
        if (!optionGdnClassEntity.isPresent()) {
            return CommonUtil.format(4200, "班级不存在！,请确认班级后再更新");
        }
        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();

        GdnClassEntity gdnClassEntity = optionGdnClassEntity.get();
        CommonUtil.exchangeObj(gdnClassMgmtBean, gdnClassEntity);
        //更新园所
        gdnClassEntity.setGdnGardenEntity(gdnGardenEntity);
        gdnGardenEntity.setUpdateTime(new Date());
        gdnClassMgmtDao.save(gdnClassEntity);
        return CommonUtil.format(2000, "班级更新成功！");
    }

    @Override
    public String find(GdnClassMgmtBean gdnClassMgmtBean) {
        Optional<GdnClassEntity> optionGdnClassEntity = gdnClassMgmtDao.findById(gdnClassMgmtBean.getId());
        if (!optionGdnClassEntity.isPresent()) {
            return CommonUtil.format(4200, "班级不存在！,请确认班级后再更新");
        }
        GdnClassEntity gdnClassEntity = optionGdnClassEntity.get();
        return CommonUtil.format(2000, gdnClassEntity);
    }

    @Override
    public String finds(GdnClassMgmtBean gdnClassMgmtBean) {
        List<GdnClassEntity> GdnGardenEntitiees = gdnClassMgmtDao.findby_name_district_Level(gdnClassMgmtBean);
        return CommonUtil.format(2000, GdnGardenEntitiees);
    }
}
