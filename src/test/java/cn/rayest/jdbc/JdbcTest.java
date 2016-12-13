package cn.rayest.jdbc;

import cn.rayest.model.Student;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rayest on 2016/12/12 0012.
 */
public class JdbcTest {

    @Test
    public void testCreate() throws Exception {
        Jdbc jdbc = new Jdbc();
        Student student = new Student(8, "black", 50);
        int result = jdbc.create(student);
        assertEquals(1, result);
    }

    @Test
    public void testDelete() throws Exception {
        Jdbc jdbc = new Jdbc();
        int result = jdbc.deleteById(1);
        assertEquals(1, result);
    }

    @Test
    public void testLoad() throws Exception {
        Jdbc jdbc = new Jdbc();
        List<Student> studentList = jdbc.load();
        int result = studentList.size();
        assertEquals(7, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Jdbc jdbc = new Jdbc();
        Student student = new Student(8, "white", 30);
        int result = jdbc.update(student);
        assertEquals(1, result);
    }

}
