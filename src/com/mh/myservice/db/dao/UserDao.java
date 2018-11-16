package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDao<UserEntity> {

    public UserDao() {
        super("xiezuo");
    }

    @Override
    public List<UserEntity> listData() {
        return null;
    }

    @Override
    public UserEntity selectData(UserEntity data) {
        try {
            String sql = "SELECT * FROM user where `username`='{username}' AND `password`='{password}'";
            sql = sql.replace("{username}", data.getUsername());
            sql = sql.replace("{password}", data.getPassword());
            ResultSet rs = getDB().executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new UserEntity(id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserEntity selectData(String token) {
        try {
            String sql = "SELECT * FROM user where `token`='{token}'";
            sql = sql.replace("{token}", token);
            ResultSet rs = getDB().executeQuery(sql);
            if (rs.next()) {
                return new UserEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("token")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUsername(String username) {
        try {
            String sql = "SELECT * FROM user where `username`='{username}'";
            sql = sql.replace("{username}", username);
            ResultSet rs = getDB().executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(UserEntity data) {
        try {
            String sql = "INSERT INTO `user` (`username`, `password`, `token`)" +
                    " VALUE ('{username}', '{password}', '{token}')";
            sql = sql.replace("{username}", data.getUsername());
            sql = sql.replace("{password}", data.getPassword());
            sql = sql.replace("{token}", "");
            getDB().execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(UserEntity data) {
        return false;
    }

    @Override
    public boolean update(UserEntity data) {
        if (data != null) {
            try {
                String sql = "UPDATE user SET " +
                        "token='{token}' " +
//                        "username='{username}', " +
//                        "password='{password}', " +
                        "WHERE id={id}";
//                sql = sql.replace("{username}", data.getUsername());
//                sql = sql.replace("{password}", data.getPassword());
                sql = sql.replace("{token}", data.getToken());
                sql = sql.replace("{id}", String.valueOf(data.getId()));
                return getDB().execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int countData() {
        return 0;
    }
}
