package com.mh.myservice.db.dao;

import com.mh.myservice.db.BaseDao;
import com.mh.myservice.entity.VersioncheckEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VersionCheckDao extends BaseDao<VersioncheckEntity> {

    public VersionCheckDao() {
        super("xiezuo");
    }

    @Override
    public List<VersioncheckEntity> listData() {
        return null;
    }

    @Override
    public VersioncheckEntity selectData(VersioncheckEntity data) {
        return null;
    }

    @Override
    public boolean update(VersioncheckEntity data) {
        return false;
    }

    @Override
    public int countData() {
        return 0;
    }

    @Override
    public boolean add(VersioncheckEntity data) {
        return false;
    }

    @Override
    public boolean delete(VersioncheckEntity data) {
        return false;
    }


    public VersioncheckEntity getLastVersion() {
        try {
            ResultSet rs = getDB().executeQuery("SELECT * FROM versioncheck ORDER BY lastVersion DESC LIMIT 1;");
            if (rs.next()) {
                VersioncheckEntity entity = new VersioncheckEntity(
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
