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
@Table(name = "age06_gdn_teachertrain")
public class GdnTeacherTrainEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //教师id,并没有做关联
    @Column(length = 36)
    private String gdnTeacherId;
    //教职工参与培训编号
    @Column(length = 40)
    private String code;
    //机构编号
    @Column(length = 12)
    private String agencycode;
    //姓名
    @Column(length = 36)
    private String name;
    //项目名称
    @Column(length = 180)
    private String projectname;
    //级别
    @Column(length = 10)
    private String level;
    //培训时长是否大于一月
    @Column(columnDefinition = "smallint")
    private int trainlength;
    // 天_小时
    @Column(precision = 5, scale = 1)
    private BigDecimal hourday;
    //年度
    @Column(length = 4)
    private String year;
    //操作者Id
    @Column(length = 16)
    private String operatorId;
    //操作者
    @Column(length = 50)
    private String operator;
    //机构Id
    @Column(length = 36)
    private String gdnAgencyId;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


}
