package com.datahome.bean;


import com.datahome.group.GdnTeacherClassMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class GdnClassTeacherMgmtBean implements Serializable {

    private String id;
    @NotNull(groups = {GdnTeacherClassMgmtGroup.delete.class, GdnTeacherClassMgmtGroup.find.class, GdnTeacherClassMgmtGroup.update.class,GdnTeacherClassMgmtGroup.save.class})
    private String gdnClassid;

    @NotNull(groups = {GdnTeacherClassMgmtGroup.delete.class, GdnTeacherClassMgmtGroup.find.class, GdnTeacherClassMgmtGroup.update.class,GdnTeacherClassMgmtGroup.save.class})
    private String gdnTeacherid;
    //职务，词汇表

    private String duty;
    //排序数

    private Integer sort;
    //备注

    private String remarks;

    //状态(词汇表)

    private String status;
}
