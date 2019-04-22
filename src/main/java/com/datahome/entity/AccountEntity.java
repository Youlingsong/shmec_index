package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/10 14:21
 */
@Setter
@Getter
@Entity
@Table(name = "age06_account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @Column(columnDefinition = "varchar(255)")
    private String account;

    @Column(columnDefinition = "text")
    private String password;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String institution;

    @Column
    private String job;

    // 1 启用  2 禁用
    @Column(nullable = false)
    private String accountStatus;

    //可访问的指标（json 格式）
    @Column(columnDefinition = "text")
    private String allowReadIndexs;

    // 地域（json 格式）
    @Column(columnDefinition = "text")
    private String cityIds;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
