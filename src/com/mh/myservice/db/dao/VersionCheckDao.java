package com.mh.myservice.db.dao;

import com.mh.myservice.entity.VersionCheckEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class VersionCheckDao {


    public VersionCheckEntity getLastVersion() {
        Session session = HibernateUtil.openSession();
        try {
            Query<VersionCheckEntity> query = session.createQuery("FROM VersionCheckEntity ORDER BY lastVersion DESC ", VersionCheckEntity.class);
            query.setMaxResults(1);
            List<VersionCheckEntity> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
