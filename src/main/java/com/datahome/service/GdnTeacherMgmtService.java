package com.datahome.service;

import com.datahome.bean.GdnClassTeacherMgmtBean;
import com.datahome.bean.GdnTeacherMgmtBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface GdnTeacherMgmtService {
    String save(GdnTeacherMgmtBean gdnTeacherMgmtBean);

    String update(GdnTeacherMgmtBean gdnTeacherMgmtBean);

    String find(GdnTeacherMgmtBean gdnTeacherMgmtBean);

    String finds(GdnTeacherMgmtBean gdnTeacherMgmtBean);

    String add(GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean);

    String serach(GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean);

    String importExcel(MultipartFile file, HttpServletResponse response);
}
