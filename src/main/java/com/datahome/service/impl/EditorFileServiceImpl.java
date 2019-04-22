package com.datahome.service.impl;

import com.alibaba.fastjson.JSON;
import com.datahome.bean.FileBean;
import com.datahome.service.EditorFileService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/19 10:10
 */

@Service
public class EditorFileServiceImpl implements EditorFileService {

    @Resource
    private HttpServletRequest request;

    @Override
    public String upload(MultipartFile multipartFile, FileBean fileBean) {

        //校验文件
        if (multipartFile == null || multipartFile.isEmpty()) return CommonUtil.editorFormat(1, null, "文件为空");

        //校验文件扩展名
        String fileName = multipartFile.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileName.contains(".")) return CommonUtil.editorFormat(1, null, "文件扩展名无效");

        //文件路径
        String url = "/file/editor/";

        //文件存储路径
        String path = request.getServletContext().getRealPath("/") + url;
        // String path = commonProperties.getBaseFilePath() + url;

        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();

        //重命名
        String newFileName = UUID.randomUUID().toString() + "." + fileExt;

        //上传
        File file = new File(folder, newFileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return CommonUtil.editorFormat(1, null, "文件上传失败");
        }
        return CommonUtil.editorFormat(0, request.getContextPath() + url + newFileName, null);
    }

    @Override
    public String management(FileBean fb) {
        //获取参数
        String dir = fb.getDir();
        String order = fb.getOrder();
        String path = fb.getFilePath();

        //处理
        if (order == null) order = "name";
        if (path == null) path = "";

        //校验文件目录
        if (!Arrays.asList(new String[]{"image", "flash", "media", "file"}).contains(dir)) {
            return CommonUtil.editorFormat(1, null, "文件目录无效");
        }

        //校验path
        if (path.contains("..")) return CommonUtil.editorFormat(1, null, "不允许使用..移动到上一级目录");
        if (!"".equals(path) && !path.endsWith("/")) return CommonUtil.editorFormat(1, null, "最后一个字符不是/");

        //物理路径
        String rootPath = request.getServletContext().getRealPath("/") + "file/editor/" + dir + "/";
        File rootFolder = new File(rootPath);
        if (!rootFolder.exists()) rootFolder.mkdirs();

        //web路径
        String rootUrl = request.getContextPath() + "/file/editor/" + dir + "/";

        // 根据path参数，设置各路径和URL
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) return CommonUtil.editorFormat(1, null, "目录不存在");

        //遍历目录取的文件信息
        @SuppressWarnings("rawtypes")
        List<Hashtable> fileList = new ArrayList<>();
        File[] files = currentPathFile.listFiles();
        if (files != null) {
            for (File file : files) {
                Hashtable<String, Object> hash = new Hashtable<>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.asList(new String[]{"gif", "jpg", "png"}).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        //排序
        if ("size".equalsIgnoreCase(order)) {
            fileList.sort((Comparator<Object>) (a, b) -> {
                Hashtable hashA = (Hashtable) a;
                Hashtable hashB = (Hashtable) b;
                if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                    return -1;
                } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                    return 1;
                } else {
                    if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                        return 1;
                    } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        } else if ("type".equalsIgnoreCase(order)) {
            fileList.sort((Comparator<Object>) (a, b) -> {
                Hashtable hashA = (Hashtable) a;
                Hashtable hashB = (Hashtable) b;
                if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                    return -1;
                } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                    return 1;
                } else {
                    return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
                }
            });
        } else {
            fileList.sort((Comparator<Object>) (a, b) -> {
                Hashtable hashA = (Hashtable) a;
                Hashtable hashB = (Hashtable) b;
                if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                    return -1;
                } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                    return 1;
                } else {
                    return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
                }
            });
        }

        //返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("moveup_dir_path", moveupDirPath);
        map.put("current_dir_path", currentDirPath);
        map.put("current_url", currentUrl);
        map.put("total_count", fileList.size());
        map.put("file_list", fileList);

        return JSON.toJSONString(map, CommonUtil.FEATURE);
    }
}
