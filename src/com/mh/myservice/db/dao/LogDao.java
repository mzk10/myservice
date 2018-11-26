package com.mh.myservice.db.dao;

import com.mh.myservice.entity.LogEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URLDecoder;
import java.util.List;

public class LogDao {


    public List<LogEntity> getGroup(String coloum) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "SELECT new LogEntity(id, devices, selected) FROM LogEntity GROUP BY :coloum";
            sql = sql.replace(":coloum", coloum);
            Query<LogEntity> query = session.createQuery(sql, LogEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<LogEntity> listGroupData(String group, String val) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM LogEntity WHERE :group=':val' ORDER BY id DESC";
            sql = sql.replace(":group", group);
            sql = sql.replace(":val", val);
            Query<LogEntity> query = session.createQuery(sql, LogEntity.class);

            List<LogEntity> list = query.list();
            for (LogEntity en : list) {
                String log = en.getLog();
                en.setLog(URLDecoder.decode(log, "UTF-8"));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public LogEntity selectData(LogEntity data) {

        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM LogEntity WHERE id=:id";
            sql = sql.replace(":id", String.valueOf(data.getId()));
            Query<LogEntity> query = session.createQuery(sql, LogEntity.class);
            List<LogEntity> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean add(LogEntity data) {
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

    public boolean delete(String key, String val) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "DELETE LogEntity WHERE :key=:val";
            sql = sql.replace(":key", key);
            sql = sql.replace(":val", val);
            Query query = session.createQuery(sql);
            query.executeUpdate();
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
