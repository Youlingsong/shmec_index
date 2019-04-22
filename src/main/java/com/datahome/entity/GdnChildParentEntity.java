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
@Table(name = "age06_gdn_childparent")
public class GdnChildParentEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gdnchildid")
    private GdnChildEntity gdnChildEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gdnparentid")
    private GdnParentEntity gdnParentEntity;
    //关系，词汇表
    @Column(length = 200)
    private String relation;
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
    //状态，词汇表
    @Column(length = 20)
    private String status;
    //是否监护人，词汇表
    @Column(length = 20)
    private String JHR;
}
