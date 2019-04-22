package com.datahome.bean;

import com.datahome.group.GdnMiddleDataGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GdnMiddleDataBean {
    @NotNull(groups = {GdnMiddleDataGroup.delete.class, GdnMiddleDataGroup.find.class, GdnMiddleDataGroup.update.class})
    private String id;

    private String key;


    private Integer value;


    //备注
    private String remake;


}
