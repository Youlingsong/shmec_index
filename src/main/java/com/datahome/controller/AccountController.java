package com.datahome.controller;

import com.datahome.bean.AccountBean;
import com.datahome.group.AccountGroup;
import com.datahome.service.AccountService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 普通用戶的前台
 * @Date: Create in 2018/10/9 9:18
 */

@RestController
@RequestMapping(value = "/account")
public class AccountController {


    @Resource
    private AccountService accountService;

    /**
     * 登录
     */
    @RequestMapping("/login.do")
    public String login(@Validated({Default.class, AccountGroup.login.class}) AccountBean accountBean, BindingResult result) {
        return accountService.login(accountBean);
    }


    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public String logout(@Validated({Default.class, AccountGroup.logout.class}) AccountBean accountBean, BindingResult result) {
        return accountService.logout(accountBean);
    }

    /**
     * 登录
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, AccountGroup.find.class}) AccountBean accountBean, BindingResult result) {
        return accountService.find(accountBean);
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePassword.do")
    public String updatePassword(@Validated({Default.class, AccountGroup.updatePassword.class}) AccountBean accountBean, BindingResult result) {
        return accountService.updatePassword(accountBean);
    }

    /**
     * 修改基本信息
     */
    @RequestMapping("/updateInfo.do")
    public String updateInfo(@Validated({Default.class, AccountGroup.updateInfo.class}) AccountBean accountBean, BindingResult result) {
        return accountService.updateInfo(accountBean);
    }
}
