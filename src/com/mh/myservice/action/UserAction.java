package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.UserDao;
import com.mh.myservice.entity.ResponseData;
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

    public ResponseData login() {
        String username = getParameter("username");
        String password = getParameter("password");
        UserDao dao = new UserDao();
        UserEntity entity = new UserEntity(username, password);
        UserEntity checkuser = dao.selectData(entity);
        if (checkuser != null) {
            String token = StringUtil.md5(username + password + System.currentTimeMillis());
            checkuser.setToken(token);
            dao.update(checkuser);
            return createResponseData(200, checkuser);
        }else {
            return createResponseData(202);
        }
    }

    public ResponseData tokenlogin(){
        String token = getParameter("token");
        UserDao dao = new UserDao();
        UserEntity entity = dao.selectData(token);
        if (entity!=null){
            return createResponseData(200, entity);
        }else {
            return createResponseData(210);
        }
    }

}
