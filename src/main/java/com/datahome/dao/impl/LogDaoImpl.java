package com.datahome.dao.impl;

import com.datahome.dao.LogDao;
import com.datahome.entity.LogEntity;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/2/1 15:05
 */

@Repository
public class LogDaoImpl implements LogDao {

    @Resource
    private EntityManager entityManager;

    @Override
    public void addLog(Integer userId, String userName, String requestPath, String ip, String resultCode, String message, String userType) {

        LogEntity logEntity = new LogEntity();
        logEntity.setMessage(message);
        logEntity.setRequestPath(requestPath);
        logEntity.setResultCode(resultCode);
        logEntity.setUserId(userId);
        logEntity.setUserType(userType);
        logEntity.setUserName(userName);
        logEntity.setSaveTime(Timestamp.valueOf(CommonUtil.timestampFormat.format(new Date())));

        entityManager.persist(logEntity);
    }
}
