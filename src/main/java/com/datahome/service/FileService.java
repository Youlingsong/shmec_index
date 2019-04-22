package com.datahome.service;

import com.datahome.bean.FileBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/22 10:14
 */
public interface FileService {

    String fileUpload(MultipartFile mf, FileBean fb) throws IOException;

    String deleteFile(FileBean fb) throws IOException;

    void download(HttpServletRequest request, HttpServletResponse response, FileBean fb) throws Exception;
}
