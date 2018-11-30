package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.Rootuser;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RootuserDao extends BaseDao<Rootuser> {

    public Rootuser selectData(Rootuser data) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM Rootuser WHERE username=':username'";
            sql = sql.replace(":username", data.getUsername());
            Query<Rootuser> query = session.createQuery(sql, Rootuser.class);
            List<Rootuser> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /*public boolean add(Rootuser data) {
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
    }*/

}
