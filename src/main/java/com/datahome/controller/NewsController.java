package com.datahome.controller;

import com.datahome.bean.NewsBean;
import com.datahome.group.NewsGroup;
import com.datahome.service.NewsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * @Author xl
 * @Description:新闻 模块
 * @Date: Create in 2018/1/17 14:20
 */

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    /**
     * 查询新闻详情
     */

    @RequestMapping("/find.do")
    public String find(@Validated({Default.class, NewsGroup.find.class}) NewsBean newsBean, BindingResult result) {
        return newsService.find(newsBean);
    }

    /**
     * 查询新闻列表
     */
    @RequestMapping("/finds.do")
    public String finds(@Validated({Default.class, NewsGroup.finds.class}) NewsBean newsBean, BindingResult result) {
        return newsService.finds(newsBean);
    }

    /**
     * 查询新闻分类列表
     */
    @RequestMapping("/findAll.do")
    public String findCombox(@Validated({Default.class, NewsGroup.findCombox.class}) NewsBean newsBean, BindingResult result) {
        return newsService.findNewsClasses(newsBean);
    }
}
