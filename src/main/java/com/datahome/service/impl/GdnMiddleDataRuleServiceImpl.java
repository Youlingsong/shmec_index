package com.datahome.service.impl;

import com.datahome.bean.GdnMiddleDataRuleBean;
import com.datahome.entity.GdnCityEntity;
import com.datahome.entity.GdnDataBatchEntity;
import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.entity.GdnMiddleDataRuleEntity;
import com.datahome.repository.GdnCityRepository;
import com.datahome.repository.GdnDataBatchRepository;
import com.datahome.repository.GdnMiddleDataRepository;
import com.datahome.repository.GdnMiddleDataRuleRepository;
import com.datahome.service.GdnMiddleDataRuleService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GdnMiddleDataRuleServiceImpl implements GdnMiddleDataRuleService {
    @Resource
    private GdnMiddleDataRuleRepository gdnMiddleDataRuleDao;

    @Resource
    private GdnMiddleDataRepository gdnMiddledataDao;

    @Resource
    private GdnCityRepository gdnCityDao;

    @Resource
    private GdnDataBatchRepository gdnDataBatchDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) throws Exception {
        Integer saveOrlook = gdnMiddleDataRuleBean.getSaveOrlook();

        String sqlsentence = gdnMiddleDataRuleBean.getSqlsentence();
        //前端传过来的citycode
        List<String> cityCodes = gdnMiddleDataRuleBean.getCityCodes();
        GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity = null;
        HashMap<String, Integer> resultHashMap = new HashMap<>();
        if (saveOrlook == 1) {
            //只查看结果
            for (String cityCode : cityCodes) {
                GdnCityEntity gdnCityEntity = gdnCityDao.findBycityCode(cityCode);
                if(gdnCityEntity==null){
                 return CommonUtil.format(2000, "所选城市不存在");
                }
                String areasqlRule = gdnCityEntity.getAreasqlRule();
                //最终拼接的sql
                if (sqlsentence.contains("where")) {
                    areasqlRule = " and " + areasqlRule;
                } else {
                    areasqlRule = " where " + areasqlRule;
                }
                String resultsqlRule = sqlsentence + areasqlRule;
                Integer resultValue = CommonUtil.dealsql(resultsqlRule);
                resultHashMap.put(gdnCityEntity.getCityName(), resultValue);
            }
            return CommonUtil.format(2000, resultHashMap);
        }

        //保存计算规则
        gdnMiddleDataRuleEntity = new GdnMiddleDataRuleEntity();
        CommonUtil.exchangeObj(gdnMiddleDataRuleBean, gdnMiddleDataRuleEntity);
        gdnMiddleDataRuleEntity.setCreateTime(new Date());
        gdnMiddleDataRuleEntity.setUpdateTime(new Date());
        gdnMiddleDataRuleDao.save(gdnMiddleDataRuleEntity);
        for (String cityCode : cityCodes) {
            GdnCityEntity gdnCityEntity = gdnCityDao.findBycityCode(cityCode);
            String areasqlRule = gdnCityEntity.getAreasqlRule();
            //最终拼接的sql
            if (sqlsentence.contains("where")) {
                areasqlRule = " and " + areasqlRule;
            } else {
                areasqlRule = " where " + areasqlRule;
            }
            String resultsqlRule = sqlsentence + areasqlRule;
            Integer resultValue = null;
            resultValue = CommonUtil.dealsql(resultsqlRule);

            //保存生成的中间数
            GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
            gdnMiddleDataEntity.setGdnCityEntity(gdnCityEntity);
            gdnMiddleDataEntity.setGdnMiddleDataRuleEntity(gdnMiddleDataRuleEntity);
            //查找最新的批次数据日期
            GdnDataBatchEntity gdnDataBatchEntity = gdnDataBatchDao.findLastest();
            gdnMiddleDataEntity.setGdnDataBatchEntity(gdnDataBatchEntity);
            gdnMiddleDataEntity.setValue(resultValue);
            gdnMiddleDataEntity.setRemake("备注:这是测试数剧");
            gdnMiddleDataEntity.setCreateTime(new Date());
            gdnMiddleDataEntity.setUpdateTime(new Date());
            gdnMiddledataDao.save(gdnMiddleDataEntity);
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
        if (!gdnMiddleDataRuleBean.getSqlsentence().equals(gdnMiddleDataRuleEntity.getSqlsentence())) {
            //删除原先计算规则下产生的中间数据
            String ruleid = gdnMiddleDataRuleEntity.getId();
            gdnMiddledataDao.deleteby_ruleid(ruleid);
            //生成新的中间数
            //   List<GdnMiddleDataModel> listGdnMiddleDataModel = gdnMiddleDataRuleDao.dealsql(gdnMiddleDataRuleBean.getSqlsentence());
            //保存生成的中间数
//            for(GdnMiddleDataModel gdnMiddleDataModel:listGdnMiddleDataModel){
//                GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
//                CommonUtil.exchangeObj(gdnMiddleDataModel,gdnMiddleDataEntity);
//                gdnMiddleDataEntity.setCreateTime(new Date());
//                gdnMiddleDataEntity.setUpdateTime(new Date());
//                gdnMiddleDataEntity.setGdnMiddleDataRuleEntity(gdnMiddleDataRuleEntity);
//                gdnMiddledataDao.save(gdnMiddleDataEntity);
//            }
        }
        CommonUtil.exchangeObj(gdnMiddleDataRuleBean, gdnMiddleDataRuleEntity);
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
        List<GdnMiddleDataRuleEntity> gdnParentEntities = gdnMiddleDataRuleDao.findByNameLike("%" + gdnMiddleDataRuleBean.getName() + "%");
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
