package com.datahome.bean;

import com.datahome.group.GdnScoringTaskGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class GdnScoringTaskBean {
    @NotNull(groups = {GdnScoringTaskGroup.delete.class, GdnScoringTaskGroup.find.class, GdnScoringTaskGroup.update.class})
    private String id;


    private String name;

    //(1 发布 2 无效  3 草稿 4 待审核 )
    private String taskstatus;

    // 描述
    private String description;


    //指定管理员打分
    @NotNull(groups = {GdnScoringTaskGroup.save.class ,GdnScoringTaskGroup.update.class})
    private Integer  staffid;

    //打分的指标
    @NotNull(groups = {GdnScoringTaskGroup.save.class ,GdnScoringTaskGroup.update.class})
    private Integer indexid;

    //截至时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date uptoTime;
}
