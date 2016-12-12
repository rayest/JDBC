package cn.rayest.statement;

import cn.rayest.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/6/26 0026.
 */
public class InsertApplication {
    public static void main(String[] args) throws Exception {
        DatabaseService databaseService = new DatabaseService();
        String sql = "INSERT INTO t_student VALUES (null, 'Rayest Lee', 25)";
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        System.out.println("操作结果：" + result);
        statement.close();  // 关闭 statement
        connection.close(); // 关闭连接
    }
}
