package com.datahome.service.impl;

import com.datahome.bean.GdnCityBean;
import com.datahome.entity.GdnCityEntity;
import com.datahome.repository.GdnCityRepository;
import com.datahome.service.GdnCityService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExcelUtils;
import com.datahome.util.JDBCUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class GdnCityServiceImpl implements GdnCityService {

    @Resource
    private GdnCityRepository gdnCityDao;

    @Override
    @Transactional
    public String save(GdnCityBean gdnCityBean) {
        GdnCityEntity gdnCityEntity = new GdnCityEntity();
        CommonUtil.exchangeObj(gdnCityBean, gdnCityEntity);
        gdnCityEntity.setSaveTime(new Date());
        gdnCityEntity.setUpdateTime(new Date());
        gdnCityDao.save(gdnCityEntity);
        return CommonUtil.format(2000, "城市对象添加成功");
    }

    @Override
    @Transactional
    public String importSh() {
        File file = new File("D://全国行政区划20190409(1).xlsx");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            Sheet sheet = ExcelUtils.getSheet(fileInputStream, file.getName());
            //   int rowLength = sheet.getLastRowNum() + 1;                 //总行数
            //   int colLength = sheet.getRow(0).getLastCellNum();   //总列数
            for (int i = 708; i < 724; i++) {
                Row row = sheet.getRow(i);

                Cell cell2 = row.getCell(2);
                Cell cell3 = row.getCell(3);
                //excle列的名字(原始数据)
                String excelcode = ExcelUtils.getCellValue(cell2);
                String excelname = ExcelUtils.getCellValue(cell3);
                //插入gdncity数据表
                String areasql = "HK_SHN='310000'  and  HK_SHI='" + excelcode + "'";
                batchSave(excelcode, excelname, 1, 1, areasql); System.out.println(excelcode + "------" + excelname);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileInputStream!=null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    //导入浦东幼儿园数据
    @Override
    public String importSHGR() {
        File file = new File("D://310115浦东新区.xlsx");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            Sheet sheet = ExcelUtils.getSheet(fileInputStream, file.getName());
               int rowLength = sheet.getLastRowNum() + 1;                 //总行数
            //   int colLength = sheet.getRow(0).getLastCellNum();   //总列数
            for (int i = 1; i < rowLength; i++) {
                Row row = sheet.getRow(i);

                Cell cell2 = row.getCell(3);
                Cell cell3 = row.getCell(7);
                //excle列的名字(原始数据)
                String excelcode = ExcelUtils.getCellValue(cell2);
                String excelname = ExcelUtils.getCellValue(cell3);
                //插入gdncity数据表
                String areasql = "HK_SHN='310000'  and  HK_SHI='310115' and HK_QX= '"  + excelcode + "'";
                batchSave(excelcode, excelname, 1, 2, areasql);
                System.out.println(excelcode + "------" + excelname);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileInputStream!=null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public void batchSave(String citycode, String cityname, Integer citystatus, Integer levelcode, String areasql) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Long count = 0L;
        try {
            connection = JDBCUtil.getSqlConnection();
            connection.setAutoCommit(false);

            String sql = " insert into age06_gdn_city (citycode,cityname,citystatus,levelcode,areasqlrule,savetime,updatetime) values(?,?,?,?,?,?,?) ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, citycode);
            preparedStatement.setString(2, cityname);
            preparedStatement.setInt(3, citystatus);
            preparedStatement.setInt(4, levelcode);
            preparedStatement.setString(5, areasql);
            preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
            preparedStatement.addBatch();
            count++;
            if (count % 1000 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            System.out.println("count:" + count);

            preparedStatement.executeBatch();
            preparedStatement.clearBatch();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if(connection!=null){
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void main(String str[]) {
        new GdnCityServiceImpl().importSh();
        new GdnCityServiceImpl().importSHGR();
    }


}
