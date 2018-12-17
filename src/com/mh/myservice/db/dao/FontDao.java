package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.FontEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FontDao extends BaseDao<FontEntity> {

    public List<FontEntity> listData() {
        Session session = HibernateUtil.openSession();
        try {
            Query<FontEntity> query = session.createQuery("FROM FontEntity ", FontEntity.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
