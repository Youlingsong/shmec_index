package com.datahome.controller;

import com.datahome.bean.GdnMiddleDataRuleBean;
import com.datahome.group.GdnMiddleDataGroup;
import com.datahome.group.GdnMiddleDataRuleGroup;
import com.datahome.service.GdnMiddleDataRuleService;
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
@Api("中间数计算规则管理")
@RequestMapping(value = "/MiddleDataRule")
public class GdnMiddleDataRuleController {

    @Resource
    private GdnMiddleDataRuleService gdnMiddleDataRuleService;
    /**
     * 新增中间数计算规则
     *
     * @param gdnMiddleDataRuleBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增中间数计算规则")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnMiddleDataGroup.save.class}) GdnMiddleDataRuleBean gdnMiddleDataRuleBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataRuleService.save(gdnMiddleDataRuleBean);
    }

    @ApiOperation("更新中间数计算规则")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnMiddleDataRuleGroup.update.class}) GdnMiddleDataRuleBean gdnMiddleDataRuleBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataRuleService.update(gdnMiddleDataRuleBean);
    }

    @ApiOperation("根据id查询中间数计算规则")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnMiddleDataRuleGroup.find.class}) GdnMiddleDataRuleBean gdnMiddleDataRuleBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataRuleService.find(gdnMiddleDataRuleBean);
    }

    @ApiOperation("根据条件查询中间数计算规则")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnMiddleDataRuleGroup.finds.class}) GdnMiddleDataRuleBean gdnMiddleDataRuleBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataRuleService.finds(gdnMiddleDataRuleBean);
    }

    @ApiOperation("根据id删除中间数计算规则")
    @PostMapping("/delete.do")
    public String delete(@Validated({Default.class, GdnMiddleDataRuleGroup.delete.class}) GdnMiddleDataRuleBean gdnMiddleDataRuleBean, BindingResult bindingResult) throws Exception {
        return gdnMiddleDataRuleService.delete(gdnMiddleDataRuleBean);
    }
}
