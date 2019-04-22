package com.datahome.controller;

import com.datahome.bean.IndexDataMgmtBean;
import com.datahome.group.IndexDataMgmtGroup;
import com.datahome.service.IndexDataMgmtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.concurrent.Future;

/**
 * @Author xl
 * @Description: 指标数据后台
 * @Date: Create in 2018/5/21 15:36
 */

@RestController
@RequestMapping(value = "/mgmt/indexData")
@Api("指标数据管理")
public class IndexDataMgmtController {

    @Resource
    private IndexDataMgmtService indexDataMgmtService;


    /**
     * 批量生成基础指标数据
     */
    @RequestMapping("/saveIndexData.do")
    public Future<String> saveIndexData(@Validated({Default.class, IndexDataMgmtGroup.saveIndexData.class}) IndexDataMgmtBean indexDataMgmtBean, BindingResult bindingResult) {
        return indexDataMgmtService.saveIndexData(indexDataMgmtBean);
    }


    /**
     * 查询指标数据列表
     *
     * @param indexDataMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexDataMgmtGroup.finds.class}) IndexDataMgmtBean indexDataMgmtBean, BindingResult bindingResult) {
        return indexDataMgmtService.finds(indexDataMgmtBean);
    }

    /**
     * 修改指标数据
     *
     * @param indexDataMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, IndexDataMgmtGroup.update.class}) IndexDataMgmtBean indexDataMgmtBean, BindingResult bindingResult) {
        return indexDataMgmtService.update(indexDataMgmtBean);
    }

    /**
     * 导出excel格式的数据
     *
     * @param indexDataMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/exportExcel.do")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, @Validated({Default.class, IndexDataMgmtGroup.exportExcel.class}) IndexDataMgmtBean indexDataMgmtBean, BindingResult bindingResult) {
        return indexDataMgmtService.exportExcelData(indexDataMgmtBean, request, response);
    }

    /**
     * 指标数据删除
     *
     * @param indexDataMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/delete.do")
    @ApiOperation("指标数据删除")
    public String delete(HttpServletRequest request, HttpServletResponse response, @Validated({Default.class, IndexDataMgmtGroup.delete.class}) IndexDataMgmtBean indexDataMgmtBean, BindingResult bindingResult) {
        return indexDataMgmtService.delete(indexDataMgmtBean, request, response);
    }

}
