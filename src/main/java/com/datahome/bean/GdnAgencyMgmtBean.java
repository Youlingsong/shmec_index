package com.datahome.bean;

import com.datahome.group.GdnAgencyMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
public class GdnAgencyMgmtBean {
    @NotNull(groups = {GdnAgencyMgmtGroup.delete.class, GdnAgencyMgmtGroup.find.class, GdnAgencyMgmtGroup.update.class})
    private String id;
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
      
    private String kinderGardenPhone;
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
    //机构驻地城乡类别
      
    private String townAndCountryCategory;
    //机构类别
      
    private String category;
    //机构开办形式
      
    private String openForm;
    //公办园核定编制总数
    @Column(columnDefinition = "smallint")
    private Integer organizationNum;
    //是否是附属园
      
    private String subsidiaryGarden;
    //附属于学校类型
      
    private String subsidiaryGardenType;
    //附属于学校名称
      
    private String subsidiaryGardenName;
    //组织机构代码
      
    private String organizationCode;
    //办别
      
    private String diseretion;
    //项目幼儿园类别
      
    private String gardenCategory;
    //是否营利性机构
      
    private String profitOrganization;
    //是否乡镇中心学前教育机构
      
    private String preschoolEducationOrganization;
    //是否普惠性幼儿园
      
    private String phxGarden;
    //资助工作机构名称
      
    private String subsidizeOrganizationName;
    //资助工作机构负责人
      
    private String subsidizeOrganizationPrincipal;
    //资助工作专职人员
      
    private String subsidizeFulltimeStaff;
    //资助信息填报人
      
    private String subsidizeInformant;
    //资助工作联系电话
      
    private String subsidizePhone;
    //资助工作传真
      
    private String subsidizeFax;
    //资助工作电子信箱
      
    private String subsidizeEmail;
    //机构开办许可证颁证日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date sgencyExtendLicenseDate;
    //机构开办许可证号
      
    private String agencyExtendLicenseKey;
    //机构开办许可证审批机关
      
    private String agencyExtendLicenseApprovalDepartment;
    //机构登记注册号
      
    private String agencyExtendRegistrationNumber;
    //法人登记证号（事业单位/非企业）
      
    private String gencyExtendLegalPersonNo;
    //单位电子邮箱
      
    private String agencyExtendDepartmentEmail;
    //上网方式
      
    private String agencyExtendInternetAccessMethod;
    //网站主页
      
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
      
    private String agencyExtendFirstEducationLanguage;
    //辅教学语言
      
    private String agencyExtendSecondEducationLanguage;
    //附设班类型
      
    private String agencyExtendSubordinateClassType;
    //主管部门
      
    private String agencyExtendDirectorDepartment;
    //所在区类别
      
    private String agencyExtendDistrictCategory;
    //所在地经济属性
      
    private String agencyExtendAreaEconomics;
    //所在地民族属性
      
    private String agencyExtendAreaNation;
    //特色
      
    private String agencyExtendCharacteristic;

      
    private String status;
    //第三方应用开头
      
    private String openThirdAccountType;
    //Sep对应Id
      
    private String sepId;


    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
