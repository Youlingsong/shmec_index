package com.datahome.bean;

import com.datahome.group.GdnParentMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class GdnParentMgmtBean {
    @NotNull(groups = {GdnParentMgmtGroup.delete.class, GdnParentMgmtGroup.find.class, GdnParentMgmtGroup.update.class})
    private String id;
    //姓名

    private String name;
    //性别。词汇表

    private String gender;
    //用户名

    private String userName;
    //出生日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    //联系电话

    private String phone;
    //证件类型，词汇表

    private String IDType;
    //证件号

    private String IDNo;
    //电子邮件

    private String email;
    //头像（图片路径）

    private String portrait;
    //备注

    private String remarks;
    //状态，词汇表

    private String status;

    //教育水平，词汇表

    private String JYSP;
    //文化程度，词汇表

    private String WHCD;
    //从事职业，词汇表

    private String CSZY;
    //政治面貌，词汇表

    private String ZZMM;
    //婚姻状况，词汇表

    private String HYZK;
    //国籍，词汇表

    private String GJ;
    //民族，词汇表

    private String MZ;
    //工作单位

    private String GZDW;
    //休息日，词汇表

    private String XXR;
    //联系地址

    private String LXDZ;
    //开通账户

    private String openAccount;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
