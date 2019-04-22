package com.datahome.controller;

import com.datahome.bean.GdnTeacherTrainMgmtBean;
import com.datahome.group.GdnTeacherTrainMgmtGroup;
import com.datahome.service.GdnTeacherTrainMgmtService;
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
@Api("教师培训管理")
@RequestMapping(value = "/mgmt/GdnTeacherTrain")
public class GdnTeacherTrainMgmtController {
    @Resource
    private GdnTeacherTrainMgmtService gdnTeacherTrainMgmtService;


    /**
     * 新增教师培训
     *
     * @param gdnTeacherTrainMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增教师培训")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnTeacherTrainMgmtGroup.save.class}) GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherTrainMgmtService.save(gdnTeacherTrainMgmtBean);
    }

    @ApiOperation("更新教师培训")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnTeacherTrainMgmtGroup.update.class}) GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherTrainMgmtService.update(gdnTeacherTrainMgmtBean);
    }

    @ApiOperation("根据id查询教师培训")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnTeacherTrainMgmtGroup.find.class}) GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherTrainMgmtService.find(gdnTeacherTrainMgmtBean);
    }

    @ApiOperation("根据条件查询教师培训")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnTeacherTrainMgmtGroup.finds.class}) GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherTrainMgmtService.finds(gdnTeacherTrainMgmtBean);
    }
}
