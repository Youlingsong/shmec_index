package com.datahome.util;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/17 15:14
 */
@Component
public class TopicUtil {

    public String findTopicSql(String sql, String title, String publishFlag, String status, Integer priority, Integer classesId, Map<String, Object> params) {
        if (title != null) {
            sql += " and title like :title ";
            params.put("title", "%" + title + "%");
        }
        if (publishFlag != null) {
            sql += " and publishFlag = :publishFlag ";
            params.put("publishFlag", publishFlag);
        }
        if (status != null) {
            sql += " and status = :status ";
            params.put("status", status);
        }
        if (priority != null) {
            sql += " and  priority = :priority ";
            params.put("priority", priority);
        }
        if (classesId != null) {
            sql += " and  topicClassesEntity.id = :classesId ";
            params.put("classesId", classesId);
        }
        return sql;
    }

    public String findTopicClassesSql(String sql, String classes, Integer parentId, Integer classesId, String nodeids, Map<String, Object> params) {
        if (classes != null) {
            sql += " and classes = :classes ";
            params.put("classes", classes);
        }
        if (parentId != null) {
            sql += " and  parentId = :parentId ";
            params.put("parentId", parentId);
        }
        if (classesId != null) {
            sql += " and id = :id ";
            params.put("id", classesId);
        }
        if (nodeids != null) {
            sql += " and nodeids like :nodeids ";
            params.put("nodeids", nodeids + "%");
        }
        return sql;
    }
}
