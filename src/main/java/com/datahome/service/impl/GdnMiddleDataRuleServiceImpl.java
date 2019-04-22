package com.datahome.service.impl;

import com.datahome.bean.GdnMiddleDataRuleBean;
import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.entity.GdnMiddleDataRuleEntity;
import com.datahome.middledata.GdnMiddleDataModel;
import com.datahome.repository.GdnMiddleDataRepository;
import com.datahome.repository.GdnMiddleDataRuleRepository;
import com.datahome.service.GdnMiddleDataRuleService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnMiddleDataRuleServiceImpl implements GdnMiddleDataRuleService {
    @Resource
    private GdnMiddleDataRuleRepository gdnMiddleDataRuleDao;

    @Resource
   private GdnMiddleDataRepository gdnMiddledataDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) {
        Integer saveOrlook = gdnMiddleDataRuleBean.getSaveOrlook();
        List<GdnMiddleDataModel> listGdnMiddleDataModel = gdnMiddleDataRuleDao.dealsql(gdnMiddleDataRuleBean.getSqlsentence());
        if(saveOrlook==1){
          //查看结果
            return CommonUtil.format(2000, listGdnMiddleDataModel);
        }else{
          //保存计算规则
            GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity = new GdnMiddleDataRuleEntity();
            CommonUtil.exchangeObj(gdnMiddleDataRuleBean,gdnMiddleDataRuleEntity);
            gdnMiddleDataRuleEntity.setCreateTime(new Date());
            gdnMiddleDataRuleEntity.setUpdateTime(new Date());
            gdnMiddleDataRuleDao.save(gdnMiddleDataRuleEntity);
            //保存生成的中间数
            for(GdnMiddleDataModel gdnMiddleDataModel:listGdnMiddleDataModel){
                GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
                CommonUtil.exchangeObj(gdnMiddleDataModel,gdnMiddleDataEntity);
                gdnMiddleDataEntity.setCreateTime(new Date());
                gdnMiddleDataEntity.setUpdateTime(new Date());
                gdnMiddledataDao.save(gdnMiddleDataEntity);
            }
        }
        return CommonUtil.format(2000, "中间数计算规则添加成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) {
        Optional<GdnMiddleDataRuleEntity> optionGdnMiddleDataRuleEntity = gdnMiddleDataRuleDao.findById(gdnMiddleDataRuleBean.getId());
        if (!optionGdnMiddleDataRuleEntity.isPresent()) {
            return CommonUtil.format(4200, "中间数计算规则不存在");
        }
        GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity = optionGdnMiddleDataRuleEntity.get();
        //修改的是sql
        if(!gdnMiddleDataRuleBean.getSqlsentence().equals(gdnMiddleDataRuleEntity.getSqlsentence())){
            //删除原先计算规则下产生的中间数据
            String ruleid = gdnMiddleDataRuleEntity.getId();
            gdnMiddledataDao.deleteby_ruleid(ruleid);
            //生成新的中间数
            List<GdnMiddleDataModel> listGdnMiddleDataModel = gdnMiddleDataRuleDao.dealsql(gdnMiddleDataRuleBean.getSqlsentence());
            //保存生成的中间数
            for(GdnMiddleDataModel gdnMiddleDataModel:listGdnMiddleDataModel){
                GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
                CommonUtil.exchangeObj(gdnMiddleDataModel,gdnMiddleDataEntity);
                gdnMiddleDataEntity.setCreateTime(new Date());
                gdnMiddleDataEntity.setUpdateTime(new Date());
                gdnMiddleDataEntity.setGdnMiddleDataRuleEntity(gdnMiddleDataRuleEntity);
                gdnMiddledataDao.save(gdnMiddleDataEntity);
            }
        }
        CommonUtil.exchangeObj(gdnMiddleDataRuleBean,gdnMiddleDataRuleEntity);
        gdnMiddleDataRuleEntity.setUpdateTime(new Date());
        gdnMiddleDataRuleDao.save(gdnMiddleDataRuleEntity);
        return CommonUtil.format(2000, "中间数计算规则更新成功！");
    }

    @Override
    public String find(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) {
        Optional<GdnMiddleDataRuleEntity> optionGdnMiddleDataRuleEntity = gdnMiddleDataRuleDao.findById(gdnMiddleDataRuleBean.getId());
        if (!optionGdnMiddleDataRuleEntity.isPresent()) {
            return CommonUtil.format(4200, "中间数计算规则不存在");
        }
        GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity = optionGdnMiddleDataRuleEntity.get();
        return CommonUtil.format(2000, gdnMiddleDataRuleEntity);
    }

    @Override
    public String finds(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) {
        //jpa模糊查询
        List<GdnMiddleDataRuleEntity> gdnParentEntities = gdnMiddleDataRuleDao.findByNameLike("%"+gdnMiddleDataRuleBean.getName()+"%");
        return CommonUtil.format(2000, gdnParentEntities);
    }

    @Transactional
    @Override
    public String delete(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) {
        Optional<GdnMiddleDataRuleEntity> optionGdnMiddleDataRuleEntity = gdnMiddleDataRuleDao.findById(gdnMiddleDataRuleBean.getId());
        if (!optionGdnMiddleDataRuleEntity.isPresent()) {
            return CommonUtil.format(4200, "中间数计算规则不存在");
        }
        GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity = optionGdnMiddleDataRuleEntity.get();
        //删除该计算规则下产生的中间数据
        String ruleid = gdnMiddleDataRuleEntity.getId();
        gdnMiddledataDao.deleteby_ruleid(ruleid);
        //删除该规则
        gdnMiddleDataRuleDao.delete(gdnMiddleDataRuleEntity);
        return CommonUtil.format(4200, "中间数计算规则删除成功");
    }
}
