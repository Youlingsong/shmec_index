package com.datahome.dao;

import com.datahome.bean.GdnChildMgmtBean;
import com.datahome.entity.GdnChildEntity;

import java.util.List;

public interface GdnChildDao {
    List<GdnChildEntity> findby_name_district_Level(GdnChildMgmtBean gdnChildMgmtBean);
}
