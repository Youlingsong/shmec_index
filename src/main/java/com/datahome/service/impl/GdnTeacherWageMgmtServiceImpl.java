package com.datahome.service.impl;

import com.datahome.bean.GdnTeacherWageMgmtBean;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.entity.GdnTeacherEntity;
import com.datahome.entity.GdnTeacherWageEntity;
import com.datahome.repository.GdnGardenMgmtRepository;
import com.datahome.repository.GdnTeacherMgmtRepository;
import com.datahome.repository.GdnTeacherWageMgmtRepository;
import com.datahome.service.GdnTeacherWageMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnTeacherWageMgmtServiceImpl implements GdnTeacherWageMgmtService {
    @Resource
    private GdnTeacherMgmtRepository gdnTeacherMgmtDao;

    @Resource
    private GdnGardenMgmtRepository gdnGardenMgmtDao;

    @Resource
    private GdnTeacherWageMgmtRepository gdnTeacherWageMgmtDao;


    @Override
    public String save(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnTeacherId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }
        GdnTeacherWageEntity gdnTeacherWageEntity = new GdnTeacherWageEntity();
        CommonUtil.exchangeObj(gdnTeacherWageMgmtBean,gdnTeacherWageEntity);
        gdnTeacherWageEntity.setCreateTime(new Date());
        gdnTeacherWageEntity.setUpdateTime(new Date());
        gdnTeacherWageMgmtDao.save(gdnTeacherWageEntity);
        return CommonUtil.format(2000, "教师工资保存成功");
    }

    @Override
    public String update(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        Optional<GdnTeacherWageEntity> optionGdnTeacherWage = gdnTeacherWageMgmtDao.findById(gdnTeacherWageMgmtBean.getId());
        if (!optionGdnTeacherWage.isPresent()) {
            return CommonUtil.format(4200, "教师工资记录不存在！");
        }
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnTeacherId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }

        GdnTeacherWageEntity gdnTeacherWageEntity = optionGdnTeacherWage.get();
        CommonUtil.exchangeObj(gdnTeacherWageMgmtBean,gdnTeacherWageEntity);
        gdnTeacherWageEntity.setUpdateTime(new Date());
        gdnTeacherWageMgmtDao.save(gdnTeacherWageEntity);
        return CommonUtil.format(2000, "教师工资更新成功");
    }

    @Override
    public String find(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        Optional<GdnTeacherWageEntity> optionGdnTeacherWage = gdnTeacherWageMgmtDao.findById(gdnTeacherWageMgmtBean.getId());
        if (!optionGdnTeacherWage.isPresent()) {
            return CommonUtil.format(4200, "教师工资不存在！");
        }
        GdnTeacherWageEntity gdnTeacherWageEntity = optionGdnTeacherWage.get();
        return CommonUtil.format(2000, gdnTeacherWageEntity);
    }

    @Override
    public String finds(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        List<GdnTeacherWageEntity> gdnTeacherWageEntities = gdnTeacherWageMgmtDao.findby_name_district_Level(gdnTeacherWageMgmtBean);
        return CommonUtil.format(2000, gdnTeacherWageEntities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        Optional<GdnTeacherWageEntity> optionGdnTeacherWage = gdnTeacherWageMgmtDao.findById(gdnTeacherWageMgmtBean.getId());
        if (!optionGdnTeacherWage.isPresent()) {
            return CommonUtil.format(4200, "教师工资记录不存在！");
        }
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnTeacherId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnTeacherWageMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }

        GdnTeacherWageEntity gdnTeacherWageEntity = optionGdnTeacherWage.get();
        gdnTeacherWageMgmtDao.delete(gdnTeacherWageEntity);
        return CommonUtil.format(2000, "教师工资删除成功");
    }

    @Override
    public String deletes(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        List<GdnTeacherWageEntity> gdnTeacherWageEntities = gdnTeacherWageMgmtDao.findby_name_district_Level(gdnTeacherWageMgmtBean);
          if(CommonUtil.isEmptyList(gdnTeacherWageEntities)){
              for(GdnTeacherWageEntity gdnTeacherWageEntity:gdnTeacherWageEntities){
                  gdnTeacherWageMgmtDao.delete(gdnTeacherWageEntity);
              }
          }else{
              return CommonUtil.format(2000, "未根据条件找到教师工资");
          }
        return CommonUtil.format(2000, "教师工资批量删除成功");
    }
}
