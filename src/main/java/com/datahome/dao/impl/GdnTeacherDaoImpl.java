package com.datahome.dao.impl;

import com.datahome.bean.GdnTeacherMgmtBean;
import com.datahome.dao.GdnTeacherDao;
import com.datahome.entity.GdnTeacherEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnTeacherUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnTeacherDaoImpl implements GdnTeacherDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnTeacherEntity> findby_name_district_Level(GdnTeacherMgmtBean gdnTeacherMgmtBean) {
        String sql = "  select * from age06_gdn_teacher i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnTeacherUtil.findGdnAgencySql(sql,gdnTeacherMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnTeacherMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnTeacherEntity.class);

        //分页
        CommonUtil.page(query, gdnTeacherMgmtBean.getPageNumber(), gdnTeacherMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
