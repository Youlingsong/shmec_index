package com.datahome.bean;

import com.datahome.group.GdnTeacherTrainMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Getter
@Setter
public class GdnTeacherTrainMgmtBean {
    @NotNull(groups = {GdnTeacherTrainMgmtGroup.delete.class, GdnTeacherTrainMgmtGroup.find.class, GdnTeacherTrainMgmtGroup.update.class})
    private String id;
    //教师id,并没有做关联
    @NotNull(groups = {GdnTeacherTrainMgmtGroup.save.class,GdnTeacherTrainMgmtGroup.delete.class,  GdnTeacherTrainMgmtGroup.update.class})
    private String gdnTeacherId;

    //机构Id,并没有做关联
    @NotNull(groups = {GdnTeacherTrainMgmtGroup.save.class,GdnTeacherTrainMgmtGroup.delete.class,  GdnTeacherTrainMgmtGroup.update.class})
    private String gdnAgencyId;

    //教职工参与培训编号

    private String code;
    //机构编号

    private String agencycode;
    //姓名

    private String name;
    //项目名称

    private String projectname;
    //级别

    private String level;
    //培训时长是否大于一月

    private int trainlength;
    // 天_小时

    private BigDecimal hourday;
    //年度

    private String year;

    //操作者Id

    private String operatorId;
    //操作者

    private String operator;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;

}
