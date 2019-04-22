package com.datahome.dao;

import com.datahome.bean.GdnTeacherMgmtBean;
import com.datahome.entity.GdnTeacherEntity;

import java.util.List;

public interface GdnTeacherDao {
    List<GdnTeacherEntity> findby_name_district_Level(GdnTeacherMgmtBean gdnTeacherMgmtBean);
}
