package com.datahome.controller;

import com.datahome.bean.IndexUnitsMgmtBean;
import com.datahome.group.IndexUnitsMgmtGroup;
import com.datahome.service.IndexUnitsMgmtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 指标单位管理
 * @Date: Create in 2018/5/4 19:25
 */

@RestController
@RequestMapping(value = "/mgmt/units")
public class IndexUnitsMgmtController {

    @Resource
    private IndexUnitsMgmtService indexUnitsMgmtService;

    /**
     * 新增单位
     *
     * @param indexUnitsMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, IndexUnitsMgmtGroup.save.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.save(indexUnitsMgmtBean);
    }

    /**
     * 查询单条信息
     *
     * @param indexUnitsMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, IndexUnitsMgmtGroup.find.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.find(indexUnitsMgmtBean);
    }

    /**
     * 查询列表
     *
     * @param indexUnitsMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexUnitsMgmtGroup.finds.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.finds(indexUnitsMgmtBean);
    }

    /**
     * 修改
     *
     * @param indexUnitsMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, IndexUnitsMgmtGroup.update.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.update(indexUnitsMgmtBean);
    }

    /**
     * 删除
     *
     * @param indexUnitsMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/delete.do")
    public String delete(@Validated({Default.class, IndexUnitsMgmtGroup.delete.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.delete(indexUnitsMgmtBean);
    }
}
