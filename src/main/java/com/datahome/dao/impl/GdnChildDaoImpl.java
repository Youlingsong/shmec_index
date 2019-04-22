package com.datahome.dao.impl;

import com.datahome.bean.GdnChildMgmtBean;
import com.datahome.dao.GdnChildDao;
import com.datahome.entity.GdnChildEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnChildUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnChildDaoImpl implements GdnChildDao {
    @Resource
    private EntityManager entityManager;
    @Override
    public List<GdnChildEntity> findby_name_district_Level(GdnChildMgmtBean gdnChildMgmtBean) {
        String sql = "  select * from age06_gdn_child i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnChildUtil.findGdnAgencySql(sql,gdnChildMgmtBean , params);
        //排序
        sql = CommonUtil.order(sql, "ASC", gdnChildMgmtBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnChildEntity.class);

        //分页
        CommonUtil.page(query, gdnChildMgmtBean.getPageNumber(), gdnChildMgmtBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }
}
