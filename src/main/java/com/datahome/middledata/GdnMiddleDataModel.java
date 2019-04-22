package com.datahome.middledata;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GdnMiddleDataModel {
    //中间数key
    private String key;
    //中间数值
    private Integer value;
    //中间数备注
    private String remake;


    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
