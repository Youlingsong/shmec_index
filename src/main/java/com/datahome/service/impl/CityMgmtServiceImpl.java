package com.datahome.service.impl;

import com.datahome.bean.CityMgmtBean;
import com.datahome.entity.CityEntity;
import com.datahome.entity.IndexCityEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.entity.StaffEntity;
import com.datahome.repository.CityRepository;
import com.datahome.repository.IndexDataReposstory;
import com.datahome.repository.IndexRepository;
import com.datahome.service.CityMgmtService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExcelUtils;
import com.datahome.util.StaffUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 15:08
 */


@Service
public class CityMgmtServiceImpl implements CityMgmtService {

    @Resource
    private CityRepository cityDao;

    @Resource
    private IndexDataReposstory indexDataDao;

    @Resource
    private IndexRepository indexDao;

    @Resource
    private StaffUtil staffUtil;

    @Override
    public String initCity() throws Exception {

        File file = new File("I:\\陕西省行政区域.xlsx");
        if (!file.exists()) {
            System.out.println("文件不存在！");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = ExcelUtils.getWorkbook(fileInputStream, "陕西省行政区域.xlsx");
        if (workbook == null) {
            return CommonUtil.format(1, "文件不存在！");
        }

        for (int w = 0; w < workbook.getNumberOfSheets(); w++) {
            Sheet sheet = workbook.getSheetAt(w);

            //获取总行数
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum == 0) {
                throw new Exception("excel文件为空！");
            }
            for (int i = 0; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                String city = ExcelUtils.getCellValue(row.getCell(0)).trim();
                String parentCity = ExcelUtils.getCellValue(row.getCell(1));
                String levelCode = ExcelUtils.getCellValue(row.getCell(2));
                if ("".equals(city) || "".equals(parentCity)) {
                    continue;
                }
                city = city.substring(6, city.length());

                Integer parentId = 0;
                if (!"陕西省".equals(parentCity)) {
                    System.out.println("parentCity:" + parentCity);
                    CityEntity cityEntity = cityDao.findByCityName(parentCity);

                    if (cityEntity == null) {
                        return CommonUtil.format(2000, "父级分类错误！");
                    }
                    parentId = cityEntity.getId();
                }

                CityEntity cityEntity = new CityEntity();
                cityEntity.setCityName(city);
                cityEntity.setLevelCode(levelCode);
                cityEntity.setParentId(parentId);
                cityEntity.setSaveTime(new Date());
                cityEntity.setCityStatus("1");

                cityDao.save(cityEntity);

            }
        }
        return CommonUtil.format(2000, "初始化成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(CityMgmtBean cityMgmtBean) {
        String cityName = cityMgmtBean.getCityName();
        String cityStatus = cityMgmtBean.getCityStatus();
        Integer parentId = cityMgmtBean.getParentId();

        if (cityDao.findByCityNameAndParentId(cityName, parentId) != null) {
            return CommonUtil.format(4200, "城市名称已存在！");
        }

        String levelCode = "";
        boolean nodeIdUpdateFlag = true;
        CityEntity parentCity = null;
        if (parentId != 0) {
            nodeIdUpdateFlag = false;
            List<CityEntity> cityEntities = cityDao.findByCityStatusAndId("1", parentId);
            if (cityEntities == null || cityEntities.size() != 1) {
                return CommonUtil.format(4200, "父级城市信息异常！");
            }
            parentCity = cityEntities.get(0);

            if ("2".equals(parentCity.getCityStatus())) {
                cityStatus = "1";
            }
            levelCode = "2";
        } else {
            levelCode = "1";
        }

        //实例化
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCityName(cityName);
        cityEntity.setLevelCode(levelCode);
        cityEntity.setCityStatus(cityStatus);
        cityEntity.setParentId(parentId);
        cityEntity.setSaveTime(new Date());
        cityDao.save(cityEntity);

        //修改节点
        if (nodeIdUpdateFlag) {
            cityEntity.setNodeIds(cityEntity.getId().toString());
        } else {
            cityEntity.setNodeIds(parentCity.getNodeIds() + "," + cityEntity.getId());
        }
        cityDao.save(cityEntity);

        //添加中间表
        saveIndexCity_city(cityEntity);

        return CommonUtil.format(2000, "保存成功！");
    }

    //添加指标_地区
    @Transactional(rollbackFor = Exception.class)
    public String saveIndexCity_city(CityEntity cityEntity) {

        IndexCityEntity indexCityEntity = null;
        List<IndexEntity> indexEntities = indexDao.findAllByIndexStatus("1");
        for (IndexEntity indexEntity : indexEntities) {
            List<IndexCityEntity> list = indexDao.findIndexCity_by_cityId_cityStatus_indexId_indexStatus(cityEntity.getId(), "1", indexEntity.getId(), "1");
            if (list == null || list.size() == 0) {
                indexCityEntity = new IndexCityEntity();
                indexCityEntity.setCityEntity(cityEntity);
                indexCityEntity.setIndexEntity(indexEntity);
                indexCityEntity.setSaveTime(new Date());
                indexDao.saveIndexCity(indexCityEntity);
            }
        }
        return "SUCCESS";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String combox(CityMgmtBean cityMgmtBean) {
        Integer parentId = cityMgmtBean.getParentId();
        if (parentId == null) {
            parentId = 0;
        }
        List<CityEntity> cityEntitys = cityDao.findByParentIdAndCityStatusIn(parentId, Arrays.asList("1", "2"));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (CityEntity cityEntity : cityEntitys) {
            resultList.add(getCityEntityMap(cityEntity));
        }
        return CommonUtil.format(2000, resultList);
    }

    @Override
    public String findAll(CityMgmtBean cityMgmtBean) {

        //
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        List<String> status = Arrays.asList("1");
        if ("admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            status = Arrays.asList("1", "2");
        }

        List<CityEntity> cityEntitys = cityDao.findAllChildrenBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), status);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (CityEntity cityEntity : cityEntitys) {
            resultList.add(getCityEntityMap(cityEntity));
        }

        return CommonUtil.format(2000, resultList);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(CityMgmtBean cityMgmtBean) {

        Integer parentId = cityMgmtBean.getParentId();
        String cityStatus = cityMgmtBean.getCityStatus();
        String cityName = cityMgmtBean.getCityName();

        Optional<CityEntity> optionalCityEntity = cityDao.findById(cityMgmtBean.getId());
        if (!optionalCityEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        CityEntity cityEntity = optionalCityEntity.get();

        //新城市名称校验
        CityEntity city = cityDao.findByCityName(cityName);
        if (city != null && city.getId() != cityEntity.getId()) {
            return CommonUtil.format(4200, "城市名称已存在！");
        }

        //校验父级城市信息
        String levelCode = "";
        if (parentId != 0) {
            List<CityEntity> cityEntities = cityDao.findByCityStatusAndId("1", parentId);
            if (cityEntities == null || cityEntities.size() != 1) {
                return CommonUtil.format(4200, "父级城市信息异常！");
            }
            CityEntity parentCity = cityEntities.get(0);
            if ("2".equals(parentCity.getCityStatus())) {
                cityStatus = "2";
            }
            levelCode = "2";
        } else {
            levelCode = "1";
        }

        //当市级城市设置为无效 ，县区级也需要设置为无效 ,且对应的数据也设为无效
        if ("1".equals(levelCode) && "2".equals(cityStatus)) {
            cityDao.updateCityStatus_by_parentId(cityEntity.getId(), cityStatus);

            indexDataDao.updateIndexDataStatusBy_cityId("3", cityEntity.getId());
        }

        cityEntity.setParentId(parentId);
        cityEntity.setCityStatus(cityStatus);
        cityEntity.setLevelCode(levelCode);
        cityEntity.setCityName(cityName);
        cityEntity.setUpdateTime(new Date());

        cityDao.save(cityEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    private Map<String, Object> getCityEntityMap(CityEntity cityEntity) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", cityEntity.getId());
        resultMap.put("cityName", cityEntity.getCityName());
        resultMap.put("cityStatus", cityEntity.getCityStatus());
        resultMap.put("levelCode", cityEntity.getLevelCode());
        resultMap.put("parentId", cityEntity.getParentId());
        return resultMap;
    }
}
