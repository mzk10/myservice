package com.mh.myservice.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataBase<T> {

	private Connection conn;
	private Statement sm;

	protected Statement getDB() {
		return sm;
	}

	public DataBase(String dbname){
        try {
//        	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3579/" + dbname, "mzk10", "Wojiushi1");
        	conn = DriverManager.getConnection("jdbc:mysql://api.kanfamily.net:3579/" + dbname, "mzk10", "Wojiushi1");
			sm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract List<T> listData() throws SQLException;
	public abstract T selectData(T data) throws SQLException, UnsupportedEncodingException;
	public abstract boolean add(T data) throws SQLException, UnsupportedEncodingException;
	public abstract boolean delete(T data) throws SQLException;
	public abstract boolean update(T data) throws SQLException;

	public void close(){
		if (sm!=null) {
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};
	
	public abstract int countData() throws SQLException;
	
}
