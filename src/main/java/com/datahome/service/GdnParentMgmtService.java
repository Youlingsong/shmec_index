package com.datahome.service;

import com.datahome.bean.GdnChildParentMgmtBean;
import com.datahome.bean.GdnParentMgmtBean;

public interface GdnParentMgmtService {
    String save(GdnParentMgmtBean gdnParentMgmtBean);

    String update(GdnParentMgmtBean gdnParentMgmtBean);

    String find(GdnParentMgmtBean gdnParentMgmtBean);

    String finds(GdnParentMgmtBean gdnParentMgmtBean);

    String add(GdnChildParentMgmtBean gdnChildParentMgmtBean);

    String serach(GdnChildParentMgmtBean gdnChildParentMgmtBean);
}
