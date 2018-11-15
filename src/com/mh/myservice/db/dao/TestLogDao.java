package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.TestLogEntity;
import com.sun.javafx.beans.annotations.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestLogDao extends BaseDao<TestLogEntity> {

    public TestLogDao() {
        super("test");
    }

    @Override
    public List<TestLogEntity> listData() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TestLogEntity> getGroup(String coloum) {
        try {
            String sql = "SELECT devices FROM `log` GROUP BY `{coloum}`;";
            sql = sql.replace("{coloum}", coloum);
            ResultSet result;
            result = getDB().executeQuery(sql);
            List<TestLogEntity> list = new ArrayList<>();
            while (result.next()) {
                TestLogEntity entity = new TestLogEntity();
                entity.setDevices(result.getString("devices"));
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestLogEntity> listGroupData(String group, String val) {
        try {
            String sql = "SELECT * FROM `log` WHERE `{group}`='{val}' ORDER BY `id` DESC ;";
            sql = sql.replace("{group}", group);
            sql = sql.replace("{val}", val);
            ResultSet result = null;
            result = getDB().executeQuery(sql);
            List<TestLogEntity> list = new ArrayList<>();
            while (result.next()) {
                TestLogEntity entity = getTestLogEntity(result);
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TestLogEntity getTestLogEntity(ResultSet result) {
        try {
            TestLogEntity entity = new TestLogEntity();
            entity.setId(result.getInt("id"));
            entity.setType(result.getInt("type"));
            entity.setDevices(result.getString("devices"));
            entity.setIp(result.getString("ip"));
            entity.setLog(URLDecoder.decode(result.getString("log"), "UTF-8"));
            entity.setTime(result.getString("time"));
            return entity;
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TestLogEntity selectData(@NonNull TestLogEntity data) {
        try {
            int id = data.getId();
            String sql = "SELECT * FROM `log` WHERE id='{id}';";
            sql = sql.replace("{id}", String.valueOf(id));
            ResultSet result = null;
            result = getDB().executeQuery(sql);
            result.next();
            TestLogEntity entity = getTestLogEntity(result);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(TestLogEntity data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean add(TestLogEntity data) {
        try {
            String sql = "INSERT INTO log (`ip`, `devices`, `time`, `type`, `log`, `timestamp`) VALUES ('{ip}', '{devices}', '{time}', '{type}', '{log}', '{timestamp}');";
            sql = sql
                    .replace("{ip}", data.getIp())
                    .replace("{devices}", data.getDevices())
                    .replace("{time}", data.getTime())
                    .replace("{type}", String.valueOf(data.getType()))
                    .replace("{log}", URLEncoder.encode(data.getLog(), "UTF-8"))
                    .replace("{timestamp}", String.valueOf(System.currentTimeMillis() / 1000));
            return getDB().execute(sql);
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(TestLogEntity data) {
        return false;
    }

    public boolean delete(String key, String val) {
        try {
            String sql = "DELETE FROM log WHERE `{key}`='{val}';";
            sql = sql.replace("{key}", key);
            sql = sql.replace("{val}", val);
            return getDB().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countData() {
        // TODO Auto-generated method stub
        return 0;
    }

}
