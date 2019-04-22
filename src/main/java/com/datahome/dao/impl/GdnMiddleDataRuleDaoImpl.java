package com.datahome.dao.impl;

import com.datahome.dao.GdnMiddleDataRuleDao;
import com.datahome.middledata.GdnMiddleDataModel;
import com.datahome.util.CommonUtil;
import com.datahome.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GdnMiddleDataRuleDaoImpl implements GdnMiddleDataRuleDao {
    @Resource
    private EntityManager entityManager;
    String key="";
    Integer value=0;
    String remake="";
    @Override
    public List<GdnMiddleDataModel> dealsql(String sqlsentence) {
        Connection sqlConnection = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        ArrayList<GdnMiddleDataModel> gdnMiddleDataModels = new ArrayList<>();
        try {
            sqlConnection= JDBCUtil.getSqlConnection();
            pre = sqlConnection.prepareStatement(sqlsentence);
            resultSet = pre.executeQuery();
            while (resultSet != null && resultSet.next()) {
                 value = resultSet.getInt("value");
                 key = resultSet.getString("key");
                 remake=resultSet.getString("remake");
                GdnMiddleDataModel gdnMiddleDataModel = new GdnMiddleDataModel();
                gdnMiddleDataModel.setKey(key);
                gdnMiddleDataModel.setValue(value);
                gdnMiddleDataModel.setRemake(remake);
                gdnMiddleDataModels.add(gdnMiddleDataModel);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            CommonUtil.close(sqlConnection, pre, resultSet);
        }
        return gdnMiddleDataModels;
    }
}
