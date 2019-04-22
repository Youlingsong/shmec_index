package com.datahome.controller;

import com.datahome.bean.CityMgmtBean;
import com.datahome.group.CityMgmtGroup;
import com.datahome.service.CityMgmtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description:  城市管理
 * @Date: Create in 2018/10/9 14:42
 */
@RestController
@RequestMapping(value = "/mgmt/city")
public class CityMgmtController {

    @Resource
    private CityMgmtService cityMgmtService;


    @RequestMapping("/initCity.do")
    public String initCity() throws Exception {
        return cityMgmtService.initCity();
    }

    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, CityMgmtGroup.save.class}) CityMgmtBean cityBean, BindingResult bindingResult) {
        return cityMgmtService.save(cityBean);
    }

    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, CityMgmtGroup.update.class}) CityMgmtBean cityBean, BindingResult bindingResult) {
        return cityMgmtService.update(cityBean);
    }

    @RequestMapping("/cityCombox.do")
    public String cityCombox(@Validated({Default.class, CityMgmtGroup.combox.class}) CityMgmtBean cityBean, BindingResult bindingResult) {
        return cityMgmtService.combox(cityBean);
    }

    @RequestMapping("/findAll.do")
    public String findALl(@Validated({Default.class, CityMgmtGroup.combox.class}) CityMgmtBean cityBean, BindingResult bindingResult) {
        return cityMgmtService.findAll(cityBean);
    }
}
