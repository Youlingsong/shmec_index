package com.datahome.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_classteacher")
public class GdnClassTeacherEntity implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gdncalssid")
    private GdnClassEntity gdnClassEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gdnteacherid")
    private GdnTeacherEntity gdnTeacherEntity;
    //职务，词汇表
    @Column(length = 20)
    private String duty;
    //排序数
    @Column(columnDefinition = "smallint")
    private Integer sort;
    //备注
    @Column(length = 2000)
    private String remarks;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //状态(词汇表)
    @Column(length = 20)
    private String status;
}
