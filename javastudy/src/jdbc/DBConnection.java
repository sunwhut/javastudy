package jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by sun on 2017/2/20.
 */
public class DBConnection {
    private static Log log = LogFactory.getLog(DBConnection.class);
    private Connection conn = null;

    /**
     * 获取数据库的连接
     * @return Connection类的对象
     */
    public Connection getConnection(){
        //以顶层package作为根目录
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("jdbc/sql.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            //1.加载数据库驱动
            Class.forName(driver);
            //2.通过DriverManager获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            log.info("连接数据库成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     *关闭数据库的连接
     */
    public void closeConnection(){
        if (conn != null){
            try {
                conn.close();
                log.info("关闭数据库成功！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        dbConnection.closeConnection();
    }
}
