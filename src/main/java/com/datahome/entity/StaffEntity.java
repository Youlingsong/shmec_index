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
@Table(name = "age06_staff")
public class StaffEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @Column
    private String password;

    // 1 正常  2 禁用
    @Column
    private String status;

    // 1系统初始化超级管理员，不可删除  2 超级管理员新增的一般管理员
    @Column
    private String adminFlag;

    @Column
    private String phone;

    @Column
    private String email;

    // 地域
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId", nullable = false)
    private GdnCityEntity gdncityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private StaffRoleEntity staffRoleEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
