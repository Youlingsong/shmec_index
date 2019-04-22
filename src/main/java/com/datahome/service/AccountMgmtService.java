package com.datahome.service;

import com.datahome.bean.AccountMgmtBean;

/**
 * @Author xl
 * @Date: Create in 2018/5/8 17:49
 */
public interface AccountMgmtService {

    String save(AccountMgmtBean accountBean);

    String find(AccountMgmtBean accountBean);

    String finds(AccountMgmtBean accountBean);

    String initPassword(AccountMgmtBean accountBean);

    String updateInfo(AccountMgmtBean accountBean);

    String delete(AccountMgmtBean accountBean);
}
