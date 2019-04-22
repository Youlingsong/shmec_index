package com.datahome.controller;

import com.datahome.bean.CityBean;
import com.datahome.service.CityService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xl
 * @Description: 行政区域
 * @Date: Create in 2018/5/4 15:09
 */

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Resource
    private CityService cityService;

    /**
     * 查询城市 下拉框
     *
     * @param cityBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/cityCombox.do")
    public String cityCombox( CityBean cityBean, BindingResult bindingResult) {
        return cityService.combox(cityBean);
    }

    @RequestMapping("/findAll.do")
    public String findALl() {
        return cityService.findAll();
    }
}
