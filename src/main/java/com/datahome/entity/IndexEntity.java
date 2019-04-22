package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 指标管理
 * @Date: Create in 2018/5/4 9:27
 */
@Setter
@Getter
@Entity
@Table(name = "age06_index")
public class IndexEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer parentId;

    @Column(nullable = false)
    private String name;

    //(1 发布 2 无效  3 草稿 4 待审核 )
    @Column(nullable = false)
    private String indexStatus;

    // 描述
    @Column(columnDefinition = "text")
    private String description;

    //评分标准
    @Column(columnDefinition = "text")
    private String evaluationStandards;

    //该指标的总分数
    @Column(nullable = false)
    private Double grossScore;

    //指标计量单位
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitsid", nullable = false)
    private IndexUnitsEntity indexUnitsEntity;

    @Column
    private String nodeIds;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
