package com.datahome.dao.impl;

import com.datahome.bean.GdnClassMgmtBean;
import com.datahome.dao.GdnClassDao;
import com.datahome.entity.GdnClassEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnClassUtil;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GdnClassDaoImpl implements GdnClassDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnClassEntity> findby_name_district_Level(GdnClassMgmtBean gdnClassMgmtBean) {
        String sql = "  select * from age06_gdn_class i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnClassUtil.findGdnAgencySql(sql,gdnClassMgmtBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnClassMgmtBean.getSorts());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnClassEntity.class);

        //分页
        CommonUtil.page(query, gdnClassMgmtBean.getPageNumber(), gdnClassMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
