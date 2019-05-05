package com.datahome.dao.impl;

import com.datahome.bean.GdnMiddleDataBean;
import com.datahome.dao.GdnMiddleDataDao;
import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.util.CommonUtil;
import com.datahome.util.GdnMiddleDataUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GdnMiddleDataDaoImpl  implements GdnMiddleDataDao {
    @Resource
    private EntityManager entityManager;

    @Override
    public List<GdnMiddleDataEntity> finds(GdnMiddleDataBean gdnMiddleDataBean) {
        String sql = "  select * from age06_gdn_middledata i where  1=1   ";
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        sql = GdnMiddleDataUtil.findGdnAgencySql(sql,gdnMiddleDataBean , params);
        //排序
        sql = CommonUtil.orderCreate(sql, "ASC", gdnMiddleDataBean.getSort());
        //Todo 记得修改实体类型
        Query query = entityManager.createNativeQuery(sql, GdnMiddleDataEntity.class);

        //分页
        CommonUtil.page(query, gdnMiddleDataBean.getPageNumber(), gdnMiddleDataBean.getPageSize());

        //占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            query.setParameter(key, value);
        }
        return query.getResultList();
    }

    @Override
    public void deleteby_ruleid(String ruleid) {
            String sql = " delete from age06_gdn_middledata where 1=1 and middledataruleid = :middledataruleid";
            entityManager.createNativeQuery(sql).setParameter("middledataruleid", ruleid).executeUpdate();
    }


}
