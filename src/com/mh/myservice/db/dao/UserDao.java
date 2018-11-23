package com.mh.myservice.db.dao;

import com.mh.myservice.entity.UserEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao{


    public UserEntity selectData(UserEntity data) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM UserEntity WHERE username=':username' AND password=':password'";
            sql = sql.replace(":username", data.getUsername());
            sql = sql.replace(":password", data.getPassword());
            Query<UserEntity> query = session.createQuery(sql, UserEntity.class);
            List<UserEntity> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public UserEntity selectData(String token) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM UserEntity WHERE token=':token'";
            sql = sql.replace(":token", token);
            Query<UserEntity> query = session.createQuery(sql, UserEntity.class);
            List<UserEntity> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean checkUsername(String username) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM UserEntity WHERE username=':username'";
            sql = sql.replace(":username", username);
            Query<UserEntity> query = session.createQuery(sql, UserEntity.class);
            List<UserEntity> list = query.list();
            return list.size()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean add(UserEntity data) {
        Session session = HibernateUtil.openSession();
        try {
            session.save(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(UserEntity data) {
        Session session = HibernateUtil.openSession();
        try {
            session.update(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public void close() {
    }
}
