package cn.rayest.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Rayest on 2016/12/12 0012.
 */
public class DatabaseServiceTest {

    @Test
    public void testGetConnection() throws Exception {
        DatabaseService databaseService = new DatabaseService();
        assertNotNull(databaseService.getConnection());
    }

    @Test
    public void testDatabaseInformation() throws Exception {
        DatabaseService databaseService = new DatabaseService();
        Connection connection = databaseService.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        // 获取元数据
        assertEquals(databaseMetaData.getDriverMajorVersion(), 5);
        assertEquals(databaseMetaData.getDriverMinorVersion(), 1);
        assertEquals(databaseMetaData.getDatabaseProductName(), "MySQL");
        assertEquals(databaseMetaData.getUserName(), "root@localhost");
        assertEquals(databaseMetaData.getDriverName(), "MySQL Connector Java");
        assertEquals(databaseMetaData.getURL(), "jdbc:mysql://localhost:3306/db");
    }

    @Test
    public void testMetaTable() throws Exception {
        DatabaseService databaseService = new DatabaseService();
        Connection connection = databaseService.getConnection();
        String sql = "SELECT * FROM t_student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
        // 获取元数据列的总数
        int number = resultSetMetaData.getColumnCount();
        assertEquals(7, number);
        assertEquals(resultSetMetaData.getColumnName(1), "id");
        assertEquals(resultSetMetaData.getColumnName(2), "name");
        assertEquals(resultSetMetaData.getColumnName(3), "age");
        assertEquals(resultSetMetaData.getColumnName(4), "addressId");
        assertEquals(resultSetMetaData.getColumnName(5), "gradeId");
        assertEquals(resultSetMetaData.getColumnName(6), "pic");
        assertEquals(resultSetMetaData.getColumnName(7), "remark");
        assertEquals(resultSetMetaData.getColumnTypeName(1), "INT");
        assertEquals(resultSetMetaData.getColumnTypeName(2), "VARCHAR");
        assertEquals(resultSetMetaData.getColumnTypeName(3), "INT");
        assertEquals(resultSetMetaData.getColumnTypeName(4), "INT");
        assertEquals(resultSetMetaData.getColumnTypeName(5), "INT");
        assertEquals(resultSetMetaData.getColumnTypeName(6), "LONGBLOB");
        assertEquals(resultSetMetaData.getColumnTypeName(7), "VARCHAR");
    }


}
