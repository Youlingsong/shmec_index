package com.datahome.controller;

import com.datahome.bean.IndexBean;
import com.datahome.group.IndexGroup;
import com.datahome.service.IndexService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 前台指标查询
 * @Date: Create in 2018/5/18 9:11
 */

@RestController
@RequestMapping(value = "/index")
public class IndexController {


    @Resource
    private IndexService indexService;

    /**
     * 查询列表  加载指标树 和当前用户可查看的指标
     *
     * @param indexBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexGroup.finds.class}) IndexBean indexBean, BindingResult bindingResult) {
        return indexService.finds(indexBean);
    }

    /**
     * 查询 所有的根级指标
     */
    @RequestMapping("/findRootIndex.do")
    public String findRootIndex() {
        return indexService.findsRootIndexs();
    }
}
