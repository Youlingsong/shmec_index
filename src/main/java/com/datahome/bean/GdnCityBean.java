package com.datahome.bean;


import com.datahome.group.GdnCityGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class GdnCityBean {

    private Integer id;

    @NotNull(groups = {GdnCityGroup.save.class})
    private String levelCode;

    @NotNull(groups = {GdnCityGroup.save.class})
    private String cityStatus;

    @NotNull(groups = {GdnCityGroup.save.class})
    private String cityName;

    @NotNull(groups = {GdnCityGroup.save.class})
    private String cityCode;

    @NotNull(groups = {GdnCityGroup.save.class})
    private String areasqlRule;
}
