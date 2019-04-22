package com.datahome.service.impl;

import com.datahome.bean.OpenTimeMgmtBean;
import com.datahome.entity.OpenTimeEntity;
import com.datahome.listener.OpenTimeListener;
import com.datahome.repository.OpenTimeMgmtRepository;
import com.datahome.service.OpenTimeMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/16 14:50
 */
@Service
public class OpenTimeMgmtServiceImpl implements OpenTimeMgmtService {

    @Resource
    private OpenTimeMgmtRepository openTimeMgmtDao;

    @Override
    public String find(OpenTimeMgmtBean openTimeMgmtBean) {
        List<OpenTimeEntity> openTimeEntities = openTimeMgmtDao.findAll();
        if (openTimeEntities == null || openTimeEntities.size() != 1) {
            return CommonUtil.format(4200, "记录不存在！");
        }
        return CommonUtil.format(2000, openTimeEntities.get(0));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String update(OpenTimeMgmtBean openTimeMgmtBean) {
        Optional<OpenTimeEntity> optionalOpenTimeEntity = openTimeMgmtDao.findById(openTimeMgmtBean.getId());
        if (!optionalOpenTimeEntity.isPresent()) {
            return CommonUtil.format(4200, "记录不存在！");
        }
        Date startTime = openTimeMgmtBean.getStartTime();
        Date endTime = openTimeMgmtBean.getEndTime();
        if (endTime.before(startTime)) {
            return CommonUtil.format(4200, "时间设置错误！");
        }

        OpenTimeEntity openTimeEntity = optionalOpenTimeEntity.get();
        openTimeEntity.setStartTime(openTimeMgmtBean.getStartTime());
        openTimeEntity.setEndTime(openTimeMgmtBean.getEndTime());
        openTimeEntity.setStatus(openTimeMgmtBean.getStatus());
        openTimeEntity.setUpdateTime(new Date());

        openTimeMgmtDao.save(openTimeEntity);

        Map<String, Object> openTime = OpenTimeListener.openTime;
        openTime.put("startTime",openTimeEntity.getStartTime());
        openTime.put("endTime",openTimeEntity.getEndTime());
        openTime.put("status",openTimeEntity.getStatus());

        return CommonUtil.format(2000, "修改成功！");
    }
}
