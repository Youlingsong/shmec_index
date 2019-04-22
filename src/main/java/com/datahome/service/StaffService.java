package com.datahome.service;

import com.datahome.bean.StaffBean;

/**
 * @Author xl
 * @Date: Create in 2018/5/8 17:49
 */
public interface StaffService {


    String login(StaffBean staffBean);

    String logout(StaffBean staffBean);

    String save(StaffBean staffBean);

    String find(StaffBean staffBean);

    String finds(StaffBean staffBean);

    String delete(StaffBean staffBean);

    String updateInfo(StaffBean staffBean);

    String initPassword(StaffBean staffBean);

    String updateMyselfPassword(StaffBean staffBean);

    String updateMyselfInfo(StaffBean staffBean);

    String findAllStaffRole();
}
