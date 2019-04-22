package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 本地城市 和数据库映射表
 * @Date: Create in 2019/1/3 15:21
 */

@Setter
@Getter
@Entity
@Table(name = "age06_agency")
public class AgencyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String gdn_agencyId;

    private String age06_cityId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
