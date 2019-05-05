package com.datahome.controller;

import com.datahome.bean.AccountBean;
import com.datahome.group.AccountGroup;
import com.datahome.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author mackson
 * @Description: 普通用戶的前台
 * @Date: Create in 2019/4/29 9:18
 */

@RestController
@RequestMapping(value = "/account")
@Api("普通用戶的前台")
public class AccountController {


    @Resource
    private AccountService accountService;

    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("/login.do")
    public String login(@Validated({Default.class, AccountGroup.login.class}) AccountBean accountBean, BindingResult result) {
        return accountService.login(accountBean);
    }


    /**
     * 登出
     */
    @ApiOperation("登出")
    @PostMapping("/logout.do")
    public String logout(@Validated({Default.class, AccountGroup.logout.class}) AccountBean accountBean, BindingResult result) {
        return accountService.logout(accountBean);
    }

    /**
     * 查找用户
     */
    @PostMapping("/find.do")
    @ApiOperation("查找用户")
    public String find(@Validated({Default.class, AccountGroup.find.class}) AccountBean accountBean, BindingResult result) {
        return accountService.find(accountBean);
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword.do")
    @ApiOperation("修改密码")
    public String updatePassword(@Validated({Default.class, AccountGroup.updatePassword.class}) AccountBean accountBean, BindingResult result) {
        return accountService.updatePassword(accountBean);
    }

    /**
     * 修改基本信息
     */
    @PostMapping("/updateInfo.do")
    @ApiOperation("修改基本信息")
    public String updateInfo(@Validated({Default.class, AccountGroup.updateInfo.class}) AccountBean accountBean, BindingResult result) {
        return accountService.updateInfo(accountBean);
    }
}
