package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.TestLogDao;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.entity.TestLogEntity;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestAction extends Action {

    private static final String TOKEN = "weoiurwoieurtiowutoiuer";

    @Override
    public Object deFault() throws IOException {
        getResponse().sendRedirect("test.apk");
        return null;
    }

    public Object log() {
        return save(0);
    }

    public Object err() {
        return save(1);
    }

    private ResponseData save(int type) {
        String devices = getParameter("devices");
        String log = getParameter("log");
        String time = getParameter("time");
        log = log.replaceAll("\r\n", "</br>")
                .replaceAll("\n", "</br>");
        String ip = getRequest().getRemoteAddr();
        TestLogDao dao = new TestLogDao();
        TestLogEntity data = new TestLogEntity();
        data.setTime(time);
        data.setDevices(devices);
        data.setIp(ip);
        data.setType(type);
        data.setLog(log);
        try {
            dao.add(data);
            dao.close();
            return createResponseData(200, null);
        } catch (SQLException e) {
            return createResponseData(203, null);
        } catch (UnsupportedEncodingException e) {
            return createResponseData(203, null);
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

    private void show() throws ServletException, IOException, SQLException {
        TestLogDao dao = new TestLogDao();
        String del_devices = getParameter("del_devices");
        if (del_devices!=null && !"".equals(del_devices)){
            dao.delete("devices", del_devices);
        }
        List<TestLogEntity> devices = dao.getGroup("devices");
        String devicesname = getParameter("devicesname");
        List<TestLogEntity> loglist = null;
        if (devicesname != null && !"".equals(devicesname)) {
            for (TestLogEntity entity : devices) {
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
    }

    /*public Object getloglist() {
        TestLogDao dao = new TestLogDao();
        try {
            List<TestLogEntity> devices = dao.getGroup("devices");
            List<String> list = new ArrayList<>();
            for (TestLogEntity entity : devices) {
                list.add(entity.getDevices());
            }
            return createResponseData(200, list);
        } catch (SQLException e) {
            return createResponseData(203, null);
        } finally {
            dao.close();
        }
    }*/

    /*public Object getlogfile() {
        String name = getParameter("filename");
        TestLogDao dao = new TestLogDao();
        try {
            List<TestLogEntity> devices = dao.listGroupData("devices", name);
            return createResponseData(200, devices);
        } catch (SQLException e) {
            return createResponseData(203, "");
        } finally {
            dao.close();
        }
    }*/


    public Object deletefile() throws SQLException, ServletException, IOException {
        String devicesname = getParameter("del_devices");
        TestLogDao dao = new TestLogDao();
        dao.delete("devices", devicesname);
        dao.close();
        show();
        return null;
    }

}
