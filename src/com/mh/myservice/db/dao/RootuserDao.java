package com.mh.myservice.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mh.myservice.db.DataBase;
import com.mh.myservice.entity.Rootuser;

public class RootuserDao extends DataBase<Rootuser>{

	public RootuserDao(String dbname) {
		super("xiezuo");
	}

	@Override
	public List<Rootuser> listData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rootuser selectData(Rootuser data) throws SQLException {
		ResultSet rs = getDB().executeQuery("SELECT * FROM rootuser WHERE username='"+ data.getUsername() + "';");
		if (rs.next()) {
			Rootuser rootuser = new Rootuser();
			rootuser.setUsername(rs.getString("username"));
			rootuser.setPassword(rs.getString("password"));
			rootuser.setId(rs.getInt("id"));
			rootuser.setStatus(rs.getInt("status"));
			return rootuser;
		}
		return null;
	}

	@Override
	public boolean update(Rootuser data) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Rootuser data) throws SQLException {
		Rootuser res = selectData(data);
		if (res!=null) {
			return false;
		}else{
			getDB().execute("INSERT INTO rootuser (`username`, `password`, `status`) VALUES ('" + data.getUsername() + "', '" + data.getPassword() +"', 0);");
			return true;
		}
	}

	@Override
	public int countData() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


}
