package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/15 17:47
 */
@Getter
@Setter
@Entity
@Table(name = "age06_index_city")
public class IndexCityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indexid", nullable = false)
    private IndexEntity indexEntity;

    // 地域
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityid")
    private CityEntity cityEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
