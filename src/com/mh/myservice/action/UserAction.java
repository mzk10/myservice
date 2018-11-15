package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.UserDao;
import com.mh.myservice.entity.UserEntity;
import com.mh.myservice.util.StringUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

public class UserAction extends Action {

    @Override
    public Object deFault() throws ServletException, IOException, SQLException {
        return null;
    }

    public Object login() {
        String username = getParameter("username");
        String password = getParameter("password");
        UserDao dao = null;
        UserEntity entity = new UserEntity(username, password);
        dao = new UserDao();
        UserEntity checkuser = dao.selectData(entity);
        if (checkuser != null) {
            String token = StringUtil.md5(username + password + System.currentTimeMillis());
            return createResponseData(200, checkuser);
        }
        if (dao != null) {
            dao.close();
        }
        return createResponseData(202);
    }

}
