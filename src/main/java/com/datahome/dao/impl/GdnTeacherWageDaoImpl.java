package com.datahome.dao.impl;

import com.datahome.bean.GdnTeacherWageMgmtBean;
import com.datahome.dao.GdnTeacherWageDao;
import com.datahome.entity.GdnTeacherWageEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnTeacherWageUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnTeacherWageDaoImpl implements GdnTeacherWageDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnTeacherWageEntity> findby_name_district_Level(GdnTeacherWageMgmtBean gdnTeacherWageMgmtBean) {
        String sql = "  select * from age06_gdn_teacherwage i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnTeacherWageUtil.findGdnAgencySql(sql,gdnTeacherWageMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnTeacherWageMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnTeacherWageEntity.class);

        //分页
        CommonUtil.page(query, gdnTeacherWageMgmtBean.getPageNumber(), gdnTeacherWageMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
