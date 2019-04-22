package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.StaffDao;
import com.datahome.entity.StaffEntity;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/11 17:59
 */
public interface StaffRepository extends BaseDao<StaffEntity, Integer>, StaffDao {

    StaffEntity findByName(String name);

    StaffEntity findByNameAndPassword(String name, String password);

}
