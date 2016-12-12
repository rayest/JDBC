package cn.rayest.statement;

import cn.rayest.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/6/27 0027.
 */
public class DeleteApplication {

    private static DatabaseService databaseService = new DatabaseService();

    public static int delete(int id) throws Exception {
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM t_student" + " WHERE id = " + id;
        int result = statement.executeUpdate(sql);
        databaseService.close(statement, connection);
        return result;
    }

    public static void main(String[] args) throws Exception {
        int result = delete(12);
        if (result == 1) {
            System.out.println("删除成功！");
        }
        System.out.println("删除失败！");
    }
}
