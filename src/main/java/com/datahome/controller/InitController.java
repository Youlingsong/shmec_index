package com.datahome.controller;

import com.datahome.service.CalculatingDataService;
import com.datahome.service.InitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xl
 * @Description: 初始化平台数据
 * @Date: Create in 2018/7/17 20:10
 */

@RestController
@RequestMapping("/mgmt/init")
public class InitController {

    @Resource
    private InitService initService;

    @Resource
    private CalculatingDataService calculatingDataService;

    /**
     * 初始化城市数据
     *
     * @return
     */
    @RequestMapping("/initCity.do")
    public String initCity() {
        return initService.initCity();
    }

    /**
     * 初始化指标
     *
     * @return
     */
    @RequestMapping("/initIndex.do")
    public String initIndex() {
        return initService.initIndex();
    }

    /**
     * 初始化指标数据
     *
     * @return
     */
    @RequestMapping("/initIndexData.do")
    public String initIndexData() {
        return initService.initIndexData();
    }

    @RequestMapping("/calculating.do")
    public String calculating() {
        return calculatingDataService.startCalculate();
    }
}
