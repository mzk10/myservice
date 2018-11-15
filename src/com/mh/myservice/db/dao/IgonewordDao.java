package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.IgonewordEntity;

import java.sql.SQLException;
import java.util.List;

public class IgonewordDao extends BaseDao<IgonewordEntity> {

    public IgonewordDao() {
        super("xiezuo");
    }

    @Override
    public List<IgonewordEntity> listData() {
        return null;
    }

    @Override
    public IgonewordEntity selectData(IgonewordEntity data) {
        return null;
    }

    @Override
    public boolean add(IgonewordEntity data) {
        try {
            String sql = "INSERT INTO `igoneword` (word) VALUE ('{word}')";
            getDB().execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean add(String word) {
        try {
            String sql = "INSERT INTO `igoneword` (word) VALUE ('{word}')";
            sql = sql.replace("{word}", word);
            return getDB().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(IgonewordEntity data) {
        return false;
    }

    @Override
    public boolean update(IgonewordEntity data) {
        return false;
    }

    @Override
    public int countData() {
        return 0;
    }
}
