package example.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSource(){

        Map<String,DataSource> dataSourceMap = new HashMap<String,DataSource>();

        //配置第一个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/demo_ds_0?userUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/demo_ds_1?userUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("ds1", dataSource2);

        //配置t_order表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new
                ShardingTableRuleConfiguration("t_order","ds${0..1}.t_order_${0..1}");

        //配置分库策略 order_id % 2 == 0  ds0 ds1
        orderTableRuleConfig.setDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration(
                        "order_id","dbShardingAlgorithm"));

        //配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(
                new StandardShardingStrategyConfiguration(
                        "user_id","tableShardingAlgorithm"));

        //配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = null;
        try {
            dataSource = ShardingSphereDataSourceFactory.createDataSource(
                    dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }
}
