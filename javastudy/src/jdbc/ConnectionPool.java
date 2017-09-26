package jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Created by sun on 2017/2/21.
 */
public class ConnectionPool {
    private static Log log = LogFactory.getLog(ConnectionPool.class);

    //连接池属性
    private int initSize;
    private int maxSize;
    private int currentSize = 0;

    private static Properties properties = new Properties();
    private static LinkedList<Connection> connectionPool = new LinkedList<Connection>();

    //加载ConnectionPool类时加载数据库驱动
    static {
        try {
            InputStream inputStream = new FileInputStream("./src/jdbc/sql.properties");
            properties.load(inputStream);
            Class.forName(properties.getProperty("driver"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return  数据库连接
     */
    /*public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
            log.info("连接数据库成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }*/

    /**
     * 使用动态代理的方法获取数据库连接
     * @return  数据库连接
     */
    public Connection getConnection(){
        try {
            final Connection connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
            log.info("连接数据库成功！");
            //把连接交给动态代理类转换为代理的连接对象
            Connection myconn = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),
                    new Class[] {Connection.class},
                    new InvocationHandler(){
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args)
                                throws Throwable {
                            Object value = null;

                            //当遇到close方法，就会把对象放回连接池中，而不是关闭连接
                            if (method.getName().equals("close")){
                                ConnectionPool.connectionPool.addLast(connection);
                            }else {
                                //其他方法不变
                                value = method.invoke(connection, args);
                            }
                            return value;
                        }
                    });
            return  myconn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 构造函数
     * @param init  初始化连接数
     * @param max  最大连接数
     */
    public ConnectionPool(int init, int max){
        initSize = init;
        maxSize = max;
        Connection connection;
        for (int i=0; i < initSize; i++){
            connection = getConnection();
            connectionPool.add(connection);
            currentSize++;
            log.info("当前连接数：" + currentSize);
        }
    }

    /**
     * 从连接池中获取连接
     * @return  数据库连接
     */
    public Connection getConnectionFromPool(){
        Connection connection = null;
        if (connectionPool.size() > 0){
            connection = connectionPool.getFirst();
            connectionPool.removeFirst();
            log.info("当前连接数：" + currentSize);
        }else if ((connectionPool.size() == 0) && currentSize < maxSize){
            //连接池为空，但是当前连接数小于最大连接数
            connectionPool.addLast(getConnection());
            currentSize++;

            log.info("当前连接数：" + currentSize);
            connection = connectionPool.getFirst();
            connectionPool.removeFirst();
        }else{
            log.info("连接数已达上限，请等待");
        }
        return connection;
    }

    /**
     * 待释放的连接，加入到连接池中
     * @param connection  待释放的连接
     */
    public void releaseConnection(Connection connection){
        if (connection != null){
            connectionPool.addLast(connection);
        }
    }

    public static void main(String[] args){
        ConnectionPool conn = new ConnectionPool(5, 8);
        Connection connection = null;
        for (int i = 0; i < 8; i++){
            connection = conn.getConnectionFromPool();
            System.out.println(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*if (i == 7){
                conn.releaseConnection(connection);
            }*/
        }
    }
}
