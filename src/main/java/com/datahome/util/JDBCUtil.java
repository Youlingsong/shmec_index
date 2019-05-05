package com.datahome.util;

import java.sql.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/7/4 17:46
 */

public class JDBCUtil {

    static final String DRIVER = "org.postgresql.Driver";
    static final String url = "jdbc:postgresql://192.168.1.221/shmec_age06_gdnindex";
    static final String user = "root";
    static final String password = "datahome123";

    public static Connection getSqlConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getSqlConnection(String url) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(url, user, password);
    }


    public static Connection getSqlConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(url, user, password);
    }


    public static Connection getSqlConnection(String url, String user, String password, String driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
