package com.datahome.service;

import com.datahome.bean.GdnTeacherTrainMgmtBean;

public interface GdnTeacherTrainMgmtService {
    String save(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean);

    String update(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean);

    String find(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean);

    String finds(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean);
}
