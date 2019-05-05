package com.datahome.dao;

import com.datahome.bean.GdnMiddleDataBean;
import com.datahome.entity.GdnMiddleDataEntity;

import java.util.List;

public interface GdnMiddleDataDao {


    List<GdnMiddleDataEntity>  finds(GdnMiddleDataBean gdnMiddleDataBean);

    void deleteby_ruleid(String ruleid);

}
