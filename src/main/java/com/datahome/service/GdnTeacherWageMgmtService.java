package com.datahome.service;

import com.datahome.bean.GdnTeacherWageMgmtBean;

public interface GdnTeacherWageMgmtService {
    String save(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);

    String update(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);

    String find(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);

    String finds(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);

    String delete(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);

    String deletes(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean);
}
