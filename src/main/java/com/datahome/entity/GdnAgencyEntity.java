package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_gdn_agency")
public class GdnAgencyEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false, columnDefinition = "char(36)")
    private String id;
    //区
    @Column(length = 50)
    private String district;
    //街道
    @Column(length = 50)
    private String street;
    //编号
    @Column(length = 50)
    private String code;
    //名称
    @Column(length = 100)
    private String name;
    //英文名称
    @Column(length = 200)
    private String englishName;
    //简称
    @Column(length = 500)
    private String simpleName;
    //机构（园所）成立年月
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    //机构负责人（园长）姓名
    @Column(length = 50)
    private String kinderGardenName;
    //机构负责人（园长）手机号码
    @Column(length = 50)
    private String kinderGardenTelephone;
    //机构负责人（园长）固定电话
    @Column(length = 50)
    private String kinderGardenPhone;
    //保健员姓名
    @Column(length = 50)
    private String healthWorkersName;
    //保健员电话
    @Column(length = 50)
    private String healthWorkersPhone;
    //传真电话
    @Column(length = 50)
    private String faxTelephone;
    //办公电话
    @Column(length = 50)
    private String officeTelephone;
    //级别
    @Column(length = 50)
    private String level;
    //是否城镇小区配套幼儿园
    @Column(length = 50)
    private String czxqpt;
    //邮政编码
    @Column(length = 50)
    private String postcode;
    //地址
    @Column(length = 200)
    private String address;
    //机构驻地城乡类别
    @Column(length = 50)
    private String townAndCountryCategory;
    //机构类别
    @Column(length = 50)
    private String category;
    //机构开办形式
    @Column(length = 50)
    private String openForm;
    //公办园核定编制总数
    @Column(columnDefinition = "smallint")
    private Integer organizationNum;
    //是否是附属园
    @Column(length = 50)
    private String subsidiaryGarden;
    //附属于学校类型
    @Column(length = 50)
    private String subsidiaryGardenType;
    //附属于学校名称
    @Column(length = 100)
    private String subsidiaryGardenName;
    //组织机构代码
    @Column(length = 50)
    private String organizationCode;
    //办别
    @Column(length = 50)
    private String diseretion;
    //项目幼儿园类别
    @Column(length = 50)
    private String gardenCategory;
    //是否营利性机构
    @Column(length = 50)
    private String profitOrganization;
    //是否乡镇中心学前教育机构
    @Column(length = 50)
    private String preschoolEducationOrganization;
    //是否普惠性幼儿园
    @Column(length = 50)
    private String phxGarden;
    //资助工作机构名称
    @Column(length = 50)
    private String subsidizeOrganizationName;
    //资助工作机构负责人
    @Column(length = 50)
    private String subsidizeOrganizationPrincipal;
    //资助工作专职人员
    @Column(length = 50)
    private String subsidizeFulltimeStaff;
    //资助信息填报人
    @Column(length = 50)
    private String subsidizeInformant;
    //资助工作联系电话
    @Column(length = 100)
    private String subsidizePhone;
    //资助工作传真
    @Column(length = 100)
    private String subsidizeFax;
    //资助工作电子信箱
    @Column(length = 400)
    private String subsidizeEmail;
    //机构开办许可证颁证日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date sgencyExtendLicenseDate;
    //机构开办许可证号
    @Column(length = 50)
    private String agencyExtendLicenseKey;
    //机构开办许可证审批机关
    @Column(length = 50)
    private String agencyExtendLicenseApprovalDepartment;
    //机构登记注册号
    @Column(length = 50)
    private String agencyExtendRegistrationNumber;
    //法人登记证号（事业单位/非企业）
    @Column(length = 50)
    private String gencyExtendLegalPersonNo;
    //单位电子邮箱
    @Column(length = 50)
    private String agencyExtendDepartmentEmail;
    //上网方式
    @Column(length = 50)
    private String agencyExtendInternetAccessMethod;
    //网站主页
    @Column(length = 400)
    private String agencyExtendHomePageUrl;
    //经度
    @Column(precision = 9, scale = 2)
    private BigDecimal agencyExtendLongitude;
    //纬度
    @Column(precision = 9, scale = 2)
    private BigDecimal agencyExtendLatitude;
    //海拔高度
    @Column(precision = 9, scale = 2)
    private BigDecimal agencyExtendAltitude;
    //主教学语言
    @Column(length = 50)
    private String agencyExtendFirstEducationLanguage;
    //辅教学语言
    @Column(length = 50)
    private String agencyExtendSecondEducationLanguage;
    //附设班类型
    @Column(length = 50)
    private String agencyExtendSubordinateClassType;
    //主管部门
    @Column(length = 200)
    private String agencyExtendDirectorDepartment;
    //所在区类别
    @Column(length = 50)
    private String agencyExtendDistrictCategory;
    //所在地经济属性
    @Column(length = 50)
    private String agencyExtendAreaEconomics;
    //所在地民族属性
    @Column(length = 50)
    private String agencyExtendAreaNation;
    //特色
    @Column(length = 2000)
    private String agencyExtendCharacteristic;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //状态
    @Column(length = 50)
    private String status;
    //第三方应用开头
    @Column(length = 500)
    private String openThirdAccountType;
    //Sep对应Id
    @Column(length = 16)
    private String sepId;
}
