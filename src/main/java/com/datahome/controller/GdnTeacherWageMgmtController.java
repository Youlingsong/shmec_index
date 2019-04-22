package com.datahome.controller;

import com.datahome.bean.GdnTeacherWageMgmtBean;
import com.datahome.group.GdnTeacherWageMgmtGroup;
import com.datahome.service.GdnTeacherWageMgmtService;
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
@Api("教师工资管理")
@RequestMapping(value = "/mgmt/GdnTeacherWage")
public class GdnTeacherWageMgmtController {
    @Resource
    private GdnTeacherWageMgmtService gdnTeacherWageMgmtService;


    /**
     * 新增教师工资
     *
     * @param gdnTeacherWageMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增教师工资")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnTeacherWageMgmtGroup.save.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.save(gdnTeacherWageMgmtBean);
    }

    @ApiOperation("更新教师工资")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnTeacherWageMgmtGroup.update.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.update(gdnTeacherWageMgmtBean);
    }

    @ApiOperation("根据id查询教师工资")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnTeacherWageMgmtGroup.find.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.find(gdnTeacherWageMgmtBean);
    }

    @ApiOperation("根据条件查询教师工资")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnTeacherWageMgmtGroup.finds.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.finds(gdnTeacherWageMgmtBean);
    }

    @ApiOperation("根据id删除教师工资")
    @PostMapping("/delete.do")
    public String delete(@Validated({Default.class, GdnTeacherWageMgmtGroup.delete.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.delete(gdnTeacherWageMgmtBean);
    }

    @ApiOperation("根据查询条件删除教师工资")
    @PostMapping("/deletes.do")
    public String deletes(@Validated({Default.class, GdnTeacherWageMgmtGroup.deletes.class}) GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherWageMgmtService.deletes(gdnTeacherWageMgmtBean);
    }



}
