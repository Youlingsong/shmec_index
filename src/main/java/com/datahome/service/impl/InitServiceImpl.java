package com.datahome.service.impl;

import com.datahome.entity.*;
import com.datahome.repository.*;
import com.datahome.service.InitService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/7/17 20:12
 */

@Service
public class InitServiceImpl implements InitService {


    @Resource
    private IndexRepository indexDao;

    @Resource
    private AccountRepository accountDao;

    @Resource
    private CityRepository cityDao;

    @Resource
    private IndexUnitsRepository indexUnitsDao;

    @Resource
    private IndexDataReposstory indexDataDao;



    /**
     * 初始化城市数据
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String initCity() {
        File file = new File("I:\\cityData.xlsx");
        if (!file.exists()) {
            return CommonUtil.format(1, "文件不存在！");
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            Sheet sheet = ExcelUtils.getSheet(fileInputStream, "cityData.xlsx");
            if (sheet == null) {
                return CommonUtil.format(1, "文件不存在！");
            }

            Integer lastRowNum = sheet.getLastRowNum();
            if (lastRowNum == 0) {
                return CommonUtil.format(1, "文件不存在！");
            }

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);

                String cityName = ExcelUtils.getCellValue(row.getCell(0));
                String cityParentName = ExcelUtils.getCellValue(row.getCell(1));
                String cityLevel = ExcelUtils.getCellValue(row.getCell(2));

                System.out.println("cityName:" + cityName + " cityName:" + cityName + " cityLevel:" + cityLevel);

                CityEntity cityEntity = new CityEntity();
                cityEntity.setCityName(cityName);
                cityEntity.setLevelCode(cityLevel);
                cityEntity.setSaveTime(new Date());
                //根级城市
                if ("0".equals(cityLevel)) {
                    cityEntity.setParentId(0);
                } else {
                    CityEntity cityEntity1 = cityDao.findByCityName(cityParentName);
                    if (cityEntity1 == null) {
                        return CommonUtil.format(1, "父级城市不存在！" + cityName);
                    }
                    cityEntity.setParentId(cityEntity1.getId());
                }

                cityDao.save(cityEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonUtil.format(0, "SUCCESS");
    }

    /**
     * 初始化指标体系
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String initIndex() {

        File file = new File("I:\\index.xlsx");
        if (!file.exists()) {
            return CommonUtil.format(1, "文件不存在！");
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            Sheet sheet = ExcelUtils.getSheet(fileInputStream, "index.xlsx");
            if (sheet == null) {
                return CommonUtil.format(1, "文件不存在！");
            }

            Integer lastRowNum = sheet.getLastRowNum();
            if (lastRowNum == 0) {
                return CommonUtil.format(1, "文件不存在！");
            }

            AccountEntity accountEntity = accountDao.findByName("admin");

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);

                String indexName = ExcelUtils.getCellValue(row.getCell(0));
                String indexParentName = ExcelUtils.getCellValue(row.getCell(1));
                String cityCode = ExcelUtils.getCellValue(row.getCell(2));
                String indexUnits = ExcelUtils.getCellValue(row.getCell(3));
                String grossScoreString = ExcelUtils.getCellValue(row.getCell(4));

                Double grossScore = 0D;
                if (grossScoreString == null) {
                    grossScore = 15D;
                }

                System.out.println("indexName:" + indexName + " cityCode:" + cityCode + " indexParentName:" + indexParentName + " 计量单位:" + indexUnits);
                IndexEntity indexEntity1 = null;
                if (indexParentName != null && !"".equals(indexParentName) && !"0".equals(indexParentName)) {
                    List<IndexEntity> indexEntitys = indexDao.finds_by_ids_unitsId_indexName(null, null, indexParentName);
                    indexEntity1 = indexEntitys.get(0);
                }

                //指标计量单位
                List<CityEntity> citys = findCity_by_name(cityCode);
                IndexUnitsEntity indexUnitsEntity = indexUnitsDao.findByUnitsName(indexUnits);
                if (indexUnitsEntity == null) {
                    return CommonUtil.format(1, "indexUnits不存在！" + indexName);
                }

                //保存指标
                IndexEntity indexEntity = saveIndex(accountEntity, indexName, grossScore, indexUnitsEntity, indexEntity1);
                save_index_city(indexEntity, citys);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonUtil.format(0, "SUCCESS");
    }


    /**
     * 初始化指标数据
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String initIndexData() {


        File file = new File("I:\\indexData.xlsx");
        if (!file.exists()) {
            return CommonUtil.format(1, "文件不存在！");
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            Sheet sheet = ExcelUtils.getSheet(fileInputStream, "indexData.xlsx");
            if (sheet == null) {
                return CommonUtil.format(1, "文件不存在！");
            }

            Integer lastRowNum = sheet.getLastRowNum();
            if (lastRowNum == 0) {
                return CommonUtil.format(1, "文件不存在！");
            }

          //  DepartmentEntity departmentEntity = departmentDao.findByDepartmentName("上海市教委");

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);

                String year = ExcelUtils.getCellValue(row.getCell(0));
                String indexName = ExcelUtils.getCellValue(row.getCell(1));
                String cityName = ExcelUtils.getCellValue(row.getCell(2));

                //获取指标信息
                List<IndexEntity> entities = indexDao.finds_by_ids_unitsId_indexName(null, null, indexName);
                if (entities == null || entities.size() == 0) {
                    return CommonUtil.format(1, "指标不存在！" + indexName);
                }
                IndexEntity indexEntity = entities.get(0);

                //获取城市信息
                CityEntity cityEntity = cityDao.findByCityName(cityName);
                List<IndexCityEntity> indexCityEntity = indexDao.findIndexCity_by_cityId_cityStatus_indexId_indexStatus(cityEntity.getId(), "1", indexEntity.getId(), "1");
                if (indexCityEntity == null || indexCityEntity.size() == 0) {
                    return CommonUtil.format(1, "该城市不存在！" + cityName + "--" + indexName);
                }
                IndexCityEntity indexCityEntity1 = indexCityEntity.get(0);

                //实例化
                IndexDataEntity indexDataEntity = new IndexDataEntity();
                indexDataEntity.setIndexDataStatus("1");
            //    indexDataEntity.setDepartmentEntity(departmentEntity);
                indexDataEntity.setIndexCityEntity(indexCityEntity1);
                indexDataEntity.setYear(year);
                indexDataEntity.setSaveTime(new Date());

                indexDataDao.save(indexDataEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonUtil.format(0, "SUCCESS");
    }


    public List<CityEntity> findCity_by_name(String cityCode) throws Exception {
        //所有的城市
        if ("all".equals(cityCode)) {
            return cityDao.findAll();
        }
        List<CityEntity> resultList = new ArrayList<>();
        if (cityCode.contains("@")) {
            String[] cityCodes = cityCode.split("@");
            for (int i = 0; i < cityCodes.length; i++) {
                String city = cityCodes[i];
                if ("各区".equals(city)) {
                    resultList = cityDao.findAllChildrenBy_nodeIds_cityStatus(cityDao.findByCityName("上海市").getNodeIds(), Arrays.asList("1"));
                    continue;
                }
                resultList.add(cityDao.findByCityName(city));
            }
        }
        return resultList;
    }

    public void save_index_city(IndexEntity indexEntity, List<CityEntity> cityEntitys) {
        for (CityEntity cityEntity : cityEntitys) {
            IndexCityEntity indexCityEntity = new IndexCityEntity();
            indexCityEntity.setIndexEntity(indexEntity);
            indexCityEntity.setCityEntity(cityEntity);
            indexCityEntity.setSaveTime(new Date());
            indexDao.saveIndexCity(indexCityEntity);
        }
    }

    public IndexEntity saveIndex(AccountEntity accountEntity, String indexName, Double grossScore, IndexUnitsEntity indexUnitsEntity, IndexEntity parentIndexEntity) {
        Integer parentId = 0;
        String parentNodeIds = "";
        if (parentIndexEntity != null) {
            parentNodeIds = parentIndexEntity.getNodeIds();
            parentId = parentIndexEntity.getId();
        }

        IndexEntity indexEntity = new IndexEntity();
        indexEntity.setIndexStatus("1");
        indexEntity.setName(indexName);
        indexEntity.setParentId(parentId);
        indexEntity.setSaveTime(new Date());
        indexEntity.setIndexUnitsEntity(indexUnitsEntity);
        indexEntity.setGrossScore(grossScore);

        indexDao.save(indexEntity);
        if ("".equals(parentNodeIds)) {
            indexEntity.setNodeIds(indexEntity.getId().toString());
        } else {
            indexEntity.setNodeIds(parentNodeIds + "," + indexEntity.getId());
        }
        indexDao.save(indexEntity);

        return indexEntity;
    }
}
