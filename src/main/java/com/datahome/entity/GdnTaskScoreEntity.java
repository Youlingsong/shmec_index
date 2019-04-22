package com.datahome.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_taskscore")
public class GdnTaskScoreEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;

    //得分
    private Integer score;

    //指定管理员打分
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", nullable = false)
    private StaffEntity staffEntity;

    //打分的指标
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indexid", nullable = false)
    private IndexEntity indexEntity;

    //打分任务
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scoretaskid", nullable = false)
    private GdnScoringTaskEntity gdnScoringTaskEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

}
