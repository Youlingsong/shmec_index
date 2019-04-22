package com.datahome.dao.impl;

import com.datahome.bean.GdnGardenMgmtBean;
import com.datahome.dao.GdnGardenDao;
import com.datahome.entity.GdnGardenEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnGardenUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnGardenDaoImpl implements GdnGardenDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnGardenEntity> findby_name_district_Level(GdnGardenMgmtBean gdnGardenMgmtBean) {
        String sql = "  select * from age06_gdn_garden i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnGardenUtil.findGdnAgencySql(sql,gdnGardenMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnGardenMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnGardenEntity.class);

        //分页
        CommonUtil.page(query, gdnGardenMgmtBean.getPageNumber(), gdnGardenMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
