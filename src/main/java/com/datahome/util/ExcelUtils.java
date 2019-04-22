package com.datahome.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/3/12 11:57
 */
public class ExcelUtils {

    public static Workbook getWorkbook(InputStream inputStream, String fileName) throws Exception {

        Workbook workbook = null;
        //兼容2003 和2007
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new Exception("文件不是excel文件");
        }
        return workbook;
    }

    public static Sheet getSheet(InputStream inputStream, String fileName) throws Exception {
        Sheet sheet = null;
        //兼容2003 和2007
        if (fileName.endsWith("xls")) {
            sheet = new HSSFWorkbook(inputStream).getSheet("Sheet1");
        } else if (fileName.endsWith("xlsx")) {
            sheet = new XSSFWorkbook(inputStream).getSheet("Sheet1");
        } else {
            throw new Exception("文件不是excel文件");
        }
        return sheet;
    }

    public static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case STRING://字符串
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC://数字
                    value = String.valueOf(cell.getNumericCellValue());
                    //如果是时间
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    } else {
                        value = new DecimalFormat("########.########").format(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    break;
                case FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    break;
                case BLANK: // 空值
                    value = "";
                    break;
                case ERROR: // 故障
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value.replace(" ", "");
    }

    /**
     * @param response
     * @param fileName  导出的文件名
     * @param dataList  要导出的数据
     * @param sheetname excel 的 sheet名称
     * @param listTile  excel 的列
     * @return
     * @throws IOException
     */
    public static String export(HttpServletResponse response, String fileName, List<List<String>> dataList, String sheetname, List<String> listTile) throws IOException {
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
      /*  File file = new File(filePath);
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();*/

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);//指定下载的文件名
        response.setContentType("application/vnd.ms-excel");
        OutputStream output = response.getOutputStream();
        output.flush();
        workbook.write(output);
        output.close();

        return "SUCCESS";
    }
}
