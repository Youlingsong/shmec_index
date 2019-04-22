package com.datahome.dao.impl;

import com.datahome.bean.GdnTeacherTrainMgmtBean;
import com.datahome.dao.GdnTeacherTrainDao;
import com.datahome.entity.GdnTeacherTrainEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnTeacherTrainUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnTeacherTrainDaoImpl implements GdnTeacherTrainDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnTeacherTrainEntity> findby_name_district_Level(GdnTeacherTrainMgmtBean gdnTeacherTrainMgmtBean) {
        String sql = "  select * from age06_gdn_teachertrain i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnTeacherTrainUtil.findGdnAgencySql(sql,gdnTeacherTrainMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnTeacherTrainMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnTeacherTrainEntity.class);

        //分页
        CommonUtil.page(query, gdnTeacherTrainMgmtBean.getPageNumber(), gdnTeacherTrainMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
