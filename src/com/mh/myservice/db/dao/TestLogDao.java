package com.mh.myservice.db.dao;

import com.mh.myservice.db.DataBase;
import com.mh.myservice.entity.TestLogEntity;

import java.sql.SQLException;
import java.util.List;

public class TestLogDao extends DataBase<TestLogEntity>{

	public TestLogDao() {
		super("test");
	}

	@Override
	public List<TestLogEntity> listData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestLogEntity selectData(TestLogEntity data) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TestLogEntity data) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TestLogEntity data) throws SQLException {
		boolean execute = getDB().execute("INSERT INTO log (`time`, `log`) VALUES ('" + data.getTime() + "', '" + data.getLog() +"');");
		return execute;
	}

	@Override
	public int countData() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
