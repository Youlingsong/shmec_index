package com.datahome.controller;

import com.datahome.bean.GdnChildParentMgmtBean;
import com.datahome.bean.GdnParentMgmtBean;
import com.datahome.group.GdnChildParentMgmtGroup;
import com.datahome.group.GdnParentMgmtGroup;
import com.datahome.service.GdnParentMgmtService;
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
@Api("家长管理")
@RequestMapping(value = "/mgmt/GdnParent")
public class GdnParentMgmtController {
    @Resource
    private GdnParentMgmtService gdnParentMgmtService;


    /**
     * 新增家长
     *
     * @param gdnParentMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增家长")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnParentMgmtGroup.save.class}) GdnParentMgmtBean gdnParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.save(gdnParentMgmtBean);
    }

    @ApiOperation("更新家长")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnParentMgmtGroup.update.class}) GdnParentMgmtBean gdnParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.update(gdnParentMgmtBean);
    }

    @ApiOperation("根据id查询家长")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnParentMgmtGroup.find.class}) GdnParentMgmtBean gdnParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.find(gdnParentMgmtBean);
    }

    @ApiOperation("根据条件查询家长")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnParentMgmtGroup.finds.class}) GdnParentMgmtBean gdnParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.finds(gdnParentMgmtBean);
    }

    @ApiOperation("添加幼儿家长中间表")
    @PostMapping("/add.do")
    public String add(@Validated({Default.class, GdnChildParentMgmtGroup.save.class}) GdnChildParentMgmtBean gdnChildParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.add(gdnChildParentMgmtBean);
    }

    @ApiOperation("查询幼儿家长中间表")
    @PostMapping("/serach.do")
    public String serach(@Validated({Default.class, GdnChildParentMgmtGroup.finds.class}) GdnChildParentMgmtBean gdnChildParentMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnParentMgmtService.serach(gdnChildParentMgmtBean);
    }
}
