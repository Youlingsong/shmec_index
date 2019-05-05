package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "age06_gdn_middledata")
public class GdnMiddleDataEntity {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;

    @Column(length = 200)
    private Integer value;

    //备注
    @Column(length = 20)
    private String remake;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middledataruleid")
    private GdnMiddleDataRuleEntity gdnMiddleDataRuleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "databatchid")
    private GdnDataBatchEntity gdnDataBatchEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityid")
    private GdnCityEntity gdnCityEntity;

}
