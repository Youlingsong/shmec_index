package com.datahome.service.impl;

import com.datahome.bean.NewsBean;
import com.datahome.entity.NewsClassesEntity;
import com.datahome.entity.NewsEntity;
import com.datahome.repository.NewsRepository;
import com.datahome.service.NewsService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("newsServiceImpl")
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsRepository newsDao;


    //加载单条新闻信息
    @Transactional
    public String find(NewsBean newsBean) {
        //获取数据
        Optional<NewsEntity> optionalNewsEntity = newsDao.findById(newsBean.getId());
        if (optionalNewsEntity == null) return CommonUtil.format(4200, "未查询到相关信息！");
        NewsEntity newsEntity = optionalNewsEntity.get();
        Long hit = newsEntity.getHit();
        if (hit == null) hit = 0L;
        newsEntity.setHit(hit + 1L);
        newsDao.save(newsEntity);
        //返回值
        Map<String, Object> resultMap = CommonUtil.objectToMap(newsEntity, "newsClassesEntity", "saveTime");
        resultMap.put("classesId", newsEntity.getNewsClassesEntity().getId());
        resultMap.put("classes", newsEntity.getNewsClassesEntity().getClasses());
        return CommonUtil.format(2000, resultMap);
    }

    //查询新闻列表
    public String finds(NewsBean nb) {
        Integer classesId = nb.getClassesId();
        String publishFlag = nb.getPublishFlag();
        String title = nb.getTitle();
        List<Integer> classIds = null;
        if (classesId != null && !"".equals(classesId)) {
            classIds = new ArrayList<>();
            NewsClassesEntity newsClassesEntity = newsDao.findNewsClasses_by_id(classesId);
            if (newsClassesEntity != null && newsClassesEntity.getParentId() == 0) {
                List<Integer> childrenClassIds = newsDao.findAllChildrenClasses_by_nodeId(newsClassesEntity.getNodeIds());
                for (Integer classId : childrenClassIds) {
                    classIds.add(classId);
                }
            } else {
                classIds.add(classesId);
            }
        }


        Integer total = newsDao.findTotal_by_classesId_publishFlag_title_status(classIds, publishFlag, title, "1");
        if (total == 0) return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";

        List<NewsEntity> list = newsDao.find_by_classesId_publishFlag_title_status(classIds, publishFlag, title, "1", nb.getSort(), nb.getOrder(), nb.getPageSize(), nb.getPageNumber());
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (list == null || list.size() == 0) return "{\"status\":2000,\"message\":{\"total\":0,\"rows\":[]}}";

        for (NewsEntity newsEntity : list) {
            Map<String, Object> map = CommonUtil.objectToMap(newsEntity, "newsClassesEntity");
            map.put("classesId", newsEntity.getNewsClassesEntity().getId());
            map.put("classes", newsEntity.getNewsClassesEntity().getClasses());
            resultList.add(map);
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("total", total);
        resultMap.put("rows", resultList);
        return CommonUtil.format(2000, resultMap);
    }

    @Override
    public String findNewsClasses(NewsBean nb) {
        List<NewsClassesEntity> list = newsDao.findNewsClasses_by_parentId(null, null);
        return CommonUtil.format(2000, getNewsResultList(list));
    }

    private List<Map<String, Object>> getNewsResultList(List<NewsClassesEntity> list) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (NewsClassesEntity classes : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("classesId", classes.getId());
                map.put("classes", classes.getClasses());
                map.put("parentId", classes.getParentId());
                map.put("placeCode", classes.getPlaceCode());
                resultList.add(map);
            }
        }
        return resultList;
    }
}
