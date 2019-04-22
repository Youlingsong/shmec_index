package com.datahome.service.impl;

import com.datahome.bean.IndexMgmtBean;
import com.datahome.entity.*;
import com.datahome.repository.*;
import com.datahome.service.IndexMgmtService;
import com.datahome.util.CommonUtil;
import com.datahome.util.ExportExcelUtil;
import com.datahome.util.StaffUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author xl
 * @Description: 指标体系管理
 * @Date: Create in 2018/5/10 15:51
 */

@Service
public class IndexMgmtServiceImpl implements IndexMgmtService {

    @Resource
    private CityRepository cityDao;

    @Resource
    private IndexUnitsRepository indexUnitsDao;

    @Resource
    private IndexRepository indexDao;

    @Resource
    private IndexDataReposstory indexDataDao;

    @Resource
    private IndexGroupRepository indexGroupDao;

    @Resource
    private ExportExcelUtil exportExcelUtil;

    @Resource
    private HttpServletRequest request;

    @Resource
    private StaffUtil staffUtil;

    @Resource
    private IndexCityReposstory IndexCityDao;


    //新增指标
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(IndexMgmtBean indexMgmtBean) throws Exception {

        //用户角色校验
        if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }

        //获取参数
        Integer parentId = indexMgmtBean.getParentId();
        Integer indexUnitsId = indexMgmtBean.getUnitsId();
        String indexStatus = indexMgmtBean.getIndexStatus();
        String name = indexMgmtBean.getName();
        String description = indexMgmtBean.getDescription();
        String evaluationStandards = indexMgmtBean.getEvaluationStandards();
        List<Integer> groupIds = indexMgmtBean.getGroupId();
        Double grossScore = indexMgmtBean.getGrossScore();
        String nodeIds = "";

        //父级指标检验
        boolean nodeIdUpdateFlag = false;
        IndexEntity index = null;
        if (parentId != 0) {
            Optional<IndexEntity> optionalIndexEntity = indexDao.findById(parentId);
            if (!optionalIndexEntity.isPresent()) {
                return CommonUtil.format(4200, "父级指标错误！");
            }
            index = optionalIndexEntity.get();
            //如果父指标无效，则子指标也改为无效
            if ("2".equals(index.getIndexStatus())) {
                indexStatus = "2";
            }
            nodeIdUpdateFlag = true;
        }

        //校验 指标名称是否存在
        List<IndexEntity> entities = indexDao.finds_by_ids_unitsId_indexName(null, null, name);
        if (entities != null && entities.size() > 0) {
            return CommonUtil.format(4200, "指标名称已存在！");
        }

        //指标计量单位
        Optional<IndexUnitsEntity> optionalIndexUnitsEntity = indexUnitsDao.findById(indexUnitsId);
        if (optionalIndexUnitsEntity == null) {
            return CommonUtil.format(4200, "计量单位错误！");
        }

        //指标分组
        List<IndexGroupEntity> indexGroupEntities = null;
        if (groupIds != null) {
            indexGroupEntities = indexGroupDao.findByIdIn(groupIds);
            if (indexGroupEntities == null || indexGroupEntities.size() != groupIds.size()) {
                return CommonUtil.format(4200, "指标分组错误！");
            }
        }

        //实例化
        IndexEntity indexEntity = new IndexEntity();
        indexEntity.setIndexUnitsEntity(optionalIndexUnitsEntity.get());
        indexEntity.setIndexStatus(indexStatus);
        indexEntity.setParentId(parentId);
        indexEntity.setName(name);
        indexEntity.setDescription(description);
        indexEntity.setEvaluationStandards(evaluationStandards);
        indexEntity.setSaveTime(new Date());
        indexEntity.setGrossScore(grossScore);
        indexDao.save(indexEntity);

        //修改指标的nodeIds
        if (nodeIdUpdateFlag) {
            nodeIds = index.getNodeIds() + "," + indexEntity.getId();
        } else {
            nodeIds = indexEntity.getId().toString();
        }
        indexEntity.setNodeIds(nodeIds);
        indexDao.save(indexEntity);

        //添加指标_城市
        String result = saveIndexCityOrDepartment(indexEntity);
        if (!"SUCCESS".equals(result)) {
            return result;
        }

        //添加指标_分组
        if (indexGroupEntities != null) {
            result = saveIndex_group(indexEntity, indexGroupEntities);
            if (!"SUCCESS".equals(result)) {
                return result;
            }
        }

