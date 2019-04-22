package com.datahome.controller;

import com.datahome.bean.NewsMgmtBean;
import com.datahome.group.NewsMgmtGroup;
import com.datahome.service.NewsMgmtService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description:新闻 后台管理系统
 * @Date: Create in 2018/1/17 14:20
 */

@RestController
@RequestMapping("/mgmt/news")
public class NewsMgmtController {

    @Resource
    private NewsMgmtService newsService;


    /**
     * 添加新闻
     */
    @RequestMapping("/save.do")
    public String save(@Validated({Default.class, NewsMgmtGroup.save.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.save(nb);
    }

    /**
     * 查询单条新闻
     */

    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, NewsMgmtGroup.find.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.find(nb);
    }

    /**
     * 查询新闻列表
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, NewsMgmtGroup.finds.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.finds(nb);
    }

    /**
     * 修改新闻
     */

    @RequestMapping("/update.do")
    public String updates(@Validated({Default.class, NewsMgmtGroup.updates.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.updates(nb);
    }

    /**
     * 删除新闻
     */
    @RequestMapping("/delete.do")
    public String delete(@Validated({Default.class, NewsMgmtGroup.delete.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.delete(nb);
    }

    /**
     * 新增新闻分类
     */

    @RequestMapping("/saveNewsClasses.do")
    public String saveNewsClasses(@Validated({Default.class, NewsMgmtGroup.saveNewsClasses.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.saveNewsClasses(nb);
    }

    /**
     * 查询新闻分类列表
     */
    @RequestMapping("/findClassesList.do")
    public String findCombox(@Validated({Default.class, NewsMgmtGroup.findClassesList.class}) NewsMgmtBean nb, BindingResult result) throws Exception {
        return newsService.findClassesList(nb);
    }

    /**
     * 查询单个新闻分类
     */
    @RequestMapping("/findNewsClasses.do")
    public String findNewsClasses(@Validated({Default.class, NewsMgmtGroup.findNewsClasses.class}) NewsMgmtBean nb, BindingResult result) throws Exception {
        return newsService.findNewsClasses(nb);
    }

    /**
     * 修改新闻分类
     */

    @RequestMapping("/updateNewsClasses.do")
    public String updateNewsClasses(@Validated({Default.class, NewsMgmtGroup.updateNewsClasses.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.updateNewsClasses(nb);
    }

    /**
     * 删除新闻分类
     */
    @RequestMapping("/deleteNewsClasses.do")
    public String deleteNewsClasses(@Validated({Default.class, NewsMgmtGroup.deleteNewsClasses.class}) NewsMgmtBean nb, BindingResult result) {
        return newsService.deleteNewsClasses(nb);
    }
}
