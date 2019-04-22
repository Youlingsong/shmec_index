package com.datahome.service;

import com.datahome.bean.AccountBean;

/**
 * @Author xl
 * @Date: Create in 2018/5/8 17:49
 */
public interface AccountService {

    String login(AccountBean accseeBean);

    String logout(AccountBean accseeBean);

    String find(AccountBean accseeBean);

    String updatePassword(AccountBean accseeBean);

    String updateInfo(AccountBean accseeBean);
}
