package com.mh.myservice.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.mh.myservice.core.Action;
import com.mh.myservice.entity.VersionCheckEntity;

public class VersionAction extends Action{

	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		return null;
	}
	
	public Object check() throws ServletException, IOException, SQLException
	{
		VersionCheckEntity versionCheckEntity = new VersionCheckEntity();
		versionCheckEntity.setLastVersion(2);
		versionCheckEntity.setDownloadUrl("http:www.kengni.com/lalala");
		versionCheckEntity.setLength(1024000);
		return createResponseData(200, versionCheckEntity);
	}

}
