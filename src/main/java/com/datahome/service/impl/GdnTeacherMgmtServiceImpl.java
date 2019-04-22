package com.datahome.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.datahome.bean.GdnClassTeacherMgmtBean;
import com.datahome.bean.GdnGardenMgmtBean;
import com.datahome.bean.GdnTeacherMgmtBean;
import com.datahome.easypoi.ImportTeacherBean;
import com.datahome.entity.GdnClassEntity;
import com.datahome.entity.GdnClassTeacherEntity;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.entity.GdnTeacherEntity;
import com.datahome.repository.GdnClassMgmtRepository;
import com.datahome.repository.GdnClassTeacherMgmtRepository;
import com.datahome.repository.GdnGardenMgmtRepository;
import com.datahome.repository.GdnTeacherMgmtRepository;
import com.datahome.service.GdnTeacherMgmtService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class GdnTeacherMgmtServiceImpl implements GdnTeacherMgmtService {
    @Resource
    private GdnTeacherMgmtRepository gdnTeacherMgmtDao;

    @Resource
    private GdnGardenMgmtRepository gdnGardenMgmtDao;

    @Resource
    private GdnClassMgmtRepository gdnClassMgmtDao;

    @Resource
    private GdnClassTeacherMgmtRepository gdnClassTeacherMgmtDao;
    @Resource
    private HttpServletRequest request;


    @Override
    public String save(GdnTeacherMgmtBean gdnTeacherMgmtBean) {
        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnTeacherMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }
        GdnTeacherEntity gdnTeacherEntity = new GdnTeacherEntity();
        CommonUtil.exchangeObj(gdnTeacherMgmtBean, gdnTeacherEntity);
        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();
        gdnTeacherEntity.setGdnGardenEntity(gdnGardenEntity);
        gdnTeacherEntity.setCreateTime(new Date());
        gdnTeacherEntity.setUpdateTime(new Date());
        gdnTeacherMgmtDao.save(gdnTeacherEntity);
        return CommonUtil.format(2000, "教师保存成功");
    }

    @Override
    public String update(GdnTeacherMgmtBean gdnTeacherMgmtBean) {
        Optional<GdnGardenEntity> optionGdnGardenEntity = gdnGardenMgmtDao.findById(gdnTeacherMgmtBean.getGdnGardenid());
        if (!optionGdnGardenEntity.isPresent()) {
            return CommonUtil.format(4200, "园所不存在！");
        }
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherMgmtBean.getId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }
        GdnTeacherEntity gdnTeacherEntity = optionGdnTeacherEntity.get();
        CommonUtil.exchangeObj(gdnTeacherMgmtBean, gdnTeacherEntity);
        GdnGardenEntity gdnGardenEntity = optionGdnGardenEntity.get();
        //更新园所
        gdnTeacherEntity.setGdnGardenEntity(gdnGardenEntity);
        gdnTeacherEntity.setUpdateTime(new Date());
        gdnTeacherMgmtDao.save(gdnTeacherEntity);

        return CommonUtil.format(2000, "教师更新成功");
    }

    @Override
    public String find(GdnTeacherMgmtBean gdnTeacherMgmtBean) {
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnTeacherMgmtBean.getId());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }
        GdnTeacherEntity gdnTeacherEntity = optionGdnTeacherEntity.get();
        return CommonUtil.format(2000, gdnTeacherEntity);
    }

    @Override
    public String finds(GdnTeacherMgmtBean gdnTeacherMgmtBean) {
        List<GdnTeacherEntity> GdnTeacherEntitiees = gdnTeacherMgmtDao.findby_name_district_Level(gdnTeacherMgmtBean);
        return CommonUtil.format(2000, GdnTeacherEntitiees);
    }

    @Override
    public String add(GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean) {
        Optional<GdnTeacherEntity> optionGdnTeacherEntity = gdnTeacherMgmtDao.findById(gdnClassTeacherMgmtBean.getGdnTeacherid());
        if (!optionGdnTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师不存在！");
        }

        Optional<GdnClassEntity> optionGdnClassEntity = gdnClassMgmtDao.findById(gdnClassTeacherMgmtBean.getGdnClassid());
        if (!optionGdnClassEntity.isPresent()) {
            return CommonUtil.format(4200, "班级不存在！");
        }
        GdnTeacherEntity gdnTeacherEntity = optionGdnTeacherEntity.get();
        GdnClassEntity gdnClassEntity = optionGdnClassEntity.get();
        GdnClassTeacherEntity gdnClassTeacherEntity = new GdnClassTeacherEntity();
        CommonUtil.exchangeObj(gdnClassTeacherMgmtBean, gdnClassTeacherEntity);
        gdnClassTeacherEntity.setGdnClassEntity(gdnClassEntity);
        gdnClassTeacherEntity.setGdnTeacherEntity(gdnTeacherEntity);
        gdnClassTeacherEntity.setCreateTime(new Date());
        gdnClassTeacherEntity.setUpdateTime(new Date());
        gdnClassTeacherMgmtDao.save(gdnClassTeacherEntity);
        return CommonUtil.format(2000, "教师班级中间表保存成功");
    }

    @Override
    public String serach(GdnClassTeacherMgmtBean gdnClassTeacherMgmtBean) {
        Optional<GdnClassTeacherEntity> optionGdnClassTeacherEntity = gdnClassTeacherMgmtDao.findById(gdnClassTeacherMgmtBean.getId());
        if (!optionGdnClassTeacherEntity.isPresent()) {
            return CommonUtil.format(4200, "教师班级中间表不存在！");
        }
        return CommonUtil.format(2000, optionGdnClassTeacherEntity.get());
    }

    @Override
    public String importExcel(MultipartFile file, HttpServletResponse response) {

        // 初始化easyPOI 并开启校验
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);

        //错误消息
        List<ImportTeacherBean> faileList = new ArrayList<>();
        FileOutputStream fileOutputStream = null;
        long successCount = 0;
        String localFilePath = "";
        String fileName = "";
        try {
            ExcelImportResult<ImportTeacherBean> result = ExcelImportUtil.importExcelMore(file.getInputStream(), ImportTeacherBean.class, importParams);
            //添加 注解校验失败的数据
            if (result.isVerfiyFail()) {
                faileList = result.getFailList();
            }
            List<ImportTeacherBean> importTeacherBeanlist = result.getList();
            for (ImportTeacherBean importTeacherBean : importTeacherBeanlist) {
                GdnGardenMgmtBean gdnGardenMgmtBean = new GdnGardenMgmtBean();
                gdnGardenMgmtBean.setName(importTeacherBean.getGdnGardenname());
                List<GdnGardenEntity> gdnGardenEntities = gdnGardenMgmtDao.findby_name_district_Level(gdnGardenMgmtBean);
               if(!CommonUtil.isEmptyList(gdnGardenEntities)){
                   importTeacherBean.setErrorMsg("教师所属园不存在");
                   faileList.add(importTeacherBean);
                   break;
               }
                GdnTeacherEntity gdnTeacherEntity = new GdnTeacherEntity();
                CommonUtil.exchangeObj(importTeacherBean, gdnTeacherEntity);
                //Todo 已名字查询园所，得到的列表，不确认是唯一的园所，这里待确认！！！！！！！！！！
                gdnTeacherEntity.setGdnGardenEntity(gdnGardenEntities.get(0));
                gdnTeacherMgmtDao.save(gdnTeacherEntity);
                successCount++;
            }
            //导出异常信息
            if (!faileList.isEmpty()) {
                //导出错误数据
                ExportParams exportParams = new ExportParams();
                exportParams.setSheetName("教师数据");
                Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, ImportTeacherBean.class, faileList);
                int sheets = workbook.getNumberOfSheets();
                for (int i = 0; i < sheets; i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (sheet == null) continue;
                    int rows = sheet.getLastRowNum();
                    if (rows == 0) continue;

                    //将表格中的 "null",null 替换为""
                    for (int r = 1; r <= rows; r++) {
                        Row row = sheet.getRow(r);
                        if (row == null) continue;
                        Short lastCellnum = row.getLastCellNum();
                        for (int c = 0; c < lastCellnum; c++) {
                            Cell cell = row.getCell(c);
                            String value = ExcelUtils.getCellValue(cell);
                            if (StringUtils.isEmpty(value) || "null".equals(value)) {
                                value = "";
                            }
                            cell.setCellValue(value);
                        }
                    }
                }

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //创建本地临时文件
                localFilePath = "/data/temp/" + uuid + "/";
                // 文件物理路径
                String path = request.getSession().getServletContext().getRealPath("/");
                String basePath = path + localFilePath;
                File temp = new File(basePath);
                if (!temp.exists()) temp.mkdirs();
                fileName = "《教师数据表" + CommonUtil.dateFormat.format(new Date()).replaceAll("-", ".") + "》" + ".xlsx";
                fileOutputStream = new FileOutputStream(new File(basePath + fileName));
                workbook.write(fileOutputStream);
                ExcelExportUtil.closeExportBigExcel();
                fileOutputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("successCount", successCount);
        resultMap.put("errorCount", faileList.size());
        resultMap.put("errorFilePath", localFilePath + fileName);
        return CommonUtil.format(0, resultMap);
    }
}
