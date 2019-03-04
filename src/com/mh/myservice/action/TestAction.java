package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.LogDao;
import com.mh.myservice.entity.LogEntity;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.util.AuthCode;
import com.mh.myservice.util.ConfigUtil;
import com.mh.myservice.util.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        return createResponseData(CODE_SUCCESS, entity);
    }

    /**
     * 提交普通日志
     *
     * @return
     */
    public Object log(String devices, String log, String time
    ) {
        boolean isSaveLog = ConfigUtil.getBooleanConfig("isSaveLog");
        if (isSaveLog) {
            return save(0, devices, log, time);
        }
        return createResponseData(CODE_SUCCESS);
    }

    /**
     * 提交错误日志
     *
     * @return
     */
    public Object err(String devices, String log, String time) {
        boolean isSaveErr = ConfigUtil.getBooleanConfig("isSaveErr");
        if (isSaveErr) {
            return save(1, devices, log, time);
        }
        return createResponseData(CODE_SUCCESS);
    }

    private ResponseData save(int type, String devices, String log, String time) {
        /*String devices = getParameter("devices");
        String log = getParameter("log");
        String time = getParameter("time");*/
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
        }
    }

    /**
     * 显示日志页面
     *
     * @param password
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object showlog(String password, String del_devices, String devicesname) throws ServletException, IOException {
        String token = (String) getRequest().getSession().getAttribute("token");
        if (TOKEN.equals(token)) {
            show(del_devices, devicesname);
        } else {
            //String password = getParameter("password");
            if (StringUtil.isEmpty(password)) {
                goPage("/WEB-INF/html/checklog.html");
            } else {
                if ("9527".equals(password)) {
                    getRequest().getSession().setAttribute("token", TOKEN);
                    show(del_devices, devicesname);
                } else {
                    goPage("/WEB-INF/html/checklog.html");
                }
            }
        }
        return null;
    }

    private void show(String del_devices, String devicesname) {
        LogDao dao = new LogDao();
        try {
            //String del_devices = getParameter("del_devices");
            if (!StringUtil.isEmpty(del_devices)) {
                dao.delete("devices", del_devices);
            }
            List<LogEntity> devices = dao.getGroup("devices");
            //String devicesname = getParameter("devicesname");
            List<LogEntity> loglist = null;
            if (!StringUtil.isEmpty(devicesname)) {
                for (LogEntity entity : devices) {
                    if (devicesname.equals(entity.getDevices())) {
                        entity.setSelected(1);
                    }
                }
                loglist = dao.listGroupData("devices", devicesname);
            }
            if (loglist == null) {
                loglist = new ArrayList<>();
            }
            getRequest().setAttribute("devicesname", devicesname);
            getRequest().setAttribute("logs", loglist);
            getRequest().setAttribute("devices", devices);
            goPage("/WEB-INF/jsp/showlog.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证码测试
     *
     * @return
     */
    public Object getAuthImg() throws IOException {
        String authCode = AuthCode.getAuthCode();
        BufferedImage authImg = AuthCode.getAuthImg(authCode);
        getResponse().addHeader("content-type", "image/png");
        ImageIO.write(authImg, "PNG", getResponse().getOutputStream());
        return null;
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
