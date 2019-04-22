package com.datahome.service.impl;

import com.datahome.bean.IndexDataMgmtBean;
import com.datahome.entity.*;
import com.datahome.repository.CityRepository;
import com.datahome.repository.IndexDataReposstory;
import com.datahome.repository.IndexRepository;
import com.datahome.service.IndexDataMgmtService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExportExcelUtil;
import com.datahome.util.StaffUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/21 15:05
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
public class IndexDataMgmtServiceImpl implements IndexDataMgmtService {

    @Resource
    private IndexDataReposstory indexDataDao;

    @Resource
    private IndexRepository indexDao;

    @Resource
    private ExportExcelUtil exportExcelUtil;

    @Resource
    private CityRepository cityDao;


    @Resource
    private StaffUtil staffUtil;

    @Override
    public String find(IndexDataMgmtBean indexDataMgmtBean) {

        Optional<IndexDataEntity> optionalIndexDataEntity = indexDataDao.findById(indexDataMgmtBean.getId());

        if (!optionalIndexDataEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexDataEntity indexData = optionalIndexDataEntity.get();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", indexData.getId());
        resultMap.put("value", indexData.getValue());
        resultMap.put("indexDataStatus", indexData.getIndexDataStatus());
        return CommonUtil.format(2000, resultMap);
    }

    @Override
    public String finds(IndexDataMgmtBean indexDataMgmtBean) {
        Integer indexId = indexDataMgmtBean.getIndexId();
        Integer cityId = indexDataMgmtBean.getCityId();
        String year = indexDataMgmtBean.getYear();

        //获取当前用户
        StaffEntity staffEntity = staffUtil.getCurrentStaff();

        //1 发布  2 草稿  3 无效数据 4 待审核
        List<String> indexStatus = null;
        String roleKey = staffEntity.getStaffRoleEntity().getRoleKey();
        if ("admin".equals(roleKey)) {
            indexStatus = Arrays.asList("1", "2", "3", "4");
        } else if ("dataEntry".equals(roleKey)) {
            indexStatus = Arrays.asList("2", "4");
        } else if ("review".equals(roleKey)) {
            indexStatus = Arrays.asList("4");
        }

        List<Integer> cityIds2 = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));
        if (cityIds2 == null) {
            return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";
        }

        //获取允许查看的地区数据
        List<Integer> allowCityIds = new ArrayList<>();
        if (cityId == null) {
            allowCityIds = cityIds2;
        } else if (cityIds2.contains(cityId)) {
            allowCityIds.add(cityId);
        }

        Integer total = indexDataDao.findTotal_by_indexId_cityIds_year_indexDataStatus(indexId, allowCityIds, year, indexStatus);
        if (total == 0) {
            return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";
        }

        List<Object[]> dataList = indexDataDao.find_by_indexId_cityIds_year_indexDataStatus(indexId, allowCityIds, year, indexStatus, indexDataMgmtBean.getOrder(), indexDataMgmtBean.getSort(), indexDataMgmtBean.getPageSize(), indexDataMgmtBean.getPageNumber());
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] obj : dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", obj[0]);
            map.put("indexDataStatus", obj[1]);
            map.put("value", obj[2]);
            map.put("year", obj[3]);
            map.put("name", obj[4]);
            map.put("nodeIds", obj[5]);
            map.put("cityName", obj[6]);
            map.put("unitsName", obj[7]);
            map.put("departmentId", obj[9]);
            resultList.add(map);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", resultList);
        resultMap.put("total", total);

        return CommonUtil.format(2000, resultMap);
    }

    @Override
    @Transactional
    public String update(IndexDataMgmtBean indexDataMgmtBean) {
        String indexDataStatus = indexDataMgmtBean.getIndexDataStatus();
        Integer departmentId = indexDataMgmtBean.getDepartmentId();
        Double value = indexDataMgmtBean.getValue();

        Optional<IndexDataEntity> optionalIndexDataEntity = indexDataDao.findById(indexDataMgmtBean.getId());
        if (!optionalIndexDataEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexDataEntity indexDataEntity = optionalIndexDataEntity.get();
        String dataStatus = indexDataEntity.getIndexDataStatus();

        Double grossScore = indexDataEntity.getIndexCityEntity().getIndexEntity().getGrossScore();
        if (value > grossScore) {
            return CommonUtil.format(4200, "分数不能大于总分！");
        }

        //获取当前用户
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        String roleKey = staffEntity.getStaffRoleEntity().getRoleKey();
        List<Integer> cityIds = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));
        if (!cityIds.contains(indexDataEntity.getIndexCityEntity().getCityEntity().getId())) {
            return CommonUtil.format(4200, "无权修改");
        }
        // 指标数据状态(1 发布  2 草稿  3 无效数据 4 待审核)

        //dataEntry  数据录入员
        if ("dataEntry".equals(roleKey)) {
            if (!"2".equals(dataStatus) || (!"2".equals(indexDataStatus) && !"4".equals(indexDataStatus))) {
                return CommonUtil.format(4200, "无权操作！");
            }
            indexDataEntity.setValue(indexDataMgmtBean.getValue());
        }

        //review 审核员
        if ("review".equals(roleKey)) {
            if (!"4".equals(dataStatus) || (!"1".equals(indexDataStatus) && !"2".equals(indexDataStatus) && !"3".equals(indexDataStatus))) {
                return CommonUtil.format(4200, "无权操作！");
            }
        }

