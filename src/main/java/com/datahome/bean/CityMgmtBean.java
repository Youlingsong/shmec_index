package com.datahome.bean;

import com.datahome.group.CityMgmtGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author xl
 * @Description: 城市 管理
 * @Date: Create in 2018/5/4 17:00
 */
@Setter
@Getter
public class CityMgmtBean {

    @NotNull(groups = {CityMgmtGroup.update.class})
    private Integer id;

    @NotNull(groups = {CityMgmtGroup.save.class, CityMgmtGroup.update.class})
    private Integer parentId;

    @NotNull(groups = {CityMgmtGroup.save.class, CityMgmtGroup.update.class})
    private String cityName;

    @NotNull(groups = {CityMgmtGroup.save.class, CityMgmtGroup.update.class})
    @Pattern(regexp = RegexUtil.COMMON_STATUS_CODE)
    private String cityStatus;

}
