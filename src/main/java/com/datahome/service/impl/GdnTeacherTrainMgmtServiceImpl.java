package com.datahome.service.impl;

import com.datahome.bean.GdnTeacherTrainMgmtBean;
import com.datahome.entity.GdnAgencyEntity;
import com.datahome.entity.GdnTeacherEntity;
import com.datahome.entity.GdnTeacherTrainEntity;
import com.datahome.repository.GdnAgencyMgmtRepository;
import com.datahome.repository.GdnTeacherMgmtRepository;
import com.datahome.repository.GdnTeacherTrainMgmtRepository;
import com.datahome.service.GdnTeacherTrainMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnTeacherTrainMgmtServiceImpl implements GdnTeacherTrainMgmtService {
    @Resource
    private GdnTeacherMgmtRepository gdnTeacherMgmtDao;

    @Resource
    private GdnAgencyMgmtRepository gdnAgencyMgmtDao;

    @Resource
    private GdnTeacherTrainMgmtRepository gdnTeacherTrainMgmtDao;
    @Override
    public String save(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean) {
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherTrainMgmtBean.getGdnTeacherId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnAgencyEntity> optionGdnAgencyEntity = gdnAgencyMgmtDao.findById(gdnTeacherTrainMgmtBean.getGdnAgencyId());
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！");
        }
        GdnTeacherTrainEntity gdnTeacherTrainEntity = new GdnTeacherTrainEntity();
        CommonUtil.exchangeObj(gdnTeacherTrainMgmtBean,gdnTeacherTrainEntity);
        gdnTeacherTrainEntity.setCreateTime(new Date());
        gdnTeacherTrainEntity.setUpdateTime(new Date());
        gdnTeacherTrainMgmtDao.save(gdnTeacherTrainEntity);
        return CommonUtil.format(2000, "教师培训保存成功");
    }

    @Override
    public String update(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean) {
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherTrainMgmtBean.getGdnTeacherId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnAgencyEntity> optionGdnAgencyEntity = gdnAgencyMgmtDao.findById(gdnTeacherTrainMgmtBean.getGdnAgencyId());
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！");
        }
        Optional<GdnTeacherTrainEntity> optionGdnTeacherTrainEntity = gdnTeacherTrainMgmtDao.findById(gdnTeacherTrainMgmtBean.getId());
        if (!optionGdnTeacherTrainEntity.isPresent()) {
            return CommonUtil.format(4200, "教师培训信息不存在！");
        }
        GdnTeacherTrainEntity gdnTeacherTrainEntity = optionGdnTeacherTrainEntity.get();
        CommonUtil.exchangeObj(gdnTeacherTrainMgmtBean,gdnTeacherTrainEntity);
        gdnTeacherTrainEntity.setUpdateTime(new Date());
        gdnTeacherTrainMgmtDao.save(gdnTeacherTrainEntity);
        return CommonUtil.format(2000, "教师培训更新成功");
    }

    @Override
    public String find(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean) {
        Optional<GdnTeacherTrainEntity> optionGdnTeacherTrainEntity = gdnTeacherTrainMgmtDao.findById(gdnTeacherTrainMgmtBean.getId());
        if (!optionGdnTeacherTrainEntity.isPresent()) {
            return CommonUtil.format(4200, "教师培训信息不存在！");
        }
        GdnTeacherTrainEntity gdnTeacherTrainEntity = optionGdnTeacherTrainEntity.get();
        return CommonUtil.format(2000, gdnTeacherTrainEntity);
    }

    @Override
    public String finds(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean) {
        List<GdnTeacherTrainEntity> GdnTeacherTrainEntities = gdnTeacherTrainMgmtDao.findby_name_district_Level(gdnTeacherTrainMgmtBean);
        return CommonUtil.format(2000, GdnTeacherTrainEntities);
    }
}
