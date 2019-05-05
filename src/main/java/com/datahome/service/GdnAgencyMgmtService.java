package com.datahome.service;

import com.datahome.bean.GdnAgencyMgmtBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface GdnAgencyMgmtService {
    String save(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String update(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String find(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String finds(GdnAgencyMgmtBean gdnAgencyMgmtBean);

    String importExcel(MultipartFile file, HttpServletResponse response);
}
