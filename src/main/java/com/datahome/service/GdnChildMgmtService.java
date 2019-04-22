package com.datahome.service;

import com.datahome.bean.GdnChildMgmtBean;

public interface GdnChildMgmtService {
    String save(GdnChildMgmtBean gdnChildMgmtBean);

    String update(GdnChildMgmtBean gdnChildMgmtBean);

    String find(GdnChildMgmtBean gdnChildMgmtBean);

    String finds(GdnChildMgmtBean gdnChildMgmtBean);
}
