package com.datahome.dao;

import com.datahome.bean.GdnTeacherWageMgmtBean;
import com.datahome.entity.GdnTeacherWageEntity;

import java.util.List;

public interface GdnTeacherWageDao {
    List<GdnTeacherWageEntity> findby_name_district_Level(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);
}
