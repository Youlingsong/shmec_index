package com.datahome.controller;


import com.datahome.bean.GdnChildMgmtBean;
import com.datahome.bean.GdnClassMgmtBean;
import com.datahome.group.GdnClassMgmtGroup;
import com.datahome.service.GdnClassMgmtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

@RestController
@Api("班级管理")
@RequestMapping(value = "/mgmt/Class")
public class GdnClassMgmtController {
    @Resource
    private GdnClassMgmtService gdnClassMgmtService;
    /**
     * 新增班级
     *
     * @param gdnClassMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增班级")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnClassMgmtGroup.save.class}) GdnClassMgmtBean gdnClassMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnClassMgmtService.save(gdnClassMgmtBean);
    }

    @ApiOperation("更新班级")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnClassMgmtGroup.update.class}) GdnClassMgmtBean gdnClassMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnClassMgmtService.update(gdnClassMgmtBean);
    }

    @ApiOperation("根据id查询班级")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnClassMgmtGroup.find.class}) GdnClassMgmtBean gdnClassMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnClassMgmtService.find(gdnClassMgmtBean);
    }

    @ApiOperation("根据条件查询班级")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnClassMgmtGroup.finds.class}) GdnClassMgmtBean gdnClassMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnClassMgmtService.finds(gdnClassMgmtBean);
    }
}
