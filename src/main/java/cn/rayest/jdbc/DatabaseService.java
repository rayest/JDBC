package cn.rayest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/6/26 0026.
 */
public class DatabaseService {

    private static String url = "jdbc:mysql://localhost:3306/db";
    private static String userName = "root";
    private static String password = "199011081108";
    private static String driverClass = "com.mysql.jdbc.Driver";

    public Connection getConnection() throws Exception {
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    public void close(Statement statement, Connection connection) throws Exception {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
