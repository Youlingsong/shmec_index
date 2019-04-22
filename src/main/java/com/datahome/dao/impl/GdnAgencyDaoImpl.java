package com.datahome.dao.impl;

import com.datahome.bean.GdnAgencyMgmtBean;
import com.datahome.dao.GdnAgencyDao;
import com.datahome.entity.GdnAgencyEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnAgencyUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnAgencyDaoImpl implements GdnAgencyDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnAgencyEntity> findby_name_district_Level(GdnAgencyMgmtBean gdnAgencyMgmtBean) {
        String sql = "  select * from age06_gdn_agency i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnAgencyUtil.findGdnAgencySql(sql,gdnAgencyMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnAgencyMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnAgencyEntity.class);

        //分页
        CommonUtil.page(query, gdnAgencyMgmtBean.getPageNumber(), gdnAgencyMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
