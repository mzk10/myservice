package com.mh.myservice.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mh.myservice.db.DataBase;
import com.mh.myservice.entity.VersionCheckEntity;

public class VersionCheckDao extends DataBase<VersionCheckEntity> {
	
	@Override
	public List<VersionCheckEntity> listData() throws SQLException {
		return null;
	}

	@Override
	public VersionCheckEntity selectData(VersionCheckEntity data) throws SQLException {
		return null;
	}

	@Override
	public boolean update(VersionCheckEntity data) throws SQLException {
		return false;
	}

	@Override
	public boolean add(VersionCheckEntity data) throws SQLException {
		return false;
	}

	@Override
	public int countData() throws SQLException {
		return 1;
	}

	public VersionCheckEntity getLastVersion() {
		try {
			ResultSet rs = getDB().executeQuery("SELECT * FROM versioncheck ORDER BY lastVersion DESC LIMIT 1;");
			if (rs.next()) {
				VersionCheckEntity entity = new VersionCheckEntity(rs.getInt("lastVersion"),
						rs.getString("downloadUrl"), rs.getInt("length"), rs.getString("versionDetail"),
						rs.getString("versionName"));
				return entity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
