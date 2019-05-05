package com.datahome.controller;

import com.datahome.bean.IndexUnitsMgmtBean;
import com.datahome.group.IndexUnitsMgmtGroup;
import com.datahome.service.IndexUnitsMgmtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api("指标单位管理")
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
    @PostMapping("/save.do")
    @ApiOperation("新增指标单位")
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
    @PostMapping("/find.do")
    @ApiOperation("查询单条信息")
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
    @PostMapping("/finds.do")
    @ApiOperation("查询列表(所有指标单位并不是条件查询)")
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
    @PostMapping("/update.do")
    @ApiOperation("修改指标单位")
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
    @PostMapping("/delete.do")
    @ApiOperation("删除指标单位")
    public String delete(@Validated({Default.class, IndexUnitsMgmtGroup.delete.class}) IndexUnitsMgmtBean indexUnitsMgmtBean, BindingResult bindingResult) {
        return indexUnitsMgmtService.delete(indexUnitsMgmtBean);
    }
}
