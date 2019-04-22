package com.datahome.controller;

import com.datahome.bean.FileBean;
import com.datahome.service.EditorFileService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author xl
 * @Description: 文本编辑器文件上传
 * @Date: Create in 2018/5/14 10:20
 */

@RestController
@RequestMapping(value = "/editor/file")
public class EditorController {

    @Resource
    private EditorFileService editorFileService;

    /**
     * 文本编辑器上传 部分文本编辑器  固定属性  name=imgFile
     */
    @RequestMapping("/fileUpload.do")
    public String fileUpload(@RequestParam("imgFile") MultipartFile mf, @Valid FileBean fb, BindingResult br) throws Exception {
        fb.setFileName("data");
        return editorFileService.upload(mf, fb);
    }

    /**
     * 文本编辑器的 空间浏览
     */

    @RequestMapping("/management.do")
    public String management(@Valid FileBean fb) {
        return editorFileService.management(fb);
    }
}
