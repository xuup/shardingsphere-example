package example;

import example.datasource.DataSourceUtil;
import example.datasource.DataSourceYamlUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShardingJdbcDemo {

//    public static void main(String[] args) {
//        DataSource dataSource = DataSourceUtil.getDataSource();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            String sql = "insert into t_order(order_id, user_id, status) values (?, ?, ?)";
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1,3);
//            statement.setInt(2,3);
//            statement.setString(3, "ok");
//
//            int r = statement.executeUpdate();
//            System.out.println("r======" + r);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) {
        DataSource dataSource = DataSourceYamlUtil.getDataSource();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "insert into t_order_item (order_item_id, order_id, user_id, status) values (?,?,?,?)";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,3);
            statement.setInt(2,3);
            statement.setInt(3,3);
            statement.setString(4,"status");

            statement.executeUpdate();
//            rs = statement.executeQuery();
//            while (rs.next()){
//                System.out.println(rs.getInt(1) + "---" + rs.getInt(2));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
