package com.datahome.service;

import com.datahome.bean.FileBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/19 10:08
 */
public interface EditorFileService {
    String upload(MultipartFile multipartFile, FileBean fileBean);

    String management(FileBean fileBean);
}
