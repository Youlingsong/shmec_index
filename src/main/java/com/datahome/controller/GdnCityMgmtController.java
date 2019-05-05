package com.datahome.controller;

import com.datahome.bean.GdnCityBean;
import com.datahome.group.GdnCityGroup;
import com.datahome.service.GdnCityService;
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
@Api("城市对象管理")
@RequestMapping(value = "/mgmt/City")

public class GdnCityMgmtController {
    @Resource
    private GdnCityService gdnCityService;
    /**
     * 新增城市对象
     *
     * @param gdnCityBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("新增城市对象")
    @PostMapping("/save.do")
    public String save(@Validated({Default.class, GdnCityGroup.save.class}) GdnCityBean gdnCityBean, BindingResult bindingResult) throws Exception {
        return gdnCityService.save(gdnCityBean);
    }

    /**
     * 添加上海-区城市对象
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation("新增上海区城市对象")
    @PostMapping("/importSh.do")
    public String importSh(){
        return gdnCityService.importSh();
    }

    /**
     * 添加上海-区城市对象
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation("新增上海区城市对象")
    @PostMapping("/importSHGR.do")
    public String importSHGR(){
        return gdnCityService.importSHGR();
    }

}
