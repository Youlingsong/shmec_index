package com.datahome.dao;

import com.datahome.bean.GdnParentMgmtBean;
import com.datahome.entity.GdnParentEntity;

import java.util.List;

public interface GdnParentDao {
    List<GdnParentEntity> findby_name_district_Level(GdnParentMgmtBean gdnParentMgmtBean);
}
