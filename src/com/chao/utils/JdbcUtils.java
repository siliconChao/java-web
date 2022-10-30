package com.chao.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-27
 */
@Deprecated
public class JdbcUtils {
    /**
     * 获取数据库链接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection()   {
       Connection connection = null;
        try {
            //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            /*Properties pros = new Properties();
            pros.load(is);*/
            InputStream rs = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            //FileInputStream fis = new FileInputStream(new File("druid.properties"));
            Properties pros = new Properties();
            pros.load(rs);
            System.out.println("password : "  + pros.getProperty("password"));

            DataSource source = DruidDataSourceFactory.createDataSource(pros);

            connection  = source.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭资源
     *
     * @param connection
     * @param ps
     */
    public static void closeResource(Connection connection, PreparedStatement ps) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection connection, PreparedStatement ps, ResultSet rs) {
        closeResource(connection, ps);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
