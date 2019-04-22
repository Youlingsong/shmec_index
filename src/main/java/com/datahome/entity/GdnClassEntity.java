package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
//
@Setter
@Getter
@Entity
@Table(name = "age06_gdn_class")
public class GdnClassEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gdngardenid")
    private GdnGardenEntity gdnGardenEntity;
    //名称
    @Column(length = 20)
    private String name;
    //班号
    @Column(length = 50)
    private String code;
    //年级，词汇表
    @Column(length = 20)
    private String grade;
    //班级封面（图片路径）
    @Column(length = 100)
    private String picture;
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
    //创建年份
    @Column(length = 10)
    private String CJNF;
    //毕业年份
    @Column(length = 10)
    private String BYNF;
    //是否少数民族双语教学班，词汇表
    @Column(length = 20)
    private String SSMZSY;
    //少数民族双语教学模式，词汇表
    @Column(length = 20)
    private String SSMZSYMS;
    //最大班级人数
    @Column(columnDefinition = "smallint")
    private Integer ZDRS;
    //排序号
    @Column(columnDefinition = "smallint")
    private Integer sort;
    //班号 //Todo 有两个班号相同
    @Column(columnDefinition = "smallint")
    private Integer num;
    //昵称
    @Column(length = 50)
    private String nickName;
    //毕业后班级名称
    @Column(length = 50)
    private String graduatedName;
    //论坛BBSID
    @Column(length = 50)
    private String BBSID;
    //网站主页
    @Column(length = 200)
    private String homePageUrl;
    //栏目Id
    @Column(length = 50)
    private String ipsCategoryId;
    //论坛信息
    @Column(length = 50)
    private String BBSAuth;
}
