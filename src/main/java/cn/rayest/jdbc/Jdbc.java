package cn.rayest.jdbc;

import cn.rayest.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayest on 2016/12/12 0012.
 */
public class Jdbc {
    private DatabaseService databaseService = new DatabaseService();

    public int create(Student student) throws Exception {
        Connection connection = databaseService.getConnection();
        String sql = "INSERT INTO t_student (name, age) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getAge());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int deleteById(int id) throws Exception {
        Connection connection = databaseService.getConnection();
        String sql = "DELETE FROM t_student WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public List<Student> load() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection connection = databaseService.getConnection();
        String sql = "SELECT * FROM t_student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            Student student = new Student(id, name, age);
            studentList.add(student);
        }
        return studentList;
    }

    public int update(Student student) throws Exception {
        Connection connection = databaseService.getConnection();
        String sql = "UPDATE t_student SET name = ?, age = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getAge());
        preparedStatement.setInt(3, student.getId());
        int result = preparedStatement.executeUpdate();
        return result;
    }
}
