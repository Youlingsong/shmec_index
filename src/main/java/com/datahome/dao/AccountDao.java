package com.datahome.dao;

import com.datahome.entity.AccountEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/8 17:51
 */
public interface AccountDao {


    List<AccountEntity> find_by_accountStatus_city_name(String name, String accountStatus, Integer cityId, String order, String sort, Integer pageSize, Integer pageNumber);

    List<AccountEntity> find_by_cityIds(List<Integer> cityIds);

    Integer findTotal_by_accountStatus_city_name(String name, String accountStatus, Integer cityId);
}
