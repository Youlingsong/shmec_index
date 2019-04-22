package com.datahome.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_child")
public class GdnChildEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //班级id
    @Column(length = 36)
    private String gdnClassid;
    //年级
    @Column(length = 20)
    private String grade;
    //园所ID
    @Column(length = 36)
    private String gdnGardenid;
    //幼儿姓名
    @Column(length = 100)
    private String name;
    //姓名拼音
    @Column(length = 150)
    private String XMPY;
    //性别
    @Column(length = 20)
    private String gender;
    //出生日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    //学号
    @Column(length = 100)
    private String code;
    //头像路径
    @Column(length = 1000)
    private String portrait;
    //证件类型
    @Column(length = 50)
    private String IDType;
    //证件号码
    @Column(length = 50)
    private String IDNo;
    //备注
    @Column(length = 2000)
    private String remarks;
    //幼儿状态
    @Column(length = 20)
    private String status;
    //幼儿创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //幼儿更新日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //曾用名/英文名
    @Column(length = 50)
    private String CYM;
    //出生地
    @Column(length = 50)
    private String CSD;
    //入园日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date RYRQ;
    //就读方式
    @Column(length = 20)
    private String JDFS;
    //国籍
    @Column(length = 20)
    private String GJ;
    //民族
    @Column(length = 50)
    private String MZ;
    //家庭出生，词汇表
    @Column(length = 20)
    private String JTCS;
    //港澳台侨外
    @Column(length = 50)
    private String GATQ;
    //籍贯
    @Column(length = 20)
    private String JG;
    //户籍类别
    @Column(length = 20)
    private String HJLB;
    //户口性质
    @Column(length = 20)
    private String HKXZ;
    //非农业户口类型
    @Column(length = 20)
    private String FNYHKLX;
    //户口省份
    @Column(length = 20)
    private String HK_SHN;
    //户口城市
    @Column(length = 20)
    private String HK_SHI;
    //户口区
    @Column(length = 20)
    private String HK_QX;
    //户口街道
    @Column(length = 200)
    private String HK_JD;
    //街道
    @Column(length = 400)
    private String OtherHkJd;
    //户口居委会
    @Column(length = 200)
    private String HK_JWH;
    //居委会
    @Column(length = 400)
    private String OtherHkJwh;
    //详细地址
    @Column(length = 400)
    private String HK_DZ;
    //现地址省份
    @Column(length = 20)
    private String JZ_SHN;
    //现地址城市
    @Column(length = 20)
    private String JZ_SHI;
    //现地址区
    @Column(length = 20)
    private String JZ_QX;
    //现地址街道
    @Column(length = 200)
    private String JZ_JD;
    //街道
    @Column(length = 400)
    private String OtherJzJd;
    //现地址居委会
    @Column(length = 200)
    private String JZ_JWH;
    //居委会
    @Column(length = 400)
    private String OtherJzJwh;
    //现地址
    @Column(length = 400)
    private String JZ_DZ;
    //居住地邮编
    @Column(length = 50)
    private String JZ_YB;
    //健康状况
    @Column(length = 20)
    private String JKZK;
    //残疾幼儿
    @Column(length = 20)
    private String CJYE;
    //残疾幼儿类别
    @Column(length = 20)
    private String CJLB;
    //血型
    @Column(length = 20)
    private String XX;
    //幼儿特长
    @Column(length = 2000)
    private String TC;
    //特异体质
    @Column(length = 2000)
    private String TZ;
    //Todo 未给出字段意思
    @Column(length = 20)
    private String JTLX;
    //军烈子女
    @Column(length = 20)
    private String JLSZN;
    //优抚子女
    @Column(length = 20)
    private String YFZN;
    //部队子女
    @Column(length = 20)
    private String BDZN;
    //Todo 未给出字段意思
    @Column(length = 20)
    private String NMGZN;
    //独生子女
    @Column(length = 20)
    private String DSZN;
    //留守儿童
    @Column(length = 20)
    private String LSET;
    //进城务工人员随迁子女
    @Column(length = 20)
    private String SQZN;
    //寄宿生
    @Column(length = 20)
    private String JSS;
    //孤儿
    @Column(length = 20)
    private String GE;
    //低保
    @Column(length = 20)
    private String DB;
    //Todo 未给出字段意思
    @Column(length = 20)
    private String JSZZ;
    //监护人关系
    @Column(length = 50)
    private String guarderRelation;
    //监护人姓名
    @Column(length = 100)
    private String guarderName;
    //监护人身份证件类型
    @Column(length = 50)
    private String guarderIdType;
    //监护人身份证件号码
    @Column(length = 50)
    private String guarderIdNo;
    //监护人电话
    @Column(length = 50)
    private String guarderPhone;
    //毕业年份
    @Column(length = 20)
    private String graduatedYear;
    //人户一致
    @Column(length = 20)
    private String RHYZ;
    //删除原因
    @Column(length = 50)
    private String delReason;
    //监护人手机
    @Column(length = 50)
    private String guarderMobilePhone;
    //Todo 未给出字段意思
    @Column(length = 500)
    private String evidence;
    //是否错误身份证号
    @Column(length = 20)
    private String idNoError;
    //无证件
    @Column(length = 20)
    private String noIdNo;
    //监护人白名单
    @Column(length = 20)
    private String guarderStatus;
    //白名单
    @Column(length = 50)
    private String whiteStatus;
    //户口地址详细
    @Column(length = 500)
    private String hkAddressDetail;
    //现住址详细地址
    @Column(length = 500)
    private String jzAddressDetail;
}
