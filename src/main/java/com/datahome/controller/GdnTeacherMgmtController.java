package com.datahome.controller;

import com.datahome.bean.GdnClassTeacherMgmtBean;
import com.datahome.bean.GdnTeacherMgmtBean;
import com.datahome.group.GdnTeacherClassMgmtGroup;
import com.datahome.group.GdnTeacherMgmtGroup;
import com.datahome.service.GdnTeacherMgmtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

@RestController
@Api("教师管理")
@RequestMapping(value = "/mgmt/GdnTeacher")
public class GdnTeacherMgmtController {
    @Resource
    private GdnTeacherMgmtService gdnTeacherMgmtService;


    /**
     * 新增教师
     *
     * @param gdnTeacherMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增教师")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnTeacherMgmtGroup.save.class}) GdnTeacherMgmtBean gdnTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.save(gdnTeacherMgmtBean);
    }

    @ApiOperation("更新教师")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnTeacherMgmtGroup.update.class}) GdnTeacherMgmtBean gdnTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.update(gdnTeacherMgmtBean);
    }

    @ApiOperation("根据id查询教师")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnTeacherMgmtGroup.find.class}) GdnTeacherMgmtBean gdnTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.find(gdnTeacherMgmtBean);
    }

    @ApiOperation("根据条件查询教师")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnTeacherMgmtGroup.finds.class}) GdnTeacherMgmtBean gdnTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.finds(gdnTeacherMgmtBean);
    }

    @ApiOperation("添加班级教师中间表")
    @PostMapping("/add.do")
    public String add(@Validated({Default.class, GdnTeacherClassMgmtGroup.save.class}) GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.add(gdnClassTeacherMgmtBean);
    }

    @ApiOperation("查询班级教师中间表")
    @PostMapping("/serach.do")
    public String serach(@Validated({Default.class, GdnTeacherClassMgmtGroup.finds.class}) GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnTeacherMgmtService.serach(gdnClassTeacherMgmtBean);
    }

    @ApiOperation("批量导入教师基础数据")
    @PostMapping("/importExcel.do")
    public String importExcel(HttpServletResponse response, @RequestParam("importExcel") MultipartFile file
                              ) throws Exception {
        return gdnTeacherMgmtService.importExcel(file,response);
    }


}
