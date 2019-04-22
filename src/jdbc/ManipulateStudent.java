package jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

/**
 * Created by sun on 2017/2/20.
 */
public class ManipulateStudent {
    private Log log = LogFactory.getLog(ManipulateStudent.class);
    private DBConnection dbConnection = new DBConnection();
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    /**
     * 向student表中插入一条静态数据
     */
    public void insertStudent(){
        conn = dbConnection.getConnection();
        String insertSql = "insert into student values(201572642, '杨', 24)";
        try {
            statement = conn.createStatement();
            statement.executeUpdate(insertSql);
            log.info(insertSql + "插入成功！");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向student表中插入一条指定的数据
     * @param number  学号
     * @param name  姓名
     * @param age  年龄
     */
    public void insertStudent(int number, String name, int age){
        conn = dbConnection.getConnection();
        String insertSql = "insert into student values(?,?,?)";
        try {
            preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            log.info(insertSql + " 插入成功！");
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号删除student表中指定的一条记录
     * @param number  学号
     */
    public void deleteStudent(int number){
        conn = dbConnection.getConnection();
        String deleteSql = "delete from student where number = ?";
        try {
            preparedStatement = conn.prepareStatement(deleteSql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
            log.info(deleteSql + " 删除成功！");
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新student表中指定的一条记录
     * @param number  学号
     * @param age  年龄
     */
    public void updateStudent(int number, int age){
        conn = dbConnection.getConnection();
        String updateSql = "update student set age = ? where number = ?";
        try {
            preparedStatement = conn.prepareStatement(updateSql);
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, number);
            preparedStatement.executeUpdate();
            log.info(updateSql + " 更新成功！");
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号查询student表中指定的一条记录
     * @param number
     */
    public void selectStudent(int number){
        conn = dbConnection.getConnection();
        String selectSql = "select * from student where number = ?";
        try {
            preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + "\t" +
                        resultSet.getString(2) + "\t" + resultSet.getInt(3));
            }
            log.info(selectSql + " 查询成功！");
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用存储过程,在student表中计算两个学号指定的记录的年龄之和
     * @param number_a  学号
     * @param number_b  学号
     */
    public void callProcedure(int number_a, int number_b){
        conn = dbConnection.getConnection();
        String procedure = "{call add_age(?,?,?)}";
        try {
            CallableStatement callableStatement = conn.prepareCall(procedure);
            callableStatement.setInt(1, number_a);
            callableStatement.setInt(2, number_b);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();
            System.out.println("age_sum = " + callableStatement.getInt(3));
            log.info(procedure + " 存储过程调用成功！");
            callableStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用函数，在student表中计算两个学号指定的记录的年龄之和
     * @param number_a  学号
     * @param number_b  学号
     */
    public void callFunction(int number_a, int number_b){
        conn = dbConnection.getConnection();
        String function = "{?=call sum_age(?,?)}";
        try {
            CallableStatement callableStatement = conn.prepareCall(function);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, number_a);
            callableStatement.setInt(3, number_b);
            callableStatement.execute();
            System.out.println("age_sum = " + callableStatement.getInt(1));
            log.info(function + " 函数调用成功！");
            callableStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭自动提交，开启事务
     */
    public void insertTransaction(){
        conn = dbConnection.getConnection();
        String insertSql = "insert into student values(?,?,?)";
        try {
            //关闭自动提交，开启事务
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setInt(1, 201572647);
            preparedStatement.setString(2, "吴");
            preparedStatement.setInt(3, 23);
            preparedStatement.executeUpdate();
            preparedStatement.setInt(1, 201572648);
            preparedStatement.setString(2, "唐");
            preparedStatement.setInt(3, 24);
            preparedStatement.executeUpdate();
            conn.commit();
            log.info(insertSql + " 事务提交成功！");
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
                log.info(insertSql + " 事务提交失败！");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 批处理操作，以事务形式处理
     */
    public void batchOperation(){
        //1.获取连接
        conn = dbConnection.getConnection();
        String batch = "insert into student values(?,?,?)";
        try {
            //关闭自动提交，开启事务
            conn.setAutoCommit(false);
            //2.创建PreparedStatement对象
            preparedStatement = conn.prepareStatement(batch);
            //3.操作数据库
            preparedStatement.setInt(1, 201572649);
            preparedStatement.setString(2, "汪");
            preparedStatement.setInt(3, 24);
            preparedStatement.addBatch();
            preparedStatement.setInt(1, 201572645);
            preparedStatement.setString(2, "周");
            preparedStatement.setInt(3, 22);
            preparedStatement.addBatch();
            //提交批处理命令
            preparedStatement.executeBatch();
            //提交事务
            conn.commit();
            log.info(batch + " 批处理操作成功！");
            //4.关闭连接
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            try {
                //回滚事务
                conn.rollback();
                log.info(batch + "批处理操作失败！");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ManipulateStudent manipulateStudent = new ManipulateStudent();
//        manipulateStudent.insertStudent();
//        manipulateStudent.insertStudent(201572643, "汪", 24);
//        manipulateStudent.deleteStudent(201572646);
//        manipulateStudent.updateStudent(201572641, 24);
//        manipulateStudent.selectStudent(201572640);
//        manipulateStudent.callProcedure(201572640, 201572641);
//        manipulateStudent.callFunction(201572640, 201572641);
//        manipulateStudent.insertTransaction();
        manipulateStudent.batchOperation();
    }

}
