package com.datahome.service.impl;

import com.datahome.bean.GdnGardenMgmtBean;
import com.datahome.entity.GdnAgencyEntity;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.repository.GdnAgencyMgmtRepository;
import com.datahome.repository.GdnGardenMgmtRepository;
import com.datahome.service.GdnGardenMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnGardenMgmtServiceImpl implements GdnGardenMgmtService {
    @Resource
    private GdnAgencyMgmtRepository gdnAgencyMgmtDao;
    @Resource
    private GdnGardenMgmtRepository gdnGardenMgmtDao;

    @Override
    public String save(GdnGardenMgmtBean gdnGardenMgmtBean) {
        GdnGardenEntity gdnGardenEntity = new GdnGardenEntity();
        //机构id
        String gdnAgencyid = gdnGardenMgmtBean.getGdnAgencyid();
        Optional<GdnAgencyEntity> optionGdnAgencyEntity = gdnAgencyMgmtDao.findById(gdnAgencyid);
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！,请确认机构编码后再保存园所");
        }

        CommonUtil.exchangeObj(gdnGardenMgmtBean, gdnGardenEntity);
        gdnGardenEntity.setCreateTime(new Date());
        gdnGardenEntity.setUpdateTime(new Date());
        gdnGardenMgmtDao.save(gdnGardenEntity);
        return CommonUtil.format(2000, "园所添加成功！");
    }

    @Override
    public String update(GdnGardenMgmtBean gdnGardenMgmtBean) {

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnGardenMgmtBean.getId());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }

        String gdnAgencyid = gdnGardenMgmtBean.getGdnAgencyid();
        Optional<GdnAgencyEntity> optionGdnAgencyEntity = gdnAgencyMgmtDao.findById(gdnAgencyid);
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！,请确认机构编码后再更新园所");
        }

        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();
        CommonUtil.exchangeObj(gdnGardenMgmtBean, gdnGardenEntity);
        gdnGardenEntity.setUpdateTime(new Date());
        gdnGardenMgmtDao.save(gdnGardenEntity);
        return CommonUtil.format(2000, "园所更新成功！");
    }

    @Override
    public String find(GdnGardenMgmtBean gdnGardenMgmtBean) {
        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnGardenMgmtBean.getId());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }
        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();
        return CommonUtil.format(2000, gdnGardenEntity);
    }

    @Override
    public String finds(GdnGardenMgmtBean gdnGardenMgmtBean) {
        List<GdnGardenEntity> GdnGardenEntitiees = gdnGardenMgmtDao.findby_name_district_Level(gdnGardenMgmtBean);
        return CommonUtil.format(2000, GdnGardenEntitiees);
    }
}
