package com.datahome.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2019/3/5 19:13
 */

@Setter
@Getter
public class ExportTeacherBean {

    @Excel(name = "登记号码")
    private String id;

    //1正常 2已删除
    @Excel(name = "数据状态")
    private String dataStatus;

    @Excel(name = "最后修改时间")
    private String submitTime;

    // 幼儿 姓名
    @Excel(name = "姓名")
    @NotBlank(message = "幼儿姓名不能为空")
    private String name;

    //证件类型 （1居民身份证 2港澳居民居住证 3港澳居民来往内地通行证 4台湾居民居住证 5台湾居民来往大陆通行证 6护照 7军官证 8士官证 9其他 10 其他-无证件）
    @Excel(name = "证件类型")
    private String cardType;

    //证件号码
    @Excel(name = "证件号码")
    private String cardNumber;

    //出生日期
    @Excel(name = "出生日期")
    private String birthday;

    //性别（1 男  2 女）
    @Excel(name = "性别")
    private String gender;

    //户籍地址(省/市)
    @Excel(name = "户籍地址(省/市)")
    private String accountProvince;

    //户籍地址(市)
    @Excel(name = "户籍地址(市)")
    private String accountCity;

    //户籍地址(区/县)
    @Excel(name = "户籍地址(区/县)")
    private String accountDistrict;

    //户籍地址(街道/镇)
    @Excel(name = "户籍地址(街道/镇)")
    private String accountStreet;

    //户籍地址(居委会/村)
    @Excel(name = "户籍地址(居委会/村)")
    private String accountCommittee;

    //户籍地址(详细地址)
    @Excel(name = "户籍详细地址")
    private String accountAddress;

    //公安户籍地址(省/市)
    @Excel(name = "公安户籍地址(省/市)")
    private String pdAccountProvince;

    //公安户籍地址(市)
    @Excel(name = "公安户籍地址(市)")
    private String pdAccountCity;

    //公安户籍地址(区/县)
    @Excel(name = "公安户籍地址(区/县)")
    private String pdAccountDistrict;

    //公安户籍地址(街道/镇)
    @Excel(name = "公安户籍地址(街道/镇)")
    private String pdAccountStreet;

    //公安户籍地址(居委会/村)
    @Excel(name = "公安户籍地址(居委会/村)")
    private String pdAccountCommittee;

    //公安户籍地址(详细地址)
    @Excel(name = "公安户籍详细地址")
    private String pdAccountAddress;

    //1上海市居住证 2上海市居住登记凭证
    @Excel(name = "持有证件")
    private String permitExist;

    //是否人户一致  1 是  2 否
    @Excel(name = "现居地址是否与户籍地址相同")
    private String addressIsSame;

    //现居地址(省/市)
    @Excel(name = "现居地址(省/市)")
    private String residenceProvince;

    //现居地址(市)
    @Excel(name = "现居地址(市)")
    private String residenceCity;

    //现居地址(区/县)
    @Excel(name = "现居地址(区/县)")
    private String residenceDistrict;

    //现居地址(街道/镇)
    @Excel(name = "现居地址(街道/镇)")
    private String residenceStreet;

    //现居地址(居委会/村)
    @Excel(name = "现居地址(居委会/村)")
    private String residenceCommittee;

    //现居地址(详细地址)
    @Excel(name = "现居详细地址")
    private String residenceAddress;

    //公安现居地址(省/市)
    @Excel(name = "公安现居地址(省/市)")
    private String pdResidenceProvince;

    //公安现居地址(市)
    @Excel(name = "公安现居地址(市)")
    private String pdResidenceCity;

    //公安现居地址(区/县)
    @Excel(name = "公安现居地址(区/县)")
    private String pdResidenceDistrict;

    //公安现居地址(街道/镇)
    @Excel(name = "公安现居地址(街道/镇)")
    private String pdResidenceStreet;

    //公安现居地址(居委会/村)
    @Excel(name = "公安现居地址(居委会/村)")
    private String pdResidenceCommittee;

    //公安现居地址(详细地址)
    @Excel(name = "公安现居详细地址")
    private String pdResidenceAddress;

    // 监护人1姓名
    @Excel(name = "监护人1姓名")
    private String guardianName1;

    // 监护人1手机号
    @Excel(name = "监护人1手机号码")
    private String guardianMobilephone1;

    // 监护人1证件类型 （1居民身份证 2港澳居民居住证 3港澳居民来往内地通行证 4台湾居民居住证 5台湾居民来往大陆通行证 6护照 7军官证 8士官证）
    @Excel(name = "监护人1证件类型")
    private String guardianCardType1;

    // 监护人1证件号码
    @Excel(name = "监护人1证件号码")
    private String guardianCardNumber1;

    // 监护人1与幼儿关系(1父亲 2母亲 3其他法定监护人)
    @Excel(name = "监护人1与幼儿关系")
    private String guardianRelationChild1;

    // 监护人1户籍地址(省/市)
    @Excel(name = "监护人1户籍地址(省/市)")
    private String guardianAccountProvince1;

