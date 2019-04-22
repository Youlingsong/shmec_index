package com.datahome.service;

/**
 * @Author xl
 * @Description: 日志记录
 * @Date: Create in 2018/2/1 14:22
 */
public interface LogService {

    void addLog(Integer userId, String userName, String requestPath, String ip, String resultCode, String resultMessage, String userType);

}
