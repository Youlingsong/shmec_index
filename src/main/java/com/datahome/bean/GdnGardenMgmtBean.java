package com.datahome.bean;

import com.datahome.group.GdnGardenMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class GdnGardenMgmtBean {
    @NotNull(groups = {GdnGardenMgmtGroup.delete.class, GdnGardenMgmtGroup.find.class, GdnGardenMgmtGroup.update.class})
    private String id;
    //机构id,并没有外键
    @NotNull(groups = {GdnGardenMgmtGroup.save.class})
    private String gdnAgencyid;
    //网站主页
    private String homePageUrl;
    //区
    private String district;
    //街道
    private String street;
    //编号
    private String code;
    //名称
    private String name;
    //英文名称
    private String englishName;
    //简称
    private String simpleName;
    //机构（园所）成立年月
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //机构负责人（园长）姓名
    private String kinderGardenName;
    //机构负责人（园长）手机号码
    private String kinderGardenTelephone;
    //机构负责人（园长）固定电话
    private String binderGardenPhone;
    //保健员姓名
    private String healthWorkersName;
    //保健员电话
    private String healthWorkersPhone;
    //传真电话
    private String faxTelephone;
    //办公电话
    private String officeTelephone;
    //级别
    private String level;
    //是否城镇小区配套幼儿园
    private String czxqpt;
    //邮政编码
    private String postcode;
    //地址
    private String address;
    //数据状态
    private String status;

    //是否开通账号
    private String openAccount;
    //账号名称
    private String accountName;
    //账号显示名称
    private String accountDisplayName;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
