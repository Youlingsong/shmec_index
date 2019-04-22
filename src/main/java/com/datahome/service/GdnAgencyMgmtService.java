package com.datahome.service;

import com.datahome.bean.GdnAgencyMgmtBean;

public interface GdnAgencyMgmtService {
    String save(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String update(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String find(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String finds(GdnAgencyMgmtBean gdnAgencyMgmtBean);
}
