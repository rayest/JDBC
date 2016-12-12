package cn.rayest.statement;

import cn.rayest.model.Student;
import cn.rayest.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/6/27 0027.
 */
public class UpdateApplication {

    private static DatabaseService databaseService = new DatabaseService();

    public static int update(Student student) throws Exception {
        Connection connection = databaseService.getConnection();
        Statement statement = connection.createStatement();
        String sql = "UPDATE t_student SET name = '" + student.getName() + "', age =" + student.getAge() + " WHERE id = " + student.getId();
        int result = statement.executeUpdate(sql);
        databaseService.close(statement, connection);
        return result;
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student(12, "lee", 18);
        int result = update(student);
        if (result == 1) {
            System.out.println("添加成功！");
        }
        System.out.println("添加失败！");
    }
}
