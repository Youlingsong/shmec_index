package com.datahome.controller;

import com.datahome.bean.OpenTimeMgmtBean;
import com.datahome.group.OpenTimeMgmtGroup;
import com.datahome.service.OpenTimeMgmtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 平台开发时间
 * @Date: Create in 2018/11/21 14:46
 */


@RestController
@RequestMapping("/mgmt/openTime")
public class OpenTimeMgmtController {

    @Resource
    private OpenTimeMgmtService openTimeMgmtService;


    /**
     * 查询单条记录
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, OpenTimeMgmtGroup.find.class}) OpenTimeMgmtBean otmb, BindingResult result) {
        return openTimeMgmtService.find(otmb);
    }

    /**
     * 修改记录
     */
    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, OpenTimeMgmtGroup.update.class}) OpenTimeMgmtBean otmb, BindingResult result) {
        return openTimeMgmtService.update(otmb);
    }
}
