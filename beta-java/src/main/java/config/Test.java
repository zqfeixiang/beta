package config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Test {

    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/argus";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "password01!";

    public static void main(String[] args) throws Exception {
        String sql = "{CALL proc_trunc_tab('ctl_chg_date2', 'dzq')}";
        call_proc(sql);
    }

    @org.junit.Test
    public void test(){
        System.out.println(getClass().getSimpleName());
    }
    public static void call_proc(String proc) throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        CallableStatement cstm = connection.prepareCall(proc); //实例化对象cstm
        cstm.execute(); // 执行存储过程
        cstm.close();
        connection.close();
    }

    public static void test2() throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = "{CALL pro_number(?,?,?)}"; //调用存储过程 
        CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm 
        cstm.setInt(1, 2); // 存储过程输入参数 
        cstm.setInt(2, 2); // 存储过程输入参数 
        cstm.registerOutParameter(3, Types.INTEGER); // 设置返回值类型 即返回值 
        cstm.execute(); // 执行存储过程 
        System.out.println(cstm.getInt(3));
        cstm.close();
        connection.close();

    }
} 