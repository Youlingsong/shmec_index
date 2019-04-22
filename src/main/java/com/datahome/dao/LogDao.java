package com.datahome.dao;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/2/1 15:01
 */


public interface LogDao {

    void addLog(Integer userId, String userName, String requestPath, String ip, String resultCode, String message, String userType);
}
