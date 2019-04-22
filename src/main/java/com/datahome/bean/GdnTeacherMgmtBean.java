package com.datahome.bean;

import com.datahome.group.GdnTeacherMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GdnTeacherMgmtBean {
    @NotNull(groups = {GdnTeacherMgmtGroup.delete.class, GdnTeacherMgmtGroup.find.class, GdnTeacherMgmtGroup.update.class})
    private String id;
    @NotNull(groups = {GdnTeacherMgmtGroup.update.class,GdnTeacherMgmtGroup.save.class})
    private String gdnGardenid;
    //名称
    private String name;
    //用户名，账户名
    private String userName;
    //性别，词汇表
    private String gender;
    //出生日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    //教职工编号
    private String code;
    //头像（图片路径）

    private String portrait;
    //证件类型，词汇表

    private String IDType;
    //证件号

    private String IDNo;
    //联系电话

    private String phone;
    //电子邮件

    private String email;
    //主页地址

    private String homepage;
    //备注

    private String remarks;
    //状态，词汇表

    private String status;

    //曾用名

    private String CYM;
    //国籍，词汇表

    private String GJ;
    //民族，词汇表

    private String MZ;
    //健康状况，词汇表

    private String JKZK;
    //血型，词汇表

    private String XX;
    //宗教信仰，词汇表

    private String ZJXY;
    //港澳台侨外，词汇表

    private String GATQW;
    //婚姻状况，词汇表

    private String HYZK;
    //籍贯，词汇表

    private String JG;
    //出生地，词汇表

    private String CSD;
    //家庭出生，词汇表

    private String JTCS;
    //本人成分，词汇表

    private String BRCF;
    //政治面貌，词汇表

    private String ZZMM;
    //户口性质，词汇表

    private String HKXZ;
    //文化程度（最高学历），词汇表

    private String WHCD;
    //毕业院校名称

    private String BY_YXMC;
    //毕业情况，词汇表

    private String BY_QK;
    //所学专业

    private String BY_SXZY;
    //所获学位，词汇表

    private String BY_SHXW;
    //毕业日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date BY_RQ;
    //工作日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date GZRQ;
    //来园日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date LYRQ;
    //从教日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date CJRQ;
    //编制情况，词汇表

    private String BZQK;
    //专业技术职务，词汇表

    private String ZYJSZW;
    //职务，词汇表

    private String ZW;
    //职称，词汇表

    private String ZC;
    //党派，词汇表

    private String DP;
    //户口所在省，词汇表

    private String HK_SHN;
    //户口所在市，词汇表

    private String HK_SHI;
    //户口所在区（县），词汇表

    private String HK_QX;
    //户口所在街道，词汇表

    private String HK_JD;
    //其他户口街道

    private String OtherHkJd;
    //户口所在居委会，词汇表

    private String HK_JWH;
    //其他户口居委会

    private String OtherHkJwh;
    //户口地址

    private String HK_DZ;
    //居住所在省，词汇表

    private String JZ_SHN;
    //居住所在市，词汇表

    private String JZ_SHI;
    //居住所在区（县），词汇表

    private String JZ_QX;
    //居住所在街道，词汇表

    private String JZ_JD;
    //其他居住街道

    private String OtherJzJd;
    //居住所在居委会，词汇表

    private String JZ_JWH;
    //其他居住居委会

    private String OtherJzJwh;
    //居住地址

    private String JZ_DZ;
    //居住地邮编

    private String JZ_YB;
    //认可状况，词汇表

    private String RKZK;
    //档案编号

    private String DABH;
    //档案文本

    private String DAWB;
    //特长

    private String TC;
    //教师称号，词汇表

    private String JSCH;
    //是否师范类专业，词汇表

    private String SFZY;
    //是否学前教育专业毕业，词汇表

    private String XQZY;
    //上年度考核情况，词汇表

    private String SNDKH;
    //绩效工资

    private BigDecimal SR_JXGZ;
    //基本工资

    private BigDecimal SR_JBGZ;
    //其他津贴补贴

    private BigDecimal SR_QTBT;
    //上年平均收入

    private BigDecimal SR_SNPJ;
    //参加社保类型，词汇表

    private String SHB_LZ;
    //有无基本养老保险，词汇表

    private String SHB_JBYL;
    //有无医疗保险，词汇表

    private String SHB_YLBX;
    //有无住房公积金，词汇表

    private String SHB_GJJ;
    //有无工伤保险，词汇表

    private String SHB_GSBX;
    //有无失业保险，词汇表

    private String SHB_SYEBX;
    //有无生育保险，词汇表

    private String SHB_SYUBX;
    //是否有教师资格证，词汇表

    private String JSZGZ;
    //教师资格证发证机关

    private String JSZGZ_FZJG;
    //教师资格证种类，词汇表

    private String JSZGZ_ZL;
    //教师资格证号码

    private String JSZGZ_BH;
    //非农业户口类型

    private String FNYHKLX;
    //白名单

    private String whiteStatus;
    //户籍地详细地址

    private String hkAddressDetail;
    //现住址详细地址

    private String jzAddressDetail;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
