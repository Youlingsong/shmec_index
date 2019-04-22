package com.datahome.service;

import com.datahome.bean.GdnGardenMgmtBean;

public interface GdnGardenMgmtService {
    String save(GdnGardenMgmtBean gdnGardenMgmtBean);

    String update(GdnGardenMgmtBean gdnGardenMgmtBean);

    String find(GdnGardenMgmtBean gdnGardenMgmtBean);

    String finds(GdnGardenMgmtBean gdnGardenMgmtBean);
}
