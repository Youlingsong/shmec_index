package com.datahome.controller;


import com.datahome.bean.GdnGardenMgmtBean;
import com.datahome.group.GdnGardenMgmtGroup;
import com.datahome.service.GdnGardenMgmtService;
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
@Api("园所管理")
@RequestMapping(value = "/mgmt/Garden")
public class GdnGardenMgmtController {
    @Resource
    private GdnGardenMgmtService gdnGardenMgmtService;


    /**
     * 新增园所
     *
     * @param gdnGardenMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增园所")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnGardenMgmtGroup.save.class}) GdnGardenMgmtBean gdnGardenMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnGardenMgmtService.save(gdnGardenMgmtBean);
    }

    @ApiOperation("更新园所")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnGardenMgmtGroup.update.class}) GdnGardenMgmtBean gdnGardenMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnGardenMgmtService.update(gdnGardenMgmtBean);
    }

    @ApiOperation("根据id查询园所")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnGardenMgmtGroup.find.class}) GdnGardenMgmtBean gdnGardenMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnGardenMgmtService.find(gdnGardenMgmtBean);
    }

    @ApiOperation("根据条件查询园所")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnGardenMgmtGroup.finds.class}) GdnGardenMgmtBean gdnGardenMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnGardenMgmtService.finds(gdnGardenMgmtBean);
    }
}
