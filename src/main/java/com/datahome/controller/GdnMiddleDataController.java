package com.datahome.controller;


import com.datahome.bean.GdnMiddleDataBean;
import com.datahome.group.GdnMiddleDataGroup;
import com.datahome.service.GdnMiddleDataService;
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
@Api("中间数管理")
@RequestMapping(value = "/MiddleData")
public class GdnMiddleDataController {

    @Resource
    private GdnMiddleDataService gdnMiddleDataService;
    /**
     * 新增中间数
     *
     * @param gdnMiddleDataBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增中间数")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnMiddleDataGroup.save.class}) GdnMiddleDataBean gdnMiddleDataBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataService.save(gdnMiddleDataBean);
    }

    @ApiOperation("更新中间数")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnMiddleDataGroup.update.class}) GdnMiddleDataBean gdnMiddleDataBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataService.update(gdnMiddleDataBean);
    }

    @ApiOperation("根据id查询中间数")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnMiddleDataGroup.find.class}) GdnMiddleDataBean gdnMiddleDataBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataService.find(gdnMiddleDataBean);
    }

    @ApiOperation("根据条件查询中间数")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnMiddleDataGroup.finds.class}) GdnMiddleDataBean gdnMiddleDataBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataService.finds(gdnMiddleDataBean);
    }
}
