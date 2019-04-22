package com.datahome.dao;

import com.datahome.entity.StaffEntity;
import com.datahome.entity.StaffRoleEntity;

import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/8 17:51
 */
public interface StaffDao {

    List<StaffRoleEntity> findStaffRoleBy_Id(Integer roleId);

    List<StaffRoleEntity> findStaffRoles();

    List<StaffEntity> findsBy_cityIds_status(List<Integer> cityIds, String status);

}
