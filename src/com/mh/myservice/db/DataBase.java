package com.mh.myservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataBase<T> {

	private Statement sm;

	public Statement getDB() {
		return sm;
	}

	public DataBase(){
        try {
        	Connection conn = DriverManager.getConnection("", "", "");
//        	Connection conn = DriverManager.getConnection("jdbc:mysql://123.57.46.159:3579/kanfamily", "root", "Wojiushi1");
			sm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract List<T> listData() throws SQLException;
	
	public abstract T selectData(T data) throws SQLException;
	
	public abstract boolean update(T data) throws SQLException;
	
	public abstract boolean add(T data) throws SQLException;
	
	public void close(){
		try {
			sm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
	
	public abstract int countData() throws SQLException;
	
}
