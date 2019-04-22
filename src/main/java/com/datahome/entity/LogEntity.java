package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/8 17:05
 */
@Getter
@Setter
@Entity
@Table(name="age06_log")
public class LogEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String userName;

    private String requestPath;

    private String userType;

    private String ip;

    private String resultCode;

    private String message;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
