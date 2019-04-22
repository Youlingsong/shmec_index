package com.datahome.task;

import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.entity.GdnMiddleDataRuleEntity;
import com.datahome.middledata.GdnMiddleDataModel;
import com.datahome.repository.GdnMiddleDataRepository;
import com.datahome.repository.GdnMiddleDataRuleRepository;
import com.datahome.service.GdnMiddleDataRuleService;
import com.datahome.util.CommonUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class MiddleDataCreateTask {
    @Resource
    private GdnMiddleDataRuleRepository gdnMiddleDataRuleDao;

    @Resource
    private GdnMiddleDataRepository gdnMiddledataDao;

    @Resource
    private GdnMiddleDataRuleService gdnMiddleDataRuleService;

    // 每隔10秒：0/10 * * * * ?
    // 每隔2分钟：0 0/2 * * * ?
    // 每天凌晨4点：0 0 4 * * ?
    @Scheduled(cron="0/2 * * * * ?")
    public void task() {
        List<GdnMiddleDataRuleEntity> allGdnMiddleDataRuleEntity = gdnMiddleDataRuleDao.findAll();
        if(CommonUtil.isEmptyList(allGdnMiddleDataRuleEntity)){
            for(GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity:allGdnMiddleDataRuleEntity){
                String sqlsentence = gdnMiddleDataRuleEntity.getSqlsentence();
                List<GdnMiddleDataModel> listGdnMiddleDataModel = gdnMiddleDataRuleDao.dealsql(sqlsentence);
                for(GdnMiddleDataModel gdnMiddleDataModel:listGdnMiddleDataModel){
                    List<GdnMiddleDataEntity> bykey_value_remake = gdnMiddledataDao.findBykey_value_remake(gdnMiddleDataModel);
                    if(!CommonUtil.isEmptyList(bykey_value_remake)){
                        GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
                        CommonUtil.exchangeObj(gdnMiddleDataModel,gdnMiddleDataEntity);
                        gdnMiddleDataEntity.setCreateTime(new Date());
                        gdnMiddleDataEntity.setUpdateTime(new Date());
                        gdnMiddleDataEntity.setGdnMiddleDataRuleEntity(gdnMiddleDataRuleEntity);
                        gdnMiddledataDao.save(gdnMiddleDataEntity);
                    }
                }
            }
        }else{
            System.out.println("未找到需要生成的中间数");
        }

    }
}
