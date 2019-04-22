package com.datahome.bean;

import com.datahome.group.GdnChildParentMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GdnChildParentMgmtBean {
  
    private String id;

    @NotNull(groups = {GdnChildParentMgmtGroup.delete.class, GdnChildParentMgmtGroup.find.class, GdnChildParentMgmtGroup.update.class,GdnChildParentMgmtGroup.save.class})
    private String gdnchildid;

    @NotNull(groups = {GdnChildParentMgmtGroup.delete.class, GdnChildParentMgmtGroup.find.class, GdnChildParentMgmtGroup.update.class,GdnChildParentMgmtGroup.save.class})
    private String gdnparentid;
    //关系，词汇表

    private String relation;
    //排序数

    private Integer sort;
    //备注

    private String remarks;

    //状态，词汇表
    private String status;
    //是否监护人，词汇表
    private String JHR;


}