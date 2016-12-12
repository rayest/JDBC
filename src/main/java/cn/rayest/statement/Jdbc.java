package cn.rayest.statement;

import cn.rayest.jdbc.DatabaseService;
import cn.rayest.model.Student;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/12/12 0012.
 */
public class Jdbc {
    public void create() throws Exception {
        DatabaseService databaseService = new DatabaseService();
        String sql = "INSERT INTO t_student VALUES (null, 'Rayest Lee', 25)";
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public void insert(int id, Student student) throws Exception {
        DatabaseService databaseService = new DatabaseService();
        Connection connection = databaseService.getConnection();
        String sql = "INSERT INTO t_student VALUES (" + id + ", '" + student.getName() + "', " + student.getAge() + " )";
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        databaseService.close(statement, connection);
    }

    public void delete(int id) throws Exception {
        DatabaseService databaseService = new DatabaseService();
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM t_student" + " WHERE id = " + id;
        int result = statement.executeUpdate(sql);
        databaseService.close(statement, connection);
    }

    public static int update(Student student) throws Exception {
        DatabaseService databaseService = new DatabaseService();
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        String sql = "UPDATE t_student SET name = '" + student.getName() + "', age =" + student.getAge() + " WHERE id = " + student.getId();
        int result = statement.executeUpdate(sql);
        databaseService.close(statement, connection);
        return result;
    }
}