    // 监护人1户籍地址(市)
    @Excel(name = "监护人1户籍地址(市)")
    private String guardianAccountCity1;

    // 监护人1户籍地址(区/县)
    @Excel(name = "监护人1户籍地址(区/县)")
    private String guardianAccountDistrict1;

    // 监护人1户籍地址(街道/镇)
    @Excel(name = "监护人1户籍地址(街道/镇)")
    private String guardianAccountStreet1;

    // 监护人1户籍地址(居委会/村)
    @Excel(name = "监护人1户籍地址(居委会/村)")
    private String guardianAccountCommittee1;

    // 监护人1户籍地址(详细地址)
    @Excel(name = "监护人1户籍详细地址")
    private String guardianAccountAddress1;

    // 监护人1 1上海市居住证 2上海市居住登记凭证
    @Excel(name = "监护人1持有证件")
    private String guardianPermitExist1;

    // 监护人1 上海市居住证是否积分达到标准分值(1 是 2 否)
    @Excel(name = "监护人1居住证积分是否达到标准分值")
    private String guardianPermitScoreStatus1;

    // 公安监护人1  户籍地址(省/市)
    @Excel(name = "公安监护人1户籍地址(省/市)")
    private String pdGuardianAccountProvince1;

    // 公安监护人1  户籍地址(市)
    @Excel(name = "公安监护人1户籍地址(市)")
    private String pdGuardianAccountCity1;

    // 公安监护人1  户籍地址(区/县)
    @Excel(name = "公安监护人1户籍地址(区/县)")
    private String pdGuardianAccountDistrict1;

    // 公安监护人1  户籍地址(街道/镇)
    @Excel(name = "公安监护人1户籍地址(街道/镇)")
    private String pdGuardianAccountStreet1;

    // 公安监护人1  户籍地址(居委会/村)
    @Excel(name = "公安监护人1户籍地址(居委会/村)")
    private String pdGuardianAccountCommittee1;

    // 公安监护人1  户籍地址(详细地址)
    @Excel(name = "公安监护人1户籍详细地址")
    private String pdGuardianAccountAddress1;

    // 监护人2姓名
    @Excel(name = "监护人2姓名")
    private String guardianName2;

    // 监护人2手机号
    @Excel(name = "监护人2手机号码")
    private String guardianMobilephone2;

    // 监护人2证件类型 （1居民身份证 2港澳居民居住证 3港澳居民来往内地通行证 4台湾居民居住证 5台湾居民来往大陆通行证 6护照 7军官证 8士官证）
    @Excel(name = "监护人2证件类型")
    private String guardianCardType2;

    // 监护人2证件号码
    @Excel(name = "监护人2证件号码")
    private String guardianCardNumber2;

    // 监护人2与幼儿关系(1父亲 2母亲 3其他法定监护人)
    @Excel(name = "监护人2与幼儿关系")
    private String guardianRelationChild2;

    // 监护人2户籍地址(省/市)
    @Excel(name = "监护人2户籍地址(省/市)")
    private String guardianAccountProvince2;

    // 监护人2户籍地址(市)
    @Excel(name = "监护人2户籍地址(市)")
    private String guardianAccountCity2;

    // 监护人2户籍地址(区/县)
    @Excel(name = "监护人2户籍地址(区/县)")
    private String guardianAccountDistrict2;

    // 监护人2户籍地址(街道/镇)
    @Excel(name = "监护人2户籍地址(街道/镇)")
    private String guardianAccountStreet2;

    // 监护人2户籍地址(居委会/村)
    @Excel(name = "监护人2户籍地址(居委会/村)")
    private String guardianAccountCommittee2;

    // 监护人2 户籍地址(详细地址)
    @Excel(name = "监护人2户籍详细地址")
    private String guardianAccountAddress2;

    // 监护人2 1上海市居住证 2上海市居住登记凭证
    @Excel(name = "监护人2持有证件")
    private String guardianPermitExist2;

    // 监护人2 上海市居住证是否积分达到标准分值(1 是 2 否)
    @Excel(name = "监护人2居住证积分是否达到标准分值")
    private String guardianPermitScoreStatus2;

    // 公安监护人2  户籍地址(省/市)
    @Excel(name = "公安监护人2户籍地址(省/市)")
    private String pdGuardianAccountProvince2;

    // 公安 监护人2  户籍地址(市)
    @Excel(name = "公安监护人2户籍地址(市)")
    private String pdGuardianAccountCity2;

    // 公安 监护人2  户籍地址(区/县)
    @Excel(name = "公安监护人2户籍地址(区/县)")
    private String pdGuardianAccountDistrict2;

    // 公安 监护人2  户籍地址(街道/镇)
    @Excel(name = "公安监护人2户籍地址(街道/镇)")
    private String pdGuardianAccountStreet2;

    // 公安 监护人2  户籍地址(居委会/村)
    @Excel(name = "公安监护人2户籍地址(居委会/村)")
    private String pdGuardianAccountCommittee2;

    // 公安 监护人2 户籍地址(详细地址)
    @Excel(name = "公安监护人2户籍详细地址")
    private String pdGuardianAccountAddress2;
}
