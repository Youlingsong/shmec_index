package com.datahome.controller;

import com.datahome.bean.GdnChildMgmtBean;
import com.datahome.group.GdnChildMgmtGroup;
import com.datahome.service.GdnChildMgmtService;
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
@Api("幼儿管理")
@RequestMapping(value = "/mgmt/Child")
public class GdnChildMgmtController {
    @Resource
    private GdnChildMgmtService gdnChildMgmtService;
    /**
     * 新增幼儿
     *
     * @param gdnChildMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增幼儿")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnChildMgmtGroup.save.class}) GdnChildMgmtBean gdnChildMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnChildMgmtService.save(gdnChildMgmtBean);
    }

    @ApiOperation("更新幼儿")
    @PostMapping("/update.do")
    public String update(@Validated({Default.class, GdnChildMgmtGroup.update.class}) GdnChildMgmtBean gdnChildMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnChildMgmtService.update(gdnChildMgmtBean);
    }

    @ApiOperation("根据id查询幼儿")
    @PostMapping("/find.do")
    public String find(@Validated({Default.class, GdnChildMgmtGroup.find.class}) GdnChildMgmtBean gdnChildMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnChildMgmtService.find(gdnChildMgmtBean);
    }

    @ApiOperation("根据条件查询幼儿")
    @PostMapping("/finds.do")
    public String finds(@Validated({Default.class, GdnChildMgmtGroup.finds.class}) GdnChildMgmtBean gdnChildMgmtBean, BindingResult bindingResult) throws Exception {
        return gdnChildMgmtService.finds(gdnChildMgmtBean);
    }
}
