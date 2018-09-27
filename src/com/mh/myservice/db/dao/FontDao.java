package com.mh.myservice.db.dao;

import com.mh.myservice.db.DataBase;
import com.mh.myservice.entity.FontEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FontDao extends DataBase<FontEntity>{

	public FontDao() {
		super("xiezuo");
	}

	@Override
	public List<FontEntity> listData() throws SQLException {
		ResultSet rs = getDB().executeQuery("SELECT * FROM font;");
		List<FontEntity> list = new ArrayList<>();
		while(rs.next())
		{
			FontEntity font = new FontEntity();
			font.setId(rs.getInt("id"));
			font.setName(rs.getString("name"));
			font.setPath(rs.getString("path"));
			list.add(font);
		}
		return list;
	}

	@Override
	public FontEntity selectData(FontEntity data) throws SQLException {
		return null;
	}

	@Override
	public boolean update(FontEntity data) throws SQLException {
		return false;
	}

	@Override
	public boolean add(FontEntity data) throws SQLException {
		return false;
	}

	@Override
	public boolean delete(FontEntity data) throws SQLException {
		return false;
	}

	@Override
	public int countData() throws SQLException {
		return 0;
	}

}
