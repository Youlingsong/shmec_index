package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_city")
public class CityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 父类id
    @Column(nullable = false)
    private Integer parentId;

    // 城市等级  省级 0   市级 1  县级 2
    @Column(nullable = false)
    private String levelCode;

    // 城市状态  1有效   2 无效
    @Column(nullable = false)
    private String cityStatus;

    // 城市名称
    @Column(nullable = false)
    private String cityName;

    @Column
    private String nodeIds;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
