package cn.rayest.transaction;

import cn.rayest.jdbc.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rayest on 2016/6/27 0027.
 */
public class AccountService {
    DatabaseService databaseService = new DatabaseService();

    // 转出
    public void outAccount(Connection connection, String accountName, int account) throws SQLException {
        String sql = "update t_account set accountBalance=accountBalance-? where accountName=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account);
        preparedStatement.setString(2, accountName);
        preparedStatement.executeUpdate();
    }

    public void inAccount(Connection connection, String accountName, int account) throws SQLException {
        String sql = "update t_account set accountBalance=accountBalance+? where accountName=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account);
        preparedStatement.setString(2, accountName);
        preparedStatement.executeUpdate();
    }

    public void main(String[] args) {
        Connection connection = null;
        // Savepoint savepoint = null;
        // 声明回滚点
        try {
            connection = databaseService.getConnection();
            // 取消自动提交
            connection.setAutoCommit(false);
            System.out.println("Ray开始向Pay转账！");
            int account = 500;
            outAccount(connection, "Ray", account);

            // savepoint = connection.setSavepoint();
            // 设置回滚点在Ray之后，表示事务只回滚到Pay这一点，而对Ray的操作不回滚
            inAccount(connection, "Pay", account);
            System.out.println("转账成功！");
        } catch (Exception e) {
            try {
                // 一旦有异常，将执行回滚操作，回到初始状态
                connection.rollback();
                // connection.rollback(savepoint);
                // 调用回滚，回滚到 savepoint
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // 最终将进行finally操作，提交事务
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
