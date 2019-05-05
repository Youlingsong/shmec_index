package com.datahome.service.impl;

import com.datahome.bean.GdnAgencyMgmtBean;
import com.datahome.entity.GdnAgencyEntity;
import com.datahome.repository.GdnAgencyMgmtRepository;
import com.datahome.service.GdnAgencyMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnAgencyMgmtServiceImpl implements GdnAgencyMgmtService {

    @Resource
    private GdnAgencyMgmtRepository gdnAgencyMgmtDao;
    @Override
    public String save(GdnAgencyMgmtBean gdnAgencyMgmtBean) {
        GdnAgencyEntity gdnAgencyEntity = new GdnAgencyEntity();
        CommonUtil.exchangeObj(gdnAgencyMgmtBean,gdnAgencyEntity);
        gdnAgencyEntity.setCreateTime(new Date());
        gdnAgencyEntity.setUpdateTime(new Date());
        gdnAgencyMgmtDao.save(gdnAgencyEntity);
        return CommonUtil.format(2000, "机构添加成功！");
    }

    @Override
    public String update(GdnAgencyMgmtBean gdnAgencyMgmtBean) {
        Optional<GdnAgencyEntity> optionGdnAgencyEntity= gdnAgencyMgmtDao.findById(gdnAgencyMgmtBean.getId());
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！");
        }
        GdnAgencyEntity gdnAgencyEntity = optionGdnAgencyEntity.get();
        CommonUtil.exchangeObj(gdnAgencyMgmtBean, gdnAgencyEntity);
        gdnAgencyEntity.setUpdateTime(new Date());
        gdnAgencyMgmtDao.save(gdnAgencyEntity);
        return CommonUtil.format(2000, "机构更新成功！");
    }

    @Override
    public String find(GdnAgencyMgmtBean gdnAgencyMgmtBean) {
        Optional<GdnAgencyEntity> optionGdnAgencyEntity = gdnAgencyMgmtDao.findById(gdnAgencyMgmtBean.getId());
        if (!optionGdnAgencyEntity.isPresent()) {
            return CommonUtil.format(4200, "机构不存在！");
        }
        GdnAgencyEntity gdnAgencyEntity = optionGdnAgencyEntity.get();
        return CommonUtil.format(2000, gdnAgencyEntity);
    }

    @Override
    public String finds(GdnAgencyMgmtBean gdnAgencyMgmtBean) {
        List<GdnAgencyEntity> gdnAgencyEntities = gdnAgencyMgmtDao.findby_name_district_Level(gdnAgencyMgmtBean);
        return CommonUtil.format(2000, gdnAgencyEntities);
    }

    @Override
    public String importExcel(MultipartFile file, HttpServletResponse response) {

        return null;
    }
}
