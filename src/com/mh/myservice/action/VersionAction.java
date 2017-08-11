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
		versionCheckEntity.setLastVersion(1);
		versionCheckEntity.setDownloadUrl("http://api.kanfamily.com:8080/myservice/app/myxiezuo_1.apk");
		versionCheckEntity.setLength(9412686);
		versionCheckEntity.setVersionName("V0.14Beta");
		versionCheckEntity.setVersionDetail("增加了嗯嗯嗯功能。;增阿斯蒂芬加了啊啊啊功能。;增发的加了啪啪啪功能。");
		return createResponseData(200, versionCheckEntity);
	}

}
