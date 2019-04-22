package com.datahome.controller;

import com.datahome.bean.IndexGroupMgmtBean;
import com.datahome.group.IndexGroupMgmtGroup;
import com.datahome.service.IndexGroupMgmtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description: 分组管理
 * @Date: Create in 2018/5/4 19:25
 */

@RestController
@RequestMapping(value = "/mgmt/group")
public class IndexGroupMgmtController {

    @Resource
    private IndexGroupMgmtService indexGroupMgmtService;

    /**
     * 新增分组
     *
     * @param indexGroupMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, IndexGroupMgmtGroup.save.class}) IndexGroupMgmtBean indexGroupMgmtBean, BindingResult bindingResult) {
        return indexGroupMgmtService.save(indexGroupMgmtBean);
    }

    /**
     * 查询单条信息
     *
     * @param indexGroupMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, IndexGroupMgmtGroup.find.class}) IndexGroupMgmtBean indexGroupMgmtBean, BindingResult bindingResult) {
        return indexGroupMgmtService.find(indexGroupMgmtBean);
    }

    /**
     * 查询列表
     *
     * @param indexGroupMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, IndexGroupMgmtGroup.finds.class}) IndexGroupMgmtBean indexGroupMgmtBean, BindingResult bindingResult) {
        return indexGroupMgmtService.finds(indexGroupMgmtBean);
    }

    /**
     * 修改
     *
     * @param indexGroupMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update.do")
    public String update(@Validated({Default.class, IndexGroupMgmtGroup.update.class}) IndexGroupMgmtBean indexGroupMgmtBean, BindingResult bindingResult) {
        return indexGroupMgmtService.update(indexGroupMgmtBean);
    }

    /**
     * 删除
     *
     * @param indexGroupMgmtBean
     * @param bindingResult
     * @return
     */
    @RequestMapping("/delete.do")
    public String delete(@Validated({Default.class, IndexGroupMgmtGroup.delete.class}) IndexGroupMgmtBean indexGroupMgmtBean, BindingResult bindingResult) {
        return indexGroupMgmtService.delete(indexGroupMgmtBean);
    }
}
