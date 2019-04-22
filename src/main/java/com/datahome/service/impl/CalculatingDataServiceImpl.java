package com.datahome.service.impl;

import com.datahome.service.CalculatingDataService;
import com.datahome.util.CommonUtil;
import com.datahome.util.JDBCUtil;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2019/1/3 14:46
 */
@Service
public class CalculatingDataServiceImpl implements CalculatingDataService {

    private NumberFormat numberFormat = NumberFormat.getInstance();

    @Override
    public String startCalculate() {
       /* Connection connection1 = null;
        Connection connection2 = null;
        PreparedStatement pre = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try {
            //创建映射
            saveAgency();

            connection2 = JDBCUtil.getSqlConnection();
            connection1 = JDBCUtil.getSqlConnection("jdbc:sqlserver://192.168.1.225:1433;DatabaseName=age06V3_demo", "demo", "Demo123123", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

            List<Map<String, Object>> datas = new ArrayList<>();

            resultSet2 = connection2.prepareStatement(" select id, cityname from age06_city  where levelcode ='2' ").executeQuery();
            while (resultSet2.next()) {
                String cityname = resultSet2.getString("cityname");
                //老师总数
                PreparedStatement preparedStatement = connection1.prepareStatement("select count(*) as count from gdn_Garden a LEFT JOIN gdn_Teacher t on t.GardenId = a.id GROUP BY a.baseinfo_name HAVING a.baseinfo_name =?");
                preparedStatement.setString(1, cityname);
                resultSet1 = preparedStatement.executeQuery();
                Integer countTeacher = 0;
                while (resultSet1.next()) {
                    countTeacher = resultSet1.getInt("count");
                }

                //学生总数
                preparedStatement = connection1.prepareStatement("select count(*) as count from gdn_Garden a LEFT JOIN gdn_Child t on t.GardenId = a.id GROUP BY a.baseinfo_name HAVING a.baseinfo_name =?");
                preparedStatement.setString(1, cityname);
                resultSet1 = preparedStatement.executeQuery();
                Integer countStudent = 0;
                while (resultSet1.next()) {
                    countStudent = resultSet1.getInt("count");
                }

                if (countStudent == 0) {
                    continue;
                }
                numberFormat.setMaximumFractionDigits(2);
                String value = numberFormat.format((float) countTeacher / (float) countStudent * 100);
                Map<String, Object> map = new HashMap<>();
                map.put("cityId", resultSet2.getInt("id"));
                map.put("cityName", resultSet2.getString("cityname"));
                map.put("value", value);
                map.put("indexId", 116);
                datas.add(map);
            }

            for (Map<String, Object> map : datas) {
                String sql2 = "update age06_indexdata set  value=? where indexcityid in (select id from age06_index_city where indexId=? and cityId=? ) and year='2018'";
                PreparedStatement preparedStatement = connection2.prepareStatement(sql2);
                String value = map.get("value").toString();
                System.out.println(map.get("cityName") + " 的师生比是:" + value);

                preparedStatement.setDouble(1, Double.valueOf(value));
                preparedStatement.setInt(2, Integer.valueOf(map.get("indexId").toString()));
                preparedStatement.setInt(3, Integer.valueOf(map.get("cityId").toString()));
                preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(connection1, pre, resultSet1);
                JDBCUtil.closeConnection(connection2, null, resultSet2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        prepareCall();
        return CommonUtil.format(2000, "计算完毕！");
    }

    public void saveAgency() {
        Connection connection1 = null;
        Connection connection2 = null;
        PreparedStatement pre = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try {
            connection1 = JDBCUtil.getSqlConnection("jdbc:sqlserver://192.168.1.225:1433;DatabaseName=age06V3_demo", "demo", "Demo123123", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection2 = JDBCUtil.getSqlConnection();

            ResultSet resultSet = connection2.prepareStatement("select count(*) as count  from age06_agency").executeQuery();
            if (resultSet.next()) {
                Integer count = resultSet.getInt("count");
                if (count != 0) {
                    return;
                }
            }

            //gdn_Agency 和 IndexCity 做映射
            String sql1 = " select id ,baseinfo_name from gdn_agency  ";
            resultSet1 = connection1.prepareStatement(sql1).executeQuery();

            List<Map<String, Object>> datas = new ArrayList<>();

            while (resultSet1.next()) {
                String gdn_id = resultSet1.getString(1);

                //本地数据
                String baseinfo_name = resultSet1.getString(2);
                pre = connection2.prepareStatement("select id from age06_city where cityName =? ");
                pre.setString(1, baseinfo_name);
                resultSet2 = pre.executeQuery();
                if (resultSet2.getRow() > 1) continue;
                while (resultSet2.next()) {
                    Integer local_id = resultSet2.getInt("id");
                    Map<String, Object> map = new HashMap<>();
                    map.put("local_id", local_id);
                    map.put("gdn_id", gdn_id);
                    datas.add(map);
                }
            }
            //添加映射
            for (Map<String, Object> map : datas) {
                Integer local_id = (Integer) map.get("local_id");
                String gdn_id = (String) map.get("gdn_id");

                String sql = "insert into age06_agency(gdn_agencyId,age06_cityId) values(?,?)";
                PreparedStatement preparedStatement = connection2.prepareStatement(sql);
                preparedStatement.setString(1, gdn_id);
                preparedStatement.setInt(2, local_id);
                preparedStatement.execute();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(connection1, pre, resultSet1);
                JDBCUtil.closeConnection(connection2, null, resultSet2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareCall() {
        Connection connection = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;

        try {
            String sql = " {CALL totalRecords(?,?)}";
            connection = JDBCUtil.getSqlConnection();
            CallableStatement st = connection.prepareCall(sql);
            st.setString(1, "");
            st.setString(2, "user01");
            st.execute();

            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                System.out.println("value:" + rs.getString(1));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.closeConnection(connection, pre, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
