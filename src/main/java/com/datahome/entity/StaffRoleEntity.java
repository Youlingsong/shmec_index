package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/11 9:07
 */

@Setter
@Getter
@Entity
@Table(name = "age06_staffrole")
public class StaffRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    //admin 管理员  dataEntry  数据录入员  review 审核员
    private String roleKey;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
