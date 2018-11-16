package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.VersionCheckEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VersionCheckDao extends BaseDao<VersionCheckEntity> {

    public VersionCheckDao() {
        super("xiezuo");
    }

    @Override
    public List<VersionCheckEntity> listData() {
        return null;
    }

    @Override
    public VersionCheckEntity selectData(VersionCheckEntity data) {
        return null;
    }

    @Override
    public boolean update(VersionCheckEntity data) {
        return false;
    }

    @Override
    public int countData() {
        return 0;
    }

    @Override
    public boolean add(VersionCheckEntity data) {
        return false;
    }

    @Override
    public boolean delete(VersionCheckEntity data) {
        return false;
    }


    public VersionCheckEntity getLastVersion() {
        try {
            ResultSet rs = getDB().executeQuery("SELECT * FROM versioncheck ORDER BY lastVersion DESC LIMIT 1;");
            if (rs.next()) {
                VersionCheckEntity entity = new VersionCheckEntity(
                        rs.getInt("lastVersion"),
                        rs.getString("downloadUrl"),
                        rs.getInt("length"),
                        rs.getString("versionDetail"),
                        rs.getString("versionName"));
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
