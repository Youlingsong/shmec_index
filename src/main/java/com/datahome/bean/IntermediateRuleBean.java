package com.datahome.bean;

import com.datahome.group.IntermediateRuleGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IntermediateRuleBean {

    @NotNull(groups = {IntermediateRuleGroup.find.class, IntermediateRuleGroup.update.class, IntermediateRuleGroup.delete.class})
    private Integer id;

    @NotNull(groups = {IntermediateRuleGroup.save.class})
    private String name;

    @NotNull(groups = {IntermediateRuleGroup.save.class})
    private String sqlsentence;

    @NotNull(groups = {IntermediateRuleGroup.save.class})
    //1check查看结果，2直接生成中间数据
    private int isCheckorSave;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
