package com.datahome.controller;

import com.datahome.bean.StaffBean;
import com.datahome.group.StaffGroup;
import com.datahome.service.StaffService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 普通用户的后台管理
 * @Date: Create in 2018/5/10 10:02
 */

@RestController
@RequestMapping(value = "/staff")
public class StaffController {

    @Resource
    private StaffService staffService;

    /**
     * 登录
     */
    @RequestMapping("/login.do")
    public String login(@Validated({Default.class, StaffGroup.login.class}) StaffBean staffBean, BindingResult result) {
        return staffService.login(staffBean);
    }

    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public String logout(@Validated({Default.class, StaffGroup.logout.class}) StaffBean staffBean, BindingResult result) {
        return staffService.logout(staffBean);
    }

    /**
     * 新增账号
     */
    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, StaffGroup.save.class}) StaffBean staffBean, BindingResult result) {
        return staffService.save(staffBean);
    }

    /**
     * 查询用户列表
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, StaffGroup.finds.class}) StaffBean staffBean, BindingResult result) {
        return staffService.finds(staffBean);
    }

    /**
     * 查询用户详情
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, StaffGroup.find.class}) StaffBean staffBean, BindingResult result) {
        return staffService.find(staffBean);
    }


    /**
     * 超级管理员修改基本信息
     */
    @RequestMapping("/updateInfo.do")
    public String updateInfo(@Validated({Default.class, StaffGroup.updateInfo.class}) StaffBean staffBean, BindingResult result) {
        return staffService.updateInfo(staffBean);
    }

    /**
     * 超级管理员初始化用户密码
     */
    @RequestMapping("/initPassword.do")
    public String initPassword(@Validated({Default.class, StaffGroup.initPassword.class}) StaffBean staffBean, BindingResult result) {
        return staffService.initPassword(staffBean);
    }


    /**
     * 查询所有的管理员角色
     */
    @RequestMapping("/findsStaffRole.do")
    public String findsStaffRole() {
        return staffService.findAllStaffRole();
    }

    /**
     * 个人中心修改密码
     */
    @RequestMapping("/updatePassword.do")
    public String updatePassword(@Validated({Default.class, StaffGroup.updatePassword.class}) StaffBean staffBean, BindingResult result) {
        return staffService.updateMyselfPassword(staffBean);
    }

    /**
     * 个人中心修改基本信息
     */
    @RequestMapping("/updateMyselfInfo.do")
    public String updateMyselfInfo(@Validated({Default.class, StaffGroup.updateMyselfInfo.class}) StaffBean staffBean, BindingResult result) {
        return staffService.updateMyselfInfo(staffBean);
    }


    /**
     * 删除用户
     */
    @RequestMapping("/delete.do")
    public String delete(@Validated({Default.class, StaffGroup.delete.class}) StaffBean staffBean, BindingResult result) {
        return staffService.delete(staffBean);
    }
}
