package com.datahome.controller;

import com.datahome.bean.FileBean;
import com.datahome.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/22 10:05
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 上传文件
     */
    @ApiOperation("文件上传")
    @PostMapping("/fileUpload.do")
    public String fileUpload(@RequestParam("data") MultipartFile mf, @Valid FileBean fb, BindingResult br) throws Exception {
        return fileService.fileUpload(mf, fb);
    }


    /**
     * 下载
     */
    @ApiOperation("文件下载")
    @GetMapping("/fileDownload.do")
    public void fileUpload(HttpServletResponse response, HttpServletRequest request, @Valid FileBean fb, BindingResult br) throws Exception {
        fileService.download(request, response, fb);
    }

    /**
     * 删除文件
     */
    @RequestMapping("/deleteFile.do")
    public String deleteFile(@Valid FileBean fb, BindingResult br) throws Exception {
        return fileService.deleteFile(fb);
    }
}
