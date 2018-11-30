package com.mh.myservice.db;

import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDao<T> {

    private final Class entityClass;

    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public boolean add(T data) {
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

    public boolean delete(String coloum, String val) {
        Session session = HibernateUtil.openSession();
        Transaction ts = session.beginTransaction();
        String simpleName = entityClass.getSimpleName();
        try {
            String sql = "DELETE " + simpleName + " WHERE :key=':val'";
            sql = sql.replace(":coloum", coloum);
            sql = sql.replace(":val", val);
            Query query = session.createQuery(sql);
            query.executeUpdate();
            ts.commit();
            return true;
        } catch (Exception e) {
            ts.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(T data) {
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

}
