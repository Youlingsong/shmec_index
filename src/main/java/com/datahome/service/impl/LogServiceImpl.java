package com.datahome.service.impl;

import com.datahome.dao.LogDao;
import com.datahome.service.LogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/2/1 14:40
 */

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    @Async
    @Transactional
    public void addLog(Integer userId, String userName,String requestPath, String ip, String resultCode,String resultMessage,String userType) {
        logDao.addLog(userId,userName,requestPath,ip, resultCode,resultMessage,userType);
    }

}
