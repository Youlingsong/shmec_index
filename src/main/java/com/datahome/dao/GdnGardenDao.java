package com.datahome.dao;

import com.datahome.bean.GdnGardenMgmtBean;
import com.datahome.entity.GdnGardenEntity;

import java.util.List;

public interface GdnGardenDao {
    List<GdnGardenEntity> findby_name_district_Level(GdnGardenMgmtBean gdnGardenMgmtBean);
}
