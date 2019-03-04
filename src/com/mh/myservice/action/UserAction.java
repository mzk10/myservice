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

    /**
     * 检查用户唯一性
     *
     * @return
     */
    public Object checkUser(String username, String password) {
        return null;
    }

    /**
     * 注册用户
     *
     * @return
     */
    public Object regist() {


        return null;
    }

    /**
     * 登录
     *
     * @return
     */
    public Object login(String username, String password) {
        //String username = getParameter("username");
        //String password = getParameter("password");
        UserDao dao = new UserDao();
        UserEntity entity = new UserEntity(username, password);
        UserEntity checkuser = dao.selectData(entity);
        if (checkuser != null) {
            String token = StringUtil.md5(username + password + System.currentTimeMillis());
            checkuser.setToken(token);
            dao.update(checkuser);
            return createResponseData(200, checkuser);
        } else {
            return createResponseData(202);
        }
    }

    /**
     * token登录
     *
     * @return
     */
    public Object tokenlogin(String token) {
        //String token = getParameter("token");
        UserDao dao = new UserDao();
        UserEntity entity = dao.selectData(token);
        if (entity != null) {
            return createResponseData(200, entity);
        } else {
            return createResponseData(210);
        }
    }

}
