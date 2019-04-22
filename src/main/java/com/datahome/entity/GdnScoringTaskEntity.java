package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "age06_gdn_scoringtask")
public class GdnScoringTaskEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    //(1 发布 2 无效  3 草稿 4 待审核 )
    @Column(nullable = false)
    private String taskstatus;

    // 描述
    @Column(columnDefinition = "text")
    private String description;


    //指定管理员打分
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", nullable = false)
    private StaffEntity staffEntity;

    //打分的指标
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indexid", nullable = false)
    private IndexEntity indexEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //截至时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date uptoTime;

}
