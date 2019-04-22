package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 新闻分类
 * @Date: Create in 2018/1/16 13:35
 */
@Getter
@Setter
@Entity
@Table(name = "age06_newsclasses")
public class NewsClassesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer parentId;

    @Column
    private String nodeIds;

    @Column(nullable = false)
    private String classes;

    @Column
    private String placeCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
