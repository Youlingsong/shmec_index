package com.datahome.controller;


import com.datahome.bean.GdnScoringTaskBean;
import com.datahome.group.GdnScoringTaskGroup;
import com.datahome.service.GdnScoringTaskService;
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
@Api("打分任务管理")
@RequestMapping(value = "/mgmt/ScoringTask")
public class GdnScoringTaskController {

    @Resource
    private GdnScoringTaskService gdnScoringTaskService;

    @ApiOperation("新增打分任务")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnScoringTaskGroup.save.class}) GdnScoringTaskBean gdnScoringTaskBean, BindingResult bindingResult) throws Exception {
        return gdnScoringTaskService.save(gdnScoringTaskBean);
    }

    @ApiOperation("更新打分任务")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnScoringTaskGroup.update.class}) GdnScoringTaskBean gdnScoringTaskBean, BindingResult bindingResult) throws Exception {
        return gdnScoringTaskService.update(gdnScoringTaskBean);
    }

    @ApiOperation("根据id查询打分任务")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnScoringTaskGroup.find.class}) GdnScoringTaskBean gdnScoringTaskBean, BindingResult bindingResult) throws Exception {
        return gdnScoringTaskService.find(gdnScoringTaskBean);
    }

    @ApiOperation("根据条件查询打分任务")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnScoringTaskGroup.finds.class}) GdnScoringTaskBean gdnScoringTaskBean, BindingResult bindingResult) throws Exception {
        return gdnScoringTaskService.finds(gdnScoringTaskBean);
    }
}
