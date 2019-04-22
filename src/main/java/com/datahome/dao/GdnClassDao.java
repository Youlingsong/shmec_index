package com.datahome.dao;

import com.datahome.bean.GdnClassMgmtBean;
import com.datahome.entity.GdnClassEntity;

import java.util.List;

public interface GdnClassDao {
    List<GdnClassEntity> findby_name_district_Level(GdnClassMgmtBean gdnClassMgmtBean);
}
