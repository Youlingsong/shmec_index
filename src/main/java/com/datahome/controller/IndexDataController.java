package com.datahome.controller;

import com.datahome.bean.IndexDataBean;
import com.datahome.group.IndexDataGroup;
import com.datahome.service.IndexDataService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 指标数据前台
 * @Date: Create in 2018/5/21 15:36
 */

@RestController
@RequestMapping(value = "/indexData")
public class IndexDataController {

    @Resource
    private IndexDataService indexDataService;

    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexDataGroup.finds.class}) IndexDataBean indexDataBean, BindingResult bindingResult) {
        return indexDataService.finds(indexDataBean);
    }
}