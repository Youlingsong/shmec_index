package com.datahome.bean;


import com.datahome.group.GdnChildMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class GdnChildMgmtBean {
    @NotNull(groups = {GdnChildMgmtGroup.delete.class, GdnChildMgmtGroup.find.class, GdnChildMgmtGroup.update.class})
    private String id;
    //班级id
    @NotNull(groups = {GdnChildMgmtGroup.save.class,GdnChildMgmtGroup.update.class})
    private String gdnClassid;
    //年级

    private String grade;
    //园所ID
    @NotNull(groups = {GdnChildMgmtGroup.save.class,GdnChildMgmtGroup.update.class})
    private String gdnGardenid;
    //幼儿姓名

    private String name;
    //姓名拼音

    private String XMPY;
    //性别

    private String gender;
    //出生日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    //学号

    private String code;
    //头像路径

    private String portrait;
    //证件类型

    private String IDType;
    //证件号码

    private String IDNo;
    //备注

    private String remarks;
    //幼儿状态

    private String status;

    //曾用名/英文名

    private String CYM;
    //出生地

    private String CSD;
    //入园日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date RYRQ;
    //就读方式

    private String JDFS;
    //国籍

    private String GJ;
    //民族

    private String MZ;
    //家庭出生，词汇表

    private String JTCS;
    //港澳台侨外

    private String GATQ;
    //籍贯

    private String JG;
    //户籍类别

    private String HJLB;
    //户口性质

    private String HKXZ;
    //非农业户口类型

    private String FNYHKLX;
    //户口省份

    private String HK_SHN;
    //户口城市

    private String HK_SHI;
    //户口区

    private String HK_QX;
    //户口街道

    private String HK_JD;
    //街道

    private String OtherHkJd;
    //户口居委会

    private String HK_JWH;
    //居委会

    private String OtherHkJwh;
    //详细地址

    private String HK_DZ;
    //现地址省份

    private String JZ_SHN;
    //现地址城市

    private String JZ_SHI;
    //现地址区

    private String JZ_QX;
    //现地址街道

    private String JZ_JD;
    //街道

    private String OtherJzJd;
    //现地址居委会

    private String JZ_JWH;
    //居委会

    private String OtherJzJwh;
    //现地址

    private String JZ_DZ;
    //居住地邮编

    private String JZ_YB;
    //健康状况

    private String JKZK;
    //残疾幼儿

    private String CJYE;
    //残疾幼儿类别

    private String CJLB;
    //血型

    private String XX;
    //幼儿特长

    private String TC;
    //特异体质

    private String TZ;
    //Todo 未给出字段意思

    private String JTLX;
    //军烈子女

    private String JLSZN;
    //优抚子女

    private String YFZN;
    //部队子女

    private String BDZN;
    //Todo 未给出字段意思

    private String NMGZN;
    //独生子女

    private String DSZN;
    //留守儿童

    private String LSET;
    //进城务工人员随迁子女

    private String SQZN;
    //寄宿生

    private String JSS;
    //孤儿

    private String GE;
    //低保

    private String DB;
    //Todo 未给出字段意思

    private String JSZZ;
    //监护人关系

    private String guarderRelation;
    //监护人姓名

    private String guarderName;
    //监护人身份证件类型

    private String guarderIdType;
    //监护人身份证件号码

    private String guarderIdNo;
    //监护人电话

    private String guarderPhone;
    //毕业年份

    private String graduatedYear;
    //人户一致

    private String RHYZ;
    //删除原因

    private String delReason;
    //监护人手机

    private String guarderMobilePhone;
    //Todo 未给出字段意思

    private String evidence;
    //是否错误身份证号

    private String idNoError;
    //无证件

    private String noIdNo;
    //监护人白名单

    private String guarderStatus;
    //白名单

    private String whiteStatus;
    //户口地址详细

    private String hkAddressDetail;
    //现住址详细地址

    private String jzAddressDetail;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
