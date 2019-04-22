package com.datahome.util;

import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/15 17:45
 */
@Component
public class NewsUtil {

    public String findHql(String hql, List<Integer> classesId, String publishFlag, String title, String status) {
        if (classesId != null && classesId.size() > 0) {
            hql += " and classesId in (:classesId) ";
        }
        if (publishFlag != null) {
            hql += " and publishFlag = :publishFlag ";
        }
        if (title != null) {
            hql += " and title like :title ";
        }
        if (status != null) {
            hql += "  and status = :status ";
        }
        return hql;
    }

    public void setQuery(Query query, List<Integer> classesId, String publishFlag, String title, String status) {
        if (classesId != null && classesId.size() > 0) {
            query.setParameter("classesId", classesId);
        }
        if (publishFlag != null) {
            query.setParameter("publishFlag", publishFlag);
        }
        if (title != null) {
            query.setParameter("title", "%" + title + "%");
        }
        if (status != null) {
            query.setParameter("status", status);
        }
    }
}
