package com.datahome.dao;

import com.datahome.bean.GdnTeacherTrainMgmtBean;
import com.datahome.entity.GdnTeacherTrainEntity;

import java.util.List;

public interface GdnTeacherTrainDao {
    List<GdnTeacherTrainEntity> findby_name_district_Level(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean);
}
