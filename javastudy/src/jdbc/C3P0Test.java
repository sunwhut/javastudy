package jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by sun on 2017/2/21.
 */
public class C3P0Test {
    private static Log log = LogFactory.getLog(C3P0Test.class);

    private static Properties properties = new Properties();
    private static ComboPooledDataSource comboPooledDataSource = null;

    static {
        try {
            InputStream inputStream = new FileInputStream("./src/jdbc/c3p0.properties");
            properties.load(inputStream);

            //获取连接池对象
            comboPooledDataSource = new ComboPooledDataSource();

            //设置连接参数
            comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
            comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
            comboPooledDataSource.setUser(properties.getProperty("username"));
            comboPooledDataSource.setPassword(properties.getProperty("password"));

            //设置连接池参数
            comboPooledDataSource.setInitialPoolSize(Integer.parseInt(
                    properties.getProperty("initialSize")));
            comboPooledDataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxSize")));
            //当连接池用完后客户端调用getConnection后等待获取连接的最大等待时间，单位ms
            comboPooledDataSource.setCheckoutTimeout(Integer.parseInt(
                    properties.getProperty("maxWaitTime")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return  数据库连接
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
            log.info("数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args){
        C3P0Test c3P0Test = new C3P0Test();
        Connection connection;
        for (int i = 0; i < 9; i++){
            connection = c3P0Test.getConnection();
            System.out.println(connection.hashCode());
            if (i == 7){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
