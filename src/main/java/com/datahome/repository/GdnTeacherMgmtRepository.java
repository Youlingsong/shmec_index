package com.datahome.repository;

import com.datahome.dao.BaseDao;
import com.datahome.dao.GdnTeacherDao;
import com.datahome.entity.GdnTeacherEntity;

public interface GdnTeacherMgmtRepository  extends BaseDao<GdnTeacherEntity,String>, GdnTeacherDao {
}
