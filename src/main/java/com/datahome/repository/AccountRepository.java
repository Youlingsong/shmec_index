package com.datahome.repository;

import com.datahome.dao.AccountDao;
import com.datahome.dao.BaseDao;
import com.datahome.entity.AccountEntity;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/8 18:52
 */


public interface AccountRepository extends BaseDao<AccountEntity, Integer>, AccountDao {

    AccountEntity findByName(String name);

    AccountEntity findByAccount(String account);

    AccountEntity  findByNameAndPassword(String name, String password);

}
