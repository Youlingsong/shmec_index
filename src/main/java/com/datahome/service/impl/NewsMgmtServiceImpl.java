package com.datahome.service.impl;

import com.datahome.bean.NewsMgmtBean;
import com.datahome.entity.NewsClassesEntity;
import com.datahome.entity.NewsEntity;
import com.datahome.repository.NewsRepository;
import com.datahome.service.NewsMgmtService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "newsMgmtServiceImpl")
public class NewsMgmtServiceImpl implements NewsMgmtService {

    @Resource
    private NewsRepository newsDao;

    //添加新闻
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String save(NewsMgmtBean nb) {

        NewsClassesEntity newsClassesEntity = newsDao.findNewsClasses_by_id(nb.getClassesId());
        if (newsClassesEntity == null) return CommonUtil.format(4200, "新闻分类不存在!");

        //持久化操作
        NewsEntity newsEntity = new NewsEntity();
        CommonUtil.copy(nb, newsEntity);
        newsEntity.setNewsClassesEntity(newsClassesEntity);
        newsEntity.setSaveTime(new Date());
        newsDao.save(newsEntity);

        return CommonUtil.format(2000, "新闻添加成功！");
    }

    //修改新闻
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String updates(NewsMgmtBean nb) {

        Optional<NewsEntity> optionalNewsEntity = newsDao.findById(nb.getId());
        if (!optionalNewsEntity.isPresent()) return CommonUtil.format(4200, "未查询到相关信息！");

        NewsEntity newsEntity = optionalNewsEntity.get();
        //属性值复制
        CommonUtil.copy(nb, newsEntity);
        if (nb.getClassesId() != newsEntity.getNewsClassesEntity().getId()) {
            NewsClassesEntity news = newsDao.findNewsClasses_by_id(nb.getClassesId());
            if (news == null) return CommonUtil.format(4200, "新闻分类错误！");
            newsEntity.setNewsClassesEntity(news);
        }
        
        newsEntity.setUpdateTime(new Date());
        newsDao.save(newsEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    //删除新闻
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String delete(NewsMgmtBean nb) {
        Optional<NewsEntity> optionalNewsEntity = newsDao.findById(nb.getId());
        if (!optionalNewsEntity.isPresent()) return CommonUtil.format(4200, "未查询到相关信息！");
        newsDao.delete(optionalNewsEntity.get());
        return CommonUtil.format(2000, "删除成功！");
    }


    //查询新闻列表
    public String finds(NewsMgmtBean nb) {

        Integer classesId = nb.getClassesId();
        String publishFlag = nb.getPublishFlag();
        String title = nb.getTitle();
        String status = nb.getStatus();

        List<Integer> classIds = null;
        if (classesId != null) {
            classIds = new ArrayList<>();
            classIds.add(classesId);
        }

        Integer total = newsDao.findTotal_by_classesId_publishFlag_title_status(classIds, publishFlag, title, status);
        if (total == 0) return "{\"status\":2000,\"message\":[]}";

        List<Map<String, Object>> resultList = new ArrayList<>();
        List<NewsEntity> list = newsDao.find_by_classesId_publishFlag_title_status(classIds, publishFlag, title, status, nb.getSort(), nb.getOrder(), nb.getPageSize(), nb.getPageNumber());
        for (NewsEntity newsEntity : list) {
            Map<String, Object> map = CommonUtil.objectToMap(newsEntity, "newsClassesEntity", "content");
            map.put("classesId", newsEntity.getNewsClassesEntity().getId());
            map.put("classes", newsEntity.getNewsClassesEntity().getClasses());
            resultList.add(map);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("rows", resultList);
        return CommonUtil.format(2000, resultMap);
    }

    //查询单条新闻信息
    @Override
    public String find(NewsMgmtBean nb) {
        Optional<NewsEntity> optionalNewsEntity = newsDao.findById(nb.getId());
        if (!optionalNewsEntity.isPresent()) return CommonUtil.format(4200, "查无数据！");

        NewsEntity newsEntity = optionalNewsEntity.get();
        Map<String, Object> resultMap = CommonUtil.objectToMap(newsEntity, "newsClassesEntity");
        resultMap.put("classesId", newsEntity.getNewsClassesEntity().getId());
        resultMap.put("classes", newsEntity.getNewsClassesEntity().getClasses());
        return CommonUtil.format(2000, resultMap);
    }

    //新增新闻分类
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String saveNewsClasses(NewsMgmtBean nb) {
        Integer parentId = nb.getParentId();
        NewsClassesEntity parentClassesEntity = null;
        if (parentId != 0) {
            parentClassesEntity = newsDao.findNewsClasses_by_id(parentId);
            if (parentClassesEntity == null) {
                return CommonUtil.format(4200, "父级分类错误！");
            }
        }
        NewsClassesEntity newsClassesEntity = new NewsClassesEntity();
        newsClassesEntity.setParentId(parentId);
        newsClassesEntity.setClasses(nb.getClasses());
        newsClassesEntity.setSaveTime(new Date());
        newsDao.saveNewsClasses(newsClassesEntity);

        String nodeId = "";
        if (parentId == 0) {
            nodeId = newsClassesEntity.getId().toString();
        } else {
            nodeId = parentClassesEntity.getNodeIds() + "," + newsClassesEntity.getId().toString();
        }
        newsClassesEntity.setNodeIds(nodeId);
        newsDao.saveNewsClasses(newsClassesEntity);

        return CommonUtil.format(2000, "添加成功！");
    }

    //查询新闻分类列表
    @Override
    public String findClassesList(NewsMgmtBean nb) {
        List<NewsClassesEntity> list = newsDao.findNewsClasses_by_parentId(null, null);
        return CommonUtil.format(2000, getNewsResultList(list));
    }

    //查询单个新闻分类
    @Override
    public String findNewsClasses(NewsMgmtBean nb) {
        NewsClassesEntity newsClassesEntity = newsDao.findNewsClasses_by_id(nb.getClassesId());
        if (newsClassesEntity == null) return CommonUtil.format(4200, "查无数据！");
        Map<String, Object> map = new HashMap<>();
        map.put("classesId", newsClassesEntity.getId());
        map.put("classes", newsClassesEntity.getClasses());
        map.put("parentId", newsClassesEntity.getParentId());
        return CommonUtil.format(2000, map);
    }

    //修改新闻分类
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String updateNewsClasses(NewsMgmtBean nb) {
        Integer parentId = nb.getParentId();

        NewsClassesEntity newsClassesEntity = newsDao.findNewsClasses_by_id(nb.getClassesId());
        if (newsClassesEntity == null) return CommonUtil.format(4200, "查无数据！");

        NewsClassesEntity parentClassesEntity = null;
        if (parentId != 0) {
            parentClassesEntity = newsDao.findNewsClasses_by_id(parentId);
            if (parentClassesEntity == null) {
                return CommonUtil.format(4200, "父级分类错误！");
            }
        }
        newsClassesEntity.setParentId(parentId);
        newsClassesEntity.setClasses(nb.getClasses());
        newsDao.updateNewsClasses(newsClassesEntity);

        String nodeId = "";
        if (parentId == 0) {
            nodeId = newsClassesEntity.getId().toString();
        } else {
            nodeId = parentClassesEntity.getNodeIds() + "," + newsClassesEntity.getId().toString();
        }
        newsClassesEntity.setNodeIds(nodeId);
        newsDao.updateNewsClasses(newsClassesEntity);

        return CommonUtil.format(2000, "修改成功！");
    }

    //删除新闻分类
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String deleteNewsClasses(NewsMgmtBean nb) {

        NewsClassesEntity newsClassesEntity = newsDao.findNewsClasses_by_id(nb.getClassesId());
        if (newsClassesEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }

        //删除该分类下的数据
        newsDao.deleteNewsBy_nodeIds(newsClassesEntity.getNodeIds());

        //删除分类
        newsDao.deleteNewsClasses(newsClassesEntity);

        return CommonUtil.format(2000, "删除成功！");
    }

    private List<Map<String, Object>> getNewsResultList(List<NewsClassesEntity> list) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (NewsClassesEntity classes : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("classesId", classes.getId());
                map.put("classes", classes.getClasses());
                map.put("parentId", classes.getParentId());
                resultList.add(map);
            }
        }
        return resultList;
    }
}
