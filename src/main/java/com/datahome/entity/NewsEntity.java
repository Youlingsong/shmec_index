package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description: 新闻
 * @Date: Create in 2018/10/16 14:55
 */
@Setter
@Getter
@Entity
@Table(name = "age06_news")
public class NewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String title;

    @Column
    private String author;

    @Column
    private String titleImageUrl;

    @Column( columnDefinition = "text")
    private String summary;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private String publishFlag;

    @Column(nullable = false)
    private String status;

    @Column
    private Integer priority;

    @Column
    private String source;

    @Column(columnDefinition = "bigint default 0")
    private Long hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classesId", nullable = false)
    private NewsClassesEntity newsClassesEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


}
