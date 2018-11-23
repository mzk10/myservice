package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.LogDao;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.entity.LogEntity;
import com.mh.myservice.util.NameValues;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestAction extends Action {

    private static final String TOKEN = "weoiurwoieurtiowutoiuer";

    @Override
    public Object deFault() {
        LogDao dao = new LogDao();
        LogEntity entity = new LogEntity();
        String id1 = getParameter("id");
        int id = Integer.parseInt(id1);
        entity.setId(id);
        entity = dao.selectData(entity);
        dao.close();
        return createResponseData(CODE_SUCCESS, entity);
    }

    public Object log() {
        boolean log = NameValues.getBooleanConfig("log");
        if (log) {
            return save(0);
        }
        return createResponseData(CODE_SUCCESS);
    }

    public Object err() {
        boolean log = NameValues.getBooleanConfig("err");
        if (log) {
            return save(1);
        }
        return createResponseData(CODE_SUCCESS);
    }

    private ResponseData save(int type) {
        String devices = getParameter("devices");
        String log = getParameter("log");
        String time = getParameter("time");
        log = log.replaceAll("\r\n", "</br>")
                .replaceAll("\n", "</br>");
        String ip = getRequest().getRemoteAddr();
        LogDao dao = new LogDao();
        LogEntity data = new LogEntity();
        data.setTime(time);
        data.setDevices(devices);
        data.setIp(ip);
        data.setType(type);
        data.setLog(log);
        try {
            dao.add(data);
            return createResponseData(CODE_SUCCESS);
        } catch (Exception e) {
            return createResponseData(CODE_DATABASE_ERROR);
        } finally {
            dao.close();
        }
    }

    public Object showlog() throws ServletException, IOException, SQLException {
        String token = (String) getRequest().getSession().getAttribute("token");
        if (TOKEN.equals(token)) {
            show();
        } else {
            String password = getParameter("password");
            if (password == null || "".equals(password)) {
                goPage("/WEB-INF/html/checklog.html");
            } else {
                if ("9527".equals(password)) {
                    getRequest().getSession().setAttribute("token", TOKEN);
                    show();
                } else {
                    goPage("/WEB-INF/html/checklog.html");
                }
            }
        }
        return null;
    }

    private void show() {
        LogDao dao = new LogDao();
        try {
            String del_devices = getParameter("del_devices");
            if (del_devices != null && !"".equals(del_devices)) {
                dao.delete("devices", del_devices);
            }
            List<LogEntity> devices = dao.getGroup("devices");
            String devicesname = getParameter("devicesname");
            List<LogEntity> loglist = null;
            if (devicesname != null && !"".equals(devicesname)) {
                for (LogEntity entity : devices) {
                    if (devicesname.equals(entity.getDevices())) {
                        entity.setSelected(1);
                    }
                }
                loglist = dao.listGroupData("devices", devicesname);
            }
            dao.close();
            if (loglist == null) {
                loglist = new ArrayList<>();
            }
            getRequest().setAttribute("devicesname", devicesname);
            getRequest().setAttribute("logs", loglist);
            getRequest().setAttribute("devices", devices);
            goPage("/WEB-INF/jsp/showlog.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }


    /*public Object deletefile() throws SQLException, ServletException, IOException {
        String devicesname = getParameter("del_devices");
        LogDao dao = new LogDao();
        dao.delete("devices", devicesname);
        dao.close();
        show();
        return null;
    }*/

}
