package com.datahome.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "age06_gdn_city")
public class GdnCityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    // 城市等级  市级 0   区级 1   2幼儿园级
    @Column(nullable = false)
    private String levelCode;

    // 城市状态  1有效   2 无效
    @Column(nullable = false)
    private String cityStatus;

    // 城市名称
    @Column(nullable = false)
    private String cityName;

    // 城市名称
    @Column(nullable = false)
    private String cityCode;

    // sql拼写规则
    @Column(nullable = false)
    private String areasqlRule;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
