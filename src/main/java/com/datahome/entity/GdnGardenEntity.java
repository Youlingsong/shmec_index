package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_garden")
public class GdnGardenEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //机构id,并没有外键
    @Column(length = 36)
    private String gdnAgencyid;
    //网站主页
    @Column(length = 400)
    private String homePageUrl;
    //区
    @Column(length = 50)
    private String district;
    //街道
    @Column(length = 50)
    private String street;
    //编号
    @Column(length = 50)
    private String code;
    //名称
    @Column(length = 100)
    private String name;
    //英文名称
    @Column(length = 200)
    private String englishName;
    //简称
    @Column(length = 500)
    private String simpleName;
    //机构（园所）成立年月
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //机构负责人（园长）姓名
    @Column(length = 50)
    private String kinderGardenName;
    //机构负责人（园长）手机号码
    @Column(length = 50)
    private String kinderGardenTelephone;
    //机构负责人（园长）固定电话
    @Column(length = 50)
    private String binderGardenPhone;
    //保健员姓名
    @Column(length = 50)
    private String healthWorkersName;
    //保健员电话
    @Column(length = 50)
    private String healthWorkersPhone;
    //传真电话
    @Column(length = 50)
    private String faxTelephone;
    //办公电话
    private String officeTelephone;
    //级别
    @Column(length = 50)
    private String level;
    //是否城镇小区配套幼儿园
    @Column(length = 50)
    private String czxqpt;
    //邮政编码
    @Column(length = 50)
    private String postcode;
    //地址
    @Column(length = 200)
    private String address;
    //数据状态
    @Column(length = 50)
    private String status;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //是否开通账号
    @Column(length = 20)
    private String openAccount;
    //账号名称
    @Column(length = 50)
    private String accountName;
    //账号显示名称
    @Column(length = 50)
    private String accountDisplayName;
}
