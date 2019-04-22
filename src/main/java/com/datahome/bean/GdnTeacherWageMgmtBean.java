package com.datahome.bean;

import com.datahome.group.GdnTeacherWageMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class GdnTeacherWageMgmtBean {
    @NotNull(groups = {GdnTeacherWageMgmtGroup.delete.class, GdnTeacherWageMgmtGroup.find.class, GdnTeacherWageMgmtGroup.update.class})
    private String id;
    //园所id
    @NotNull(groups = {GdnTeacherWageMgmtGroup.save.class,GdnTeacherWageMgmtGroup.delete.class,  GdnTeacherWageMgmtGroup.update.class})
    private String gdnGardenid;
    //教师id
    @NotNull(groups = {GdnTeacherWageMgmtGroup.save.class,GdnTeacherWageMgmtGroup.delete.class,  GdnTeacherWageMgmtGroup.update.class})
    private String gdnTeacherId;
    //年份
    private Integer year;
    //上年度考核情况
    private String SNDKH;
    //绩效工资(元/月)
    private BigDecimal SR_JXGZ;
    //基本工资(元/月)
    private BigDecimal SR_JBGZ;
    //其他津贴补贴(元/月)
    private BigDecimal SR_QTBT;
    //上年平均收入(元/月)
    private BigDecimal SR_SNPJ;
    //参加社保类型
    private String SHB_LZ;
    //有无基本养老保险
    private String SHB_JBYL;
    //有无医疗保险
    private String SHB_YLBX;
    //有无住房公积金
    private String SHB_GJJ;
    //有无工伤保险
    private String SHB_GSBX;
    //有无失业保险
    private String SHB_SYEBX;
    //有无生育保险
    private String SHB_SYUBX;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
