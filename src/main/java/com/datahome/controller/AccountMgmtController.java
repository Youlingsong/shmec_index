package com.datahome.controller;

import com.datahome.bean.AccountMgmtBean;
import com.datahome.group.AccountMgmtGroup;
import com.datahome.service.AccountMgmtService;
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
 * @Author xl
 * @Description: 普通用户的后台管理
 * @Date: Create in 2018/5/10 10:02
 */

@RestController
@RequestMapping(value = "/mgmt/account")
@Api("普通用户的后台管理")
public class AccountMgmtController {

    @Resource
    private AccountMgmtService accountMgmtService;

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, AccountMgmtGroup.save.class}) AccountMgmtBean accountMgmtBean, BindingResult result) {
        return accountMgmtService.save(accountMgmtBean);
    }


    /**
     * 查询用户详情
     */
    @PostMapping("/find.do")
    @ApiOperation("查询用户详情")
    public String find(@Validated({Default.class, AccountMgmtGroup.find.class}) AccountMgmtBean accountMgmtBean, BindingResult result) {
        return accountMgmtService.find(accountMgmtBean);
    }

    /**
     * 查询用户列表
     */
    @ApiOperation("查询用户列表")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, AccountMgmtGroup.finds.class}) AccountMgmtBean accountMgmtBean, BindingResult result) {
        return accountMgmtService.finds(accountMgmtBean);
    }

    /**
     * 管理员  修改用户基本信息
     */
    @PostMapping("/updateInfo.do")
    @ApiOperation("管理员  修改用户基本信息")
    public String updateInfo(@Validated({Default.class, AccountMgmtGroup.updateInfo.class}) AccountMgmtBean accountMgmtBean, BindingResult result) {
        return accountMgmtService.updateInfo(accountMgmtBean);
    }

    /**
     * 管理员 初始化用户密码
     */
    @ApiOperation("管理员 初始化用户密码")
    @PostMapping("/initPassword.do")
    public String initPassword(@Validated({Default.class, AccountMgmtGroup.initPassword.class}) AccountMgmtBean accountMgmtBean, BindingResult result) {
        return accountMgmtService.initPassword(accountMgmtBean);
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete.do")
    public String delete(@Validated({Default.class, AccountMgmtGroup.delete.class}) AccountMgmtBean accountBean, BindingResult result) {
        return accountMgmtService.delete(accountBean);
    }
}
