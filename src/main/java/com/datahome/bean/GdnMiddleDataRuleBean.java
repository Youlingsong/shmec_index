package com.datahome.bean;

import com.datahome.group.GdnMiddleDataRuleGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GdnMiddleDataRuleBean {
    @NotNull(groups = {GdnMiddleDataRuleGroup.delete.class, GdnMiddleDataRuleGroup.find.class, GdnMiddleDataRuleGroup.update.class})
    private String id;

    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    private String sqlsentence;

    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    private String name;

    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    private String status;
    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    //1查看结果,2保存中间数据计算规则
    private Integer saveOrlook;

    //页面传过来的sql对应计算的citycode
    @NotNull(groups = {GdnMiddleDataRuleGroup.save.class})
    private List<String> cityCodes;
}
