package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_middledatarule")
public class GdnMiddleDataRuleEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //sql语句
    @Column(length = 2000,unique=true)
    private String sqlsentence;
    //规则名称
    @Column(length = 50)
    private String name;
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
