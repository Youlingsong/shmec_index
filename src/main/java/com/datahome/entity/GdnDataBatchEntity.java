package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "age06_gdn_databatch")
//存放数据的批次,生成的中间数据中,标记id
public class GdnDataBatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //年
    @Column(nullable = false)
    private String year;

    //月
    @Column(nullable = false)
    private String month;

    //日
    @Column(nullable = false)
    private String day;
}
