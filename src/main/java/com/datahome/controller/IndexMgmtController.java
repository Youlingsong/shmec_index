package com.datahome.controller;

import com.datahome.bean.IndexMgmtBean;
import com.datahome.group.IndexMgmtGroup;
import com.datahome.middledata.IndexModel;
import com.datahome.service.IndexMgmtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @Author xl
 * @Description:指标后台管理
 * @Date: Create in 2018/5/10 15:49
 */
@RestController
@Api("指标体系管理")
@RequestMapping(value = "/mgmt/index")
public class IndexMgmtController {

    @Resource
    private IndexMgmtService indexMgmtService;


    /**
     * 新增指标
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, IndexMgmtGroup.save.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) throws Exception {
        return indexMgmtService.save(indexMgmtBean);
    }

    /**
     * 查询单条
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, IndexMgmtGroup.find.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.find(indexMgmtBean);
    }

    /**
     * 查询列表
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexMgmtGroup.finds.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.finds(indexMgmtBean);
    }

    /**
     * 修改
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, IndexMgmtGroup.update.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) throws Exception {
        return indexMgmtService.update(indexMgmtBean);
    }

    /**
     * 查询指标子级
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/combox.do")
    public String combox(@Validated({Default.class, IndexMgmtGroup.combox.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.combox(indexMgmtBean);
    }

    /**
     * 查询所有指标
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/findAllIndex.do")
    public String findALlIndex(@Validated({Default.class, IndexMgmtGroup.findALlIndex.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.findALlIndex(indexMgmtBean);
    }

    /**
     * 导出excel格式的数据
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/exportExcel.do")
    public String exportExcel(HttpServletResponse response, @Validated({Default.class, IndexMgmtGroup.exportExcel.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.exportExcelIndex(indexMgmtBean, response);
    }

    /**
     * 删除指标体系
     *
     * @param indexMgmtBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("删除指标体系")
    @PostMapping("/delete.do")
    public String delete(HttpServletResponse response, @Validated({Default.class, IndexMgmtGroup.delete.class}) IndexMgmtBean indexMgmtBean, BindingResult bindingResult) {
        return indexMgmtService.delete(indexMgmtBean);
    }


    /**
     * 指标体系打分
     *
     * @param list
     * @param bindingResult
     * @return
     */
    @ApiOperation("指标体系打分")
    @PostMapping("/saveScoringtaskScore.do")
    public String saveScoringtaskScore(HttpServletResponse response, @Validated({Default.class, IndexMgmtGroup.save.class}) @RequestBody  List<IndexModel> list, BindingResult bindingResult) {
      return list.toString();
    }

}
