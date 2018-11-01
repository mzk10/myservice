package com.mh.myservice.db.dao;

import com.mh.myservice.db.DataBase;
import com.mh.myservice.entity.TestLogEntity;
import com.sun.javafx.beans.annotations.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public List<TestLogEntity> getGroup(String coloum) throws SQLException {
		String sql = "SELECT devices FROM `log` GROUP BY `{coloum}`;";
		sql = sql.replace("{coloum}", coloum);
        ResultSet result = getDB().executeQuery(sql);
        List<TestLogEntity> list = new ArrayList<>();
        while (result.next()){
            TestLogEntity entity = new TestLogEntity();
            entity.setDevices(result.getString("devices"));
            list.add(entity);
        }
        return list;
	}

    public List<TestLogEntity> listGroupData(String group, String val) throws SQLException, UnsupportedEncodingException {
        String sql = "SELECT * FROM `log` WHERE `{group}`='{val}' ORDER BY `id` DESC ;";
        sql = sql.replace("{group}", group);
        sql = sql.replace("{val}", val);
        ResultSet result = getDB().executeQuery(sql);
        List<TestLogEntity> list = new ArrayList<>();
        while (result.next()){
            TestLogEntity entity = getTestLogEntity(result);
            list.add(entity);
        }
        return list;
    }

    private TestLogEntity getTestLogEntity(ResultSet result) throws SQLException, UnsupportedEncodingException {
        TestLogEntity entity = new TestLogEntity();
        entity.setId(result.getInt("id"));
        entity.setType(result.getInt("type"));
        entity.setDevices(result.getString("devices"));
        entity.setIp(result.getString("ip"));
        entity.setLog(URLDecoder.decode(result.getString("log"), "UTF-8"));
        entity.setTime(result.getString("time"));
        return entity;
    }

    @Override
	public TestLogEntity selectData(@NonNull TestLogEntity data) throws SQLException, UnsupportedEncodingException {
        int id = data.getId();
        String sql = "SELECT * FROM `log` WHERE id='{id}';";
        sql = sql.replace("{id}", String.valueOf(id));
        ResultSet result = getDB().executeQuery(sql);
        result.next();
        TestLogEntity entity = getTestLogEntity(result);
        return entity;
	}

	@Override
	public boolean update(TestLogEntity data) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TestLogEntity data) throws SQLException, UnsupportedEncodingException {
		String sql = "INSERT INTO log (`ip`, `devices`, `time`, `type`, `log`, `timestamp`) VALUES ('{ip}', '{devices}', '{time}', '{type}', '{log}', '{timestamp}');";
		sql = sql
				.replace("{ip}", data.getIp())
				.replace("{devices}", data.getDevices())
				.replace("{time}", data.getTime())
				.replace("{type}", String.valueOf(data.getType()))
				.replace("{log}", URLEncoder.encode(data.getLog(), "UTF-8"))
				.replace("{timestamp}", String.valueOf(System.currentTimeMillis()/1000));
		boolean execute = getDB().execute(sql);
		return execute;
	}

    @Override
    public boolean delete(TestLogEntity data) throws SQLException {
        return false;
    }

    public boolean delete(String key, String val) throws SQLException {
        String sql = "DELETE FROM log WHERE `{key}`='{val}';";
        sql = sql.replace("{key}", key);
        sql = sql.replace("{val}", val);
        return getDB().execute(sql);
    }

    @Override
	public int countData() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
