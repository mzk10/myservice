package com.mh.myservice.action;


import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.VersionCheckDao;
import com.mh.myservice.entity.VersionCheckEntity;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class VersionAction extends Action{

	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		return null;
	}
	
	public Object check() throws ServletException, IOException, SQLException
	{
		VersionCheckDao dao = new VersionCheckDao();
		VersionCheckEntity versionCheckEntity = dao.getLastVersion();
		dao.close();
		if(versionCheckEntity!=null)
		{
			return createResponseData(200, versionCheckEntity);
		}else{
			return createResponseData(404, null);
		}
	}

}
