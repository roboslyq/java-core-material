package com.roboslyq.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description: SUN公司提供的一种数据库访问规则、规范, 由于数据库种类较多，并且java语言使用比较广泛，
 * sun公司就提供了一种规范，让其他的数据库提供商去实现底层的访问规则。
 * 我们的java程序只要使用sun公司提供的jdbc驱动即可。
 * @author: zone.Li
 * @create: 2020-03-31 16:34
 **/
public class JdbcDemo {
    public static void main(String[] args) {
        //定义需要的对象
        PreparedStatement ps = null;
        Connection ct = null;
        try {
            //1.初始化对象,加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2.得到连接(1433表示sql server的默认端口)
            ct = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.252.241.24)(PORT=1521))(LOAD_BALANCE=YES)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=odsuat01)))", "rptuser", "rptuser");
//            ct = DriverManager.getConnection("jdbc:oracle:thin:@//10.252.241.24:1521/odsuat01", "rptuser", "rptuser");
            //3.创建Preparestatement,创建数据
            ps = ct.prepareStatement("SELECT 1 FROM USER_DEPENDENCIES WHERE NAME = ?");
            //可以传参数进去SQL语句里，但是注意 ? 只能在where条件里面
            ps.setString(1,"NAME");
            //查询结果
            ResultSet resultSet = ps.executeQuery();
            //当判断
            while (resultSet.next()){
                System.out.println(resultSet.getString("NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                //为了程序健壮
                if (ps != null){
                    ps.close();
                }
                if (ct != null){
                    ct.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

    }
}
