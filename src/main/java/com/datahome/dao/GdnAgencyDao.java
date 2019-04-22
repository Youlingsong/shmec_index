package com.datahome.dao;

import com.datahome.bean.GdnAgencyMgmtBean;
import com.datahome.entity.GdnAgencyEntity;

import java.util.List;

public interface GdnAgencyDao {
    List<GdnAgencyEntity> findby_name_district_Level(GdnAgencyMgmtBean gdnAgencyMgmtBean);
}