        return CommonUtil.format(2000, "添加成功！");
    }

    //查询  单条指标
    @Override
    public String find(IndexMgmtBean indexMgmtBean) {

        //用户角色校验
        if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }

        Optional<IndexEntity> optionalIndexEntity = indexDao.findById(indexMgmtBean.getId());
        if (!optionalIndexEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        return CommonUtil.format(2000, getIndexEntityMap(optionalIndexEntity.get()));
    }

    // 查询指标列表
    @Override
    public String finds(IndexMgmtBean indexMgmtBean) {

        //用户角色校验
        if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }

        //获取参数
        String indexName = indexMgmtBean.getName();
        String indexStatus = indexMgmtBean.getIndexStatus();
        Integer parentId = indexMgmtBean.getParentId();

        Integer total = indexDao.findTotal_by_idList_indexName_indexStatus_parentId(null, indexName, indexStatus, parentId);
        if (total == 0) return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";

        List<IndexEntity> indexEntityList = indexDao.find_by_idList_indexName_indexStatus_parentId(null, indexName, indexStatus, parentId, indexMgmtBean.getOrder(), indexMgmtBean.getSort(), indexMgmtBean.getPageSize(), indexMgmtBean.getPageNumber());

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (IndexEntity indexEntity : indexEntityList) {
            resultList.add(getIndexEntityMap(indexEntity));
        }

        //返回值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", resultList);
        resultMap.put("total", total);

        return CommonUtil.format(2000, resultMap);
    }

    // 更新 指标
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(IndexMgmtBean indexMgmtBean) {

        //用户角色校验
        if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }

        //获取参数
        Integer indexId = indexMgmtBean.getId();
        String name = indexMgmtBean.getName();
        String indexStatus = indexMgmtBean.getIndexStatus();
        Integer indexUnitsId = indexMgmtBean.getUnitsId();
        Integer parentId = indexMgmtBean.getParentId();
        String description = indexMgmtBean.getDescription();
        String evaluationStandards = indexMgmtBean.getEvaluationStandards();
        List<Integer> groupIds = indexMgmtBean.getGroupId();
        Double grossScore = indexMgmtBean.getGrossScore();
        String nodeIds = "";

        //查询指标
        Optional<IndexEntity> optionalIndexEntity = indexDao.findById(indexMgmtBean.getId());
        if (!optionalIndexEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        IndexEntity indexEntity = optionalIndexEntity.get();


        //父级指标检验
        boolean nodeIdUpdateFlag = false;
        IndexEntity index = null;
        if (parentId != 0) {
            Optional<IndexEntity> parentIndexEntity = indexDao.findById(parentId);
            if (!parentIndexEntity.isPresent()) {
                return CommonUtil.format(4200, "父级指标错误！");
            }
            index = parentIndexEntity.get();
            //如果父指标无效，则子指标也改为无效
            if ("2".equals(index.getIndexStatus())) {
                indexStatus = "2";
            }
            nodeIdUpdateFlag = true;
        }

        //查询指标单位
        Optional<IndexUnitsEntity> optionalIndexUnitsEntity = indexUnitsDao.findById(indexUnitsId);
        if (!optionalIndexUnitsEntity.isPresent()) {
            return CommonUtil.format(4200, "指标单位错误！");
        }

        //校验 指标名称是否存在
        List<IndexEntity> entities = indexDao.finds_by_ids_unitsId_indexName(null, null, name);
        if (entities != null && entities.size() > 0 && (entities.size() > 1 || !entities.get(0).getId().equals(indexId))) {
            return CommonUtil.format(4200, "指标名称已存在！");
        }

        //修改指标的nodeIds
        if (nodeIdUpdateFlag) {
            nodeIds = index.getNodeIds() + "," + indexEntity.getId();
        } else {
            nodeIds = indexEntity.getId().toString();
        }
        indexEntity.setNodeIds(nodeIds);

        // 如果父指标状态设为无效，该指标下的所有指标设为无效
        if (!indexEntity.getIndexStatus().equals(indexStatus) && "2".equals(indexStatus)) {
            indexDao.updateIndexStatus_by_nodeIds(indexEntity.getNodeIds(), indexStatus);
            indexEntity.setIndexStatus(indexStatus);
        }

        //修改值
        indexEntity.setIndexUnitsEntity(optionalIndexUnitsEntity.get());
        indexEntity.setName(name);
        indexEntity.setDescription(description);
        indexEntity.setEvaluationStandards(evaluationStandards);
        indexEntity.setIndexStatus(indexStatus);
        indexEntity.setGrossScore(grossScore);
        indexEntity.setUpdateTime(new Date());

        //实例化
        indexDao.save(indexEntity);

        //无效指标 对应的数据设置为无效
        if ("2".equals(indexEntity.getIndexStatus())) {
            indexDataDao.updateIndexDataStatusBy_indexNodeIds("3", indexEntity.getNodeIds());
        }

        //指标分组
        List<IndexGroupEntity> indexGroupEntities = null;
        if (groupIds != null) {
            indexGroupEntities = indexGroupDao.findByIdIn(groupIds);
            if (indexGroupEntities == null || indexGroupEntities.size() != groupIds.size()) {
                return CommonUtil.format(4200, "指标分组错误！");
            }
        }

        //清除该指标原来所属的分组
        indexGroupDao.deleteIndexGroupIndexBy_indexId(indexEntity.getId());

        //新增分组
        if (groupIds != null) {
            //添加指标_分组
            if (indexGroupEntities != null) {
                String result = saveIndex_group(indexEntity, indexGroupEntities);
                if (!"SUCCESS".equals(result)) {
                    return result;
                }
            }
        }

        return CommonUtil.format(2000, "修改成功！");
    }

    // 指标的combox
    @Override
    public String combox(IndexMgmtBean indexMgmtBean) {

        //用户角色校验
        if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }

        String indexStatus = indexMgmtBean.getIndexStatus();
        Integer parentId = indexMgmtBean.getParentId();

        List<IndexEntity> indexEntityList = null;
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (indexStatus == null) {
            indexEntityList = indexDao.findAllByParentId(parentId);
        } else {
            indexEntityList = indexDao.findAllByParentIdAndIndexStatus(parentId, indexStatus);
        }
        for (IndexEntity indexEntity : indexEntityList) {
            resultList.add(getIndexEntityMap(indexEntity));
        }
        return CommonUtil.format(2000, resultList);
    }

    // 查询所有指标
    @Override
    public String findALlIndex(IndexMgmtBean indexMgmtBean) {

        //用户角色校验
        /*if (!isAdministrator()) {
            return CommonUtil.format(4200, "无权操作！");
        }*/

        List<Map<String, Object>> resultList = new ArrayList<>();
        List<IndexEntity> indexEntityList = indexDao.findAllIndexs();
        for (IndexEntity indexEntity : indexEntityList) {
            resultList.add(getIndexEntityMap(indexEntity));
        }
        return CommonUtil.format(2000, resultList);
    }

    @Override
    public String findIndex_by_cityId(IndexMgmtBean indexMgmtBean) {
        return null;
    }

    @Override
    public String exportExcelIndex(IndexMgmtBean indexMgmtBean, HttpServletResponse response) {
        List<IndexEntity> indexList = indexDao.find_by_idList_indexName_indexStatus_parentId(null, null, "1", null, null, null, 999999, 1);
        if (indexList == null || indexList.size() == 0) {
            return CommonUtil.format(4200, "查无数据！");
        }

        List<String> titleList = Arrays.asList("指标名称", "指标单位", "评分标准", "指标描述");
        List<List<String>> dataList = new ArrayList<>();
        for (IndexEntity indexEntity : indexList) {
            IndexUnitsEntity indexUnitsEntity = indexEntity.getIndexUnitsEntity();
            List<String> list = new ArrayList<>();
            list.add(indexEntity.getName());
            list.add(indexUnitsEntity.getUnitsName());
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

    @Transactional
    @Override
    public String delete(IndexMgmtBean indexMgmtBean) {
        //特殊的删除规则
        List<IndexEntity> byIdOrodeIdorparentid = indexDao.findByIdOrodeIdorparentid(indexMgmtBean.getId());

         if(CommonUtil.isEmptyList(byIdOrodeIdorparentid)) {
             for (IndexEntity indexEntity : byIdOrodeIdorparentid) {
                 //根据指标id,删除指标数据(indexdata)
                 List<IndexCityEntity> indexCityEntities = null;
                 indexCityEntities = indexDao.findIndexCity_by_cityId_cityStatus_indexId_indexStatus(null, null, indexEntity.getId(), null);
                 if (CommonUtil.isEmptyList(indexCityEntities)) {
                     for (IndexCityEntity indexCityEntity : indexCityEntities) {
                         indexDataDao.delete_by_indexcityid(indexCityEntity);
                         //根据id,删除指标城市数据(indexcity)
                         IndexCityDao.delete(indexCityEntity);
                     }
                 }
                 //根据指标id,删除指标分组数据(indexgroup_index)
                 indexGroupDao.deleteIndexGroupIndexBy_indexId(indexEntity.getId());

                 //根据id，删除指标体系数据(index)
                 indexDao.delete(indexEntity);

             }
         }
        return CommonUtil.format(2000, "删除体系成功！");
    }

    // 获取指标的相关信息
    private Map<String, Object> getIndexEntityMap(IndexEntity indexEntity) {
        Map<String, Object> resultMap = new HashMap<>();
        if (indexEntity != null) {
            resultMap.put("unitsName", indexEntity.getIndexUnitsEntity().getUnitsName());
            resultMap.put("unitsId", indexEntity.getIndexUnitsEntity().getId());
            resultMap.put("name", indexEntity.getName());
            resultMap.put("indexStatus", indexEntity.getIndexStatus());
            resultMap.put("id", indexEntity.getId());
            resultMap.put("parentId", indexEntity.getParentId());
            resultMap.put("nodeIds", indexEntity.getNodeIds());
            resultMap.put("description", indexEntity.getDescription());
            resultMap.put("evaluationStandards", indexEntity.getEvaluationStandards());
            resultMap.put("grossScore", indexEntity.getGrossScore());

            //查询该指标的所有 指标分类
            List<IndexGroupEntity> indexGroupEntities = indexGroupDao.findIndexGroupsByIndexId(indexEntity.getId());
            List<Map<String, Object>> indexGroupList = new ArrayList<>();
            if (indexGroupEntities != null) {
                for (IndexGroupEntity indexGroupEntity : indexGroupEntities) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("groupId", indexGroupEntity.getId());
                    map.put("groupName", indexGroupEntity.getName());
                    indexGroupList.add(map);
                }
            }
            resultMap.put("indexGroups", indexGroupList);

            //查询当前指标的父级
            Optional<IndexEntity> optionalIndexEntity = indexDao.findById(indexEntity.getParentId());
            if (optionalIndexEntity.isPresent()) {
                resultMap.put("parentName", optionalIndexEntity.get().getName());
            }
        }
        return resultMap;
    }


    //添加指标_地区 或 园所
    @Transactional(rollbackFor = Exception.class)
    public String saveIndexCityOrDepartment(IndexEntity indexEntity) throws Exception {

        IndexCityEntity indexCityEntity = null;
        List<IndexCityEntity> datas = new ArrayList<>();

        //获取地区 实例
        List<CityEntity> cityEntityList = cityDao.findByCityStatus("1");
        if (cityEntityList == null || cityEntityList.size() == 0) {
            throw new Exception("请先添加市、区县数据！");
        }

        //添加指标与地区的中间表
        for (CityEntity cityEntity : cityEntityList) {
            List<IndexCityEntity> list = indexDao.findIndexCity_by_cityId_cityStatus_indexId_indexStatus(cityEntity.getId(), null, indexEntity.getId(), null);
            if (list != null && list.size() > 0) {
                throw new Exception(" 在<" + cityEntity.getCityName() + ">这个区域中存在重复指标！");
            }
            indexCityEntity = new IndexCityEntity();
            indexCityEntity.setCityEntity(cityEntity);
            indexCityEntity.setIndexEntity(indexEntity);
            indexCityEntity.setSaveTime(new Date());
            indexDao.saveIndexCity(indexCityEntity);
            datas.add(indexCityEntity);
        }

        //自动创建 指标当前年份的数据记录
       /* String year = new SimpleDateFormat("yyyy").format(new Date());
        for (IndexCityEntity indexCityEntity1 : datas) {
            List indexdataList = indexDataDao.findIndexDatas_indexCityIds_years_indexDataStatus_departmentIds_ids(Arrays.asList(indexCityEntity.getId()), Arrays.asList(year), null, null, null);
            if (indexdataList == null || indexdataList.size() == 0) {
                //为新指标创建指标数据
                IndexDataEntity indexDataEntity = new IndexDataEntity();

                indexDataEntity.setIndexCityEntity(indexCityEntity1);
                indexDataEntity.setIndexDataStatus("2");
                indexDataEntity.setYear(year);
                indexDataEntity.setSaveTime(new Date());
                indexDataDao.save(indexDataEntity);
            }
        }*/
        return "SUCCESS";
    }

    //添加指标_分组
    @Transactional(rollbackFor = Exception.class)
    public String saveIndex_group(IndexEntity indexEntity, List<IndexGroupEntity> indexGroupEntitys) {

        for (IndexGroupEntity indexGroupEntity : indexGroupEntitys) {
            IndexGroupIndexEntity indexGroupIndexEntity = new IndexGroupIndexEntity();
            indexGroupIndexEntity.setIndexEntity(indexEntity);
            indexGroupIndexEntity.setIndexGroupEntity(indexGroupEntity);
            indexGroupIndexEntity.setSaveTime(new Date());
            indexGroupDao.saveIndexGroupIndex(indexGroupIndexEntity);
        }
        return "SUCCESS";
    }

    //是否是超级管理员
    private Boolean isAdministrator() {
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null) return false;
        //如果是超级管理员
        if ("1".equals(staffEntity.getAdminFlag())) {
            return true;
        }
        return false;
    }

    //是否是管理员
    private Boolean isAdmin() {
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null) return false;
        //如果是超级管理员
        if ("admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return true;
        }
        return false;
    }
}
