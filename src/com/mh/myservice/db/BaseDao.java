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

    public String add(T data) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String id = (String) session.save(data);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "";
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
        Transaction transaction = session.beginTransaction();
        try {
            session.update(data);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

}
