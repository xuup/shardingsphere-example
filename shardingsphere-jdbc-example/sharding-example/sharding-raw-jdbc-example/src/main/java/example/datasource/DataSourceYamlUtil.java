package example.datasource;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataSourceYamlUtil {

    public static DataSource getDataSource(){

        DataSource dataSource = null;
        try {
            String url = DataSourceYamlUtil.class.getClassLoader().getResource("sharding-database.yaml").getPath();
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(url));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataSource;
    }
}
