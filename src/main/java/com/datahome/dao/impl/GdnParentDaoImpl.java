package com.datahome.dao.impl;

import com.datahome.bean.GdnParentMgmtBean;
import com.datahome.dao.GdnParentDao;
import com.datahome.entity.GdnParentEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnParentUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnParentDaoImpl implements GdnParentDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnParentEntity> findby_name_district_Level(GdnParentMgmtBean gdnParentMgmtBean) {
        String sql = "  select * from age06_gdn_parent i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnParentUtil.findGdnAgencySql(sql,gdnParentMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnParentMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnParentEntity.class);

        //分页
        CommonUtil.page(query, gdnParentMgmtBean.getPageNumber(), gdnParentMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }

}
