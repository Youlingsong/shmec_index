package com.datahome.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.datahome.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2019/3/5 19:13
 */

@Setter
@Getter
public class ImportAgenceBean implements IExcelModel {

    // 幼儿 姓名
    @Excel(name = "机构名称")
    @NotBlank(message = "机构名称不能为空")
    @Length(max = 200, message = "机构名称超过长度限制")
    @Pattern(regexp = CommonUtil.REGEX_NAME, message = "机构名称含有非法字符")
    private String name;


    //出生日期
    @Excel(name = "出生日期")
    @NotNull(message = "教师出生日期不能为空")
    private Date birthday;

    //性别（1 男  2 女）
    @Excel(name = "性别")
    @Pattern(regexp = "^(男|女)$", message = "教师性别无效")
    @NotNull(message = "教师性别不能为空")
    private String gender;


    //出生日期
    @Excel(name = "教师所属园")
    private String gdnGardenname;

    @Excel(name = "错误提示")
    private String errorMsg;
}