//        //数据来源
//        DepartmentEntity departmentEntity = null;
//        if (departmentId != null) {
//            Optional<DepartmentEntity> departmentEntityOptional = departmentDao.findById(departmentId);
//            if (!departmentEntityOptional.isPresent()) {
//                return CommonUtil.format(4200, "数据来源不存在！");
//            }
//            departmentEntity = departmentEntityOptional.get();
//        }

//        indexDataEntity.setDepartmentEntity(departmentEntity);
        indexDataEntity.setIndexDataStatus(indexDataStatus);
        indexDataEntity.setValue(value);
        indexDataEntity.setUpdateTime(new Date());
        //修改 指标数据
        indexDataDao.save(indexDataEntity);
        return CommonUtil.format(2000, "修改成功！");
    }


    @Override
    @Async("threadPoolTaskExecutor")
    public Future<String> saveIndexData(IndexDataMgmtBean indexDataMgmtBean) {
        String year = indexDataMgmtBean.getYear();

       /* StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (!"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return new AsyncResult<>(CommonUtil.format(4200, "无权操作！"));
        }*/

        //查询所有城市的有效指标
        List<IndexCityEntity> indexCityEntities = indexDao.findIndexCity_by_cityId_cityStatus_indexId_indexStatus(null, "1", null, "1");
        List<Map<String, Object>> datas = new ArrayList<>();
        for (IndexCityEntity indexCityEntity : indexCityEntities) {
            List indexDataList = indexDataDao.findIndexDatas_indexCityIds_years_indexDataStatus_ids(Arrays.asList(indexCityEntity.getId()), Arrays.asList(year), null, null);
            if (indexDataList == null || indexDataList.size() == 0) {
                //批量创建新的指标数据
                Map<String, Object> map = new HashMap<>();
                map.put("indexCityId", indexCityEntity.getId());
                map.put("indexDataStatus", "2");
                map.put("year", year);
                map.put("value", String.valueOf(new Random().nextInt(90) % (75) + 25));
                datas.add(map);
            }
        }
        System.out.println("开始批量");
        indexDataDao.batchSave(datas);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>(CommonUtil.format(2000, "基础数据创建任务已提交，该任务预计需要十五分钟！"));
    }

    //指标数据导出为excel格式
    @Override
    public String exportExcelData(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest
            request, HttpServletResponse response) {

        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        List<Integer> cityIds = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));
        List<IndexDataEntity> indexDataList = indexDataDao.findBy_IndexDataStatus_cityIds("1", cityIds);
        if (indexDataList == null || indexDataList.size() == 0) {
            return CommonUtil.format(4200, "查无数据！");
        }

        String columnName = "指标范围";

        List<String> titleList = Arrays.asList("指标名称", "指标数据", "指标年份", columnName, "评分标准", "指标描述");
        List<List<String>> dataList = new ArrayList<>();
        for (IndexDataEntity indexDataEntity : indexDataList) {

            CityEntity cityEntity = indexDataEntity.getIndexCityEntity().getCityEntity();
            IndexEntity indexEntity = indexDataEntity.getIndexCityEntity().getIndexEntity();
            IndexUnitsEntity indexUnitsEntity = indexEntity.getIndexUnitsEntity();

            List<String> list = new ArrayList<>();
            list.add(indexEntity.getName() + "(" + indexUnitsEntity.getUnitsName() + ")");
            list.add(String.valueOf(indexDataEntity.getValue()));
            list.add(cityEntity.getCityName());

            list.add(indexEntity.getEvaluationStandards());
            list.add(indexEntity.getDescription());

            dataList.add(list);
        }
        try {
            exportExcelUtil.export(request, response, dataList, "指标数据", titleList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonUtil.format(2000, "导出成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(IndexDataMgmtBean indexDataMgmtBean, HttpServletRequest request, HttpServletResponse response) {
        Optional<IndexDataEntity> optionalIndexDataEntity = indexDataDao.findById(indexDataMgmtBean.getId());
        if (!optionalIndexDataEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexDataEntity indexDataEntity = optionalIndexDataEntity.get();
        indexDataDao.delete(indexDataEntity);

        return CommonUtil.format(2000, "删除成功");
    }
}
