package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.FontDao;
import com.mh.myservice.entity.FontEntity;
import com.mh.myservice.entity.ResponseData;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
		FontDao dao = new FontDao();
		List<FontEntity> listData = dao.listData();
		return ResponseData.create(CODE_SUCCESS, listData);
	}
	
	

}
