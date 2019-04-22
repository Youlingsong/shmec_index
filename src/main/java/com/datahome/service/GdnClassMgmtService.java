package com.datahome.service;

import com.datahome.bean.GdnClassMgmtBean;

public interface GdnClassMgmtService {
    String save(GdnClassMgmtBean gdnClassMgmtBean);

    String update(GdnClassMgmtBean gdnClassMgmtBean);

    String find(GdnClassMgmtBean gdnClassMgmtBean);

    String finds(GdnClassMgmtBean gdnClassMgmtBean);
}
