package com.datahome.bean;

import com.datahome.group.GdnTaskScoreGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GdnTaskScoreBean {

    private String id;

    @NotNull(groups = {GdnTaskScoreGroup.save.class ,GdnTaskScoreGroup.update.class})
    private Integer score;

    //指定管理员打分
    @NotNull(groups = {GdnTaskScoreGroup.save.class ,GdnTaskScoreGroup.update.class})
    private Integer  staffid;

    //打分的指标
    @NotNull(groups = {GdnTaskScoreGroup.save.class ,GdnTaskScoreGroup.update.class})
    private Integer indexid;

    //打分的指标
    @NotNull(groups = {GdnTaskScoreGroup.save.class ,GdnTaskScoreGroup.update.class})
    private String scoringtaskid;
}
