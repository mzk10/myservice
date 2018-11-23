package com.mh.myservice.db.dao;

import com.mh.myservice.entity.FontEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FontDao {

    public List<FontEntity> listData() {
        Session session = HibernateUtil.openSession();
        try {
            Query<FontEntity> query = session.createQuery("FROM FontEntity ", FontEntity.class);
            return query.list();
            /*ResultSet rs = getDB().executeQuery("SELECT * FROM font;");
            List<FontEntity> list = new ArrayList<>();
            while (rs.next()) {
                FontEntity font = new FontEntity();
                font.setId(rs.getInt("id"));
                font.setName(rs.getString("name"));
                font.setPath(rs.getString("path"));
                list.add(font);
            }
            return list;*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public void close(){

    }

}
