package com.datahome.controller;


import com.datahome.bean.GdnAgencyMgmtBean;
import com.datahome.group.GdnAgencyMgmtGroup;
import com.datahome.service.GdnAgencyMgmtService;
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
@Api("机构管理")
@RequestMapping(value = "/mgmt/gdnAgency")
public class GdnAgencyMgmtController {
    @Resource
    private GdnAgencyMgmtService gdnAgencyMgmtService;


    /**
     * 新增机构
     *
     * @param gdnAgencyMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增机构")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnAgencyMgmtGroup.save.class}) GdnAgencyMgmtBean gdnAgencyMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnAgencyMgmtService.save(gdnAgencyMgmtBean);
    }

    @ApiOperation("更新机构")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnAgencyMgmtGroup.update.class}) GdnAgencyMgmtBean gdnAgencyMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnAgencyMgmtService.update(gdnAgencyMgmtBean);
    }

    @ApiOperation("根据id查询机构")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnAgencyMgmtGroup.find.class}) GdnAgencyMgmtBean gdnAgencyMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnAgencyMgmtService.find(gdnAgencyMgmtBean);
    }

    @ApiOperation("根据条件查询机构")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnAgencyMgmtGroup.finds.class}) GdnAgencyMgmtBean gdnAgencyMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnAgencyMgmtService.finds(gdnAgencyMgmtBean);
    }

    @ApiOperation("批量导入机构基础数据")
    @PostMapping("/importExcel.do")
    public String importExcel(HttpServletResponse response, @RequestParam("importExcel") MultipartFile file
    ) throws Exception {
        return gdnAgencyMgmtService.importExcel(file, response);
    }
}
