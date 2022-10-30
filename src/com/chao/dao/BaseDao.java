package com.chao.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-27
 */
public class BaseDao<T> {
    private static final String DRIVER_CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.56.20:26200/fruit_db";
    private static final String USERNAME = "chao";
    private static final String PASSWORD = "lc_konglc";

    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] types = type.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    private Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASSNAME);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private  void closeResource(Connection connection, PreparedStatement ps, ResultSet rs) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public T queryForObject(String sql, Object... args) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            t = null;
            if (rs.next()) {
                t = clazz.newInstance();
                int count = metaData.getColumnCount();
                for (int i = 0; i < count; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    Object object = rs.getObject(i + 1);
                    field.setAccessible(true);
                    field.set(t, object);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return t;
    }

    public List<T> queryForList(String sql, Object... args) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> fruitList = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            fruitList = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    Object object = rs.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, object);
                }
                fruitList.add(t);
            }
            return fruitList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           closeResource(conn, ps, rs);
        }
        return fruitList;
    }

    public <E> E getValue(String sql, Object... args) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(connection,ps,rs);
        }
        return null;
    }

    public int update(String sql, Object... args) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
         int count = 0;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            count = ps.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           closeResource(connection,null,null);
        }
        return count;
    }
}
