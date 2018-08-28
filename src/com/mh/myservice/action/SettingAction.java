package com.mh.myservice.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.FontDao;
import com.mh.myservice.entity.FontEntity;
import com.mh.myservice.entity.ResponseData;

public class SettingAction extends Action{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		return null;
	}
	
	public Object fontlist() throws SQLException
	{
		FontDao dao = new FontDao("");
		List<FontEntity> listData = dao.listData();
		dao.close();
		return ResponseData.create(200, listData);
	}
	
	

}
