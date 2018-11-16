package com.mh.myservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDao<T> {

    private Connection conn;
    private Statement sm;

    protected Statement getDB() {
        return sm;
    }

    public BaseDao(String dbname) {
        try {
        	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3579/" + dbname, "mzk10", "Wojiushi1");
//            conn = DriverManager.getConnection("jdbc:mysql://api.kanfamily.net:3579/" + dbname, "mzk10", "Wojiushi1");
            sm = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract List<T> listData();

    public abstract T selectData(T data);

    public abstract boolean add(T data);

    public abstract boolean delete(T data);

    public abstract boolean update(T data);

    public void close() {
        if (sm != null) {
            try {
                sm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    ;

    public abstract int countData();

}
