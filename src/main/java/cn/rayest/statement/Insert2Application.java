package cn.rayest.statement;

import cn.rayest.model.Student;
import cn.rayest.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Rayest on 2016/6/26 0026.
 */
public class Insert2Application {

    private static DatabaseService databaseService = new DatabaseService();

    // 添加一个学生
    public static int addStudent(int id, Student student) throws Exception {
        Connection connection = databaseService.getConnection();
        String sql = "INSERT INTO t_student VALUES (" + id + ", '" + student.getName() + "', " + student.getAge() + " )";
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        System.out.println("操作结果：" + result);
        databaseService.close(statement, connection);
        return result;
    }

    public static void main(String[] args) throws Exception {
        int result = addStudent(15, new Student(12, "Paris Pay", 22));
        if (result == 1) {
            System.out.println("添加成功！");
        }
        System.out.println("添加失败！");
    }
}
