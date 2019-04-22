package com.datahome.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class ExportExcelUtil {


    //导出
    public String export(HttpServletRequest request, HttpServletResponse response, List<List<String>> dataList, String sheetname, List<String> listTile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetname); // sheet名称
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < listTile.size(); i++) {
            row.createCell(i).setCellValue(listTile.get(i));
        }
        for (int j = 0; j < dataList.size(); j++) {
            XSSFRow rows = sheet.createRow(j + 1);
            List<String> list = dataList.get(j);
            for (int i = 0; i < list.size(); i++) {
                rows.createCell(i).setCellValue(list.get(i));
            }
        }

        //制作Excel
        String path = request.getSession().getServletContext().getRealPath("/") + "file/questionare/";
        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();
        File file = new File(folder + "/data.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        //下载
        OutputStream os = response.getOutputStream();
        if (!file.exists()) return CommonUtil.format(1, "文件不存在");
        response.setHeader("Content-disposition", "attachment;filename=data.xlsx");
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content_Length", String.valueOf(file.length()));
        os.write(FileUtils.readFileToByteArray(file));
        response.flushBuffer();
        os.flush();
        if (os != null) os.close();

        return null;
    }
}
