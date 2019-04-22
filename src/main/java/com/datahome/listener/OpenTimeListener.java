package com.datahome.listener;

import com.datahome.entity.OpenTimeEntity;
import com.datahome.repository.OpenTimeMgmtRepository;
import com.datahome.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/16 15:25
 */

@Setter
@Getter
@Component
public class OpenTimeListener implements CommandLineRunner {

    @Resource
    private OpenTimeMgmtRepository openTimeMgmtDao;

    public static Map<String, Object> openTime = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        List<OpenTimeEntity> openTimeEntities = openTimeMgmtDao.findAll();
        if (openTimeEntities == null || openTimeEntities.size() != 1) {
            CommonUtil.LOGGER(OpenTimeListener.class).error(" 平台 OpenTime is error ");
        }
        OpenTimeEntity openTimeEntity = openTimeEntities.get(0);
        openTime.put("startTime",openTimeEntity.getStartTime());
        openTime.put("endTime",openTimeEntity.getEndTime());
        openTime.put("status",openTimeEntity.getStatus());
    }
}
