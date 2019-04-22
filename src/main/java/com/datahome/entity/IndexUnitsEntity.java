package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 指标单位管理
 * @Date: Create in 2018/5/4 9:22
 */
@Setter
@Getter
@Entity
@Table(name = "age06_indexunits")
public class IndexUnitsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String unitsName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
