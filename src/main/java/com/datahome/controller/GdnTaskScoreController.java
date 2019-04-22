package com.datahome.controller;


import com.datahome.bean.GdnTaskScoreBean;
import com.datahome.group.GdnTaskScoreGroup;
import com.datahome.service.GdnTaskScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@Api("指标任务打分管理")
@RequestMapping(value = "/mgmt/ScoringTask")
public class GdnTaskScoreController {
    @Resource
    private GdnTaskScoreService gdnTaskScoreService;

    /**
     * 指标体系打分
     *
     * @param listGdnTaskScoreBean
     * @param bindingResult
     * @return
     */
    @ApiOperation("指标体系打分")
    @PostMapping("/saveScore.do")
    public String saveScoringtaskScore( @Validated({Default.class, GdnTaskScoreGroup.save.class}) @RequestBody List<GdnTaskScoreBean> listGdnTaskScoreBean, BindingResult bindingResult) {
        return gdnTaskScoreService.saveScore(listGdnTaskScoreBean);
    }
}
