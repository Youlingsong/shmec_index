package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_teacherwage")
public class GdnTeacherWageEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //园所id
    @Column(length =36)
    private String gdnGardenid;
    //教师id
    @Column(length = 36)
    private String gdnTeacherId;
    //年份
    @Column(columnDefinition = "smallint")
    private Integer year;
    //上年度考核情况
    @Column(length = 50)
    private String SNDKH;
    //绩效工资(元/月)
    @Column(precision = 9, scale = 2)
    private BigDecimal SR_JXGZ;
    //基本工资(元/月)
    @Column(precision = 9, scale = 2)
    private BigDecimal SR_JBGZ;
    //其他津贴补贴(元/月)
    @Column(precision = 9, scale = 2)
    private BigDecimal SR_QTBT;
    //上年平均收入(元/月)
    @Column(precision = 9, scale = 2)
    private BigDecimal SR_SNPJ;
    //参加社保类型
    @Column(length = 50)
    private String SHB_LZ;
    //有无基本养老保险
    @Column(length = 50)
    private String SHB_JBYL;
    //有无医疗保险
    @Column(length = 50)
    private String SHB_YLBX;
    //有无住房公积金
    @Column(length = 50)
    private String SHB_GJJ;
    //有无工伤保险
    @Column(length = 50)
    private String SHB_GSBX;
    //有无失业保险
    @Column(length = 50)
    private String SHB_SYEBX;
    //有无生育保险
    @Column(length = 50)
    private String SHB_SYUBX;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
