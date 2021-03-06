package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 指标数据管理
 * @Date: Create in 2018/5/4 9:28
 */
@Setter
@Getter
@Entity
@Table(name = "age06_indexdata")
public class IndexDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    // 指标与城市  中间表
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "indexCityid", nullable = false)
//    private IndexCityEntity indexCityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "databatchid",  nullable = false)
    private GdnDataBatchEntity gdnDataBatchEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityid" ,nullable = false)
    private GdnCityEntity gdnCityEntity;

    @Column
    private Double value;


    // 指标数据状态(1 发布  2 草稿  3 无效数据 4 待审核)
    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
