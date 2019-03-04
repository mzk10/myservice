package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.BookEntity;
import com.mh.myservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BookDao extends BaseDao<BookEntity> {

    public List<BookEntity> listBook(String userid) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM BookEntity WHERE userid=:userid";
            sql = sql.replace(":userid", userid);
            Query<BookEntity> query = session.createQuery(sql, BookEntity.class);
            List<BookEntity> list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }


    public boolean checkBookName(int userid, String bookName) {
        return checkBookName(String.valueOf(userid), bookName);
    }

    public boolean checkBookName(String userid, String bookName) {
        Session session = HibernateUtil.openSession();
        try {
            String sql = "FROM BookEntity WHERE userid=:userid AND bookName=':bookName'";
            sql = sql.replace(":userid", userid)
                    .replace(":bookName", bookName);
            Query<BookEntity> query = session.createQuery(sql, BookEntity.class);
            BookEntity entity = query.uniqueResult();
            return entity != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

}
