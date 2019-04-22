package com.datahome.bean;

import com.datahome.group.GdnMiddleDataRuleGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GdnMiddleDataRuleBean {
    @NotNull(groups = {GdnMiddleDataRuleGroup.delete.class, GdnMiddleDataRuleGroup.find.class, GdnMiddleDataRuleGroup.update.class})
    private String id;

    private String key;

    private String sqlsentence;

    private String name;

    private String remarks;


    private String status;
    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    //1查看结果,2保存中间数据计算规则
    private Integer saveOrlook;
}
