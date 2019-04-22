package com.datahome.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author xl
 * @Description: 城市 管理
 * @Date: Create in 2018/5/4 17:00
 */
@Setter
@Getter
public class CityBean {

    private Integer id;

    private Integer parentId;

    private String cityName;

    private String cityStatus;

    private String levelCode;

}
