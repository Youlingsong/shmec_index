package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_parent")
public class GdnParentEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //姓名
    @Column(length = 200)
    private String name;
    //性别。词汇表
    @Column(length = 20)
    private String gender;
    //用户名
    @Column(length = 100)
    private String userName;
    //出生日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    //联系电话
    @Column(length = 200)
    private String phone;
    //证件类型，词汇表
    @Column(length = 20)
    private String IDType;
    //证件号
    @Column(length = 50)
    private String IDNo;
    //电子邮件
    @Column(length = 400)
    private String email;
    //头像（图片路径）
    @Column(length = 100)
    private String portrait;
    //备注
    @Column(length = 2000)
    private String remarks;
    //状态，词汇表
    @Column(length = 20)
    private String status;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //教育水平，词汇表
    @Column(length = 200)
    private String JYSP;
    //文化程度，词汇表
    @Column(length = 200)
    private String WHCD;
    //从事职业，词汇表
    @Column(length = 20)
    private String CSZY;
    //政治面貌，词汇表
    @Column(length = 200)
    private String ZZMM;
    //婚姻状况，词汇表
    @Column(length = 20)
    private String HYZK;
    //国籍，词汇表
    @Column(length = 20)
    private String GJ;
    //民族，词汇表
    @Column(length = 20)
    private String MZ;
    //工作单位
    @Column(length = 500)
    private String GZDW;
    //休息日，词汇表
    @Column(length = 100)
    private String XXR;
    //联系地址
    @Column(length = 500)
    private String LXDZ;
    //开通账户
    @Column(length = 10)
    private String openAccount;
}
