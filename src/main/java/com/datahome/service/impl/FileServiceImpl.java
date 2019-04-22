package com.datahome.service.impl;

import com.datahome.bean.FileBean;
import com.datahome.config.CommonProperties;
import com.datahome.service.FileService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/22 10:18
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private CommonProperties commonProperties;

    @Override
    public String fileUpload(MultipartFile mf, FileBean fb) throws IOException {

        //  文件类型
        String fileExt = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (fileExt == null || "".equals(fileExt)) return CommonUtil.format(1, "未知的文件类型！");

        // 文件url的路径 /file/...
        String url = "/file/" + fb.getFileName() + "/";

        // 文件物理路径
        String path = request.getSession().getServletContext().getRealPath("/") + url;
        // String path = commonProperties.getBaseFilePath() + url;
        if (!new File(path).exists()) new File(path).mkdirs();

        // UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 重新定义文件名
        String fileName = uuid + "." + fileExt;
        // 上传
        File file = new File(path + fileName);
        mf.transferTo(file);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("url", request.getContextPath() + url + fileName);

        return CommonUtil.format(2000, resultMap);
    }

    @Override
    public String deleteFile(FileBean fb) {
        String realFilePath = CommonUtil.decode(fb.getFilePath(), "bff49ff7f15d4163bec20187");
        // String url = commonProperties.getBaseFilePath() + "/file/" + fb.getFileName() + "/" + realFilePath;
        String url = request.getServletContext().getRealPath("/") + "/file/" + fb.getFileName() + "/" + realFilePath;
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
        return CommonUtil.format(2000, "删除成功！");
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, FileBean fb) throws Exception {
        //获取资源存储路径
        String filePath = fb.getFilePath();
        if (filePath == null) {
            //  return CommonUtil.format(4200, "filePath为null！");
        }
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        OutputStream outputStream = null;
        FileInputStream InputStream = null;
        File file = new File(contextPath + filePath.trim());
        InputStream = new FileInputStream(file);
        response.setHeader("Content-Length", String.valueOf(InputStream.available()));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes(), "iso-8859-1"));
        byte[] bytes = new byte[1024];
        int len;
        outputStream=response.getOutputStream();
        while ((len = InputStream.read(bytes)) > 0) {
            outputStream.write(bytes);
        }
//        Workbook workbook = ExcelUtils.getWorkbook(InputStream, fileName);
        outputStream = response.getOutputStream();
//        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();


    }
}
