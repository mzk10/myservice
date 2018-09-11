package com.mh.myservice.action;

import com.mh.myservice.core.Action;

import javax.servlet.ServletException;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestAction extends Action {

    private static final String TOKEN = "weoiurwoieurtiowutoiuer";

    @Override
    public Object deFault() throws IOException {
        getResponse().sendRedirect("test.apk");
        return null;
    }

    public Object log() throws IOException {
        String devices = getParameter("devices");
        String log = getParameter("log");
        String ip = getRequest().getRemoteAddr();
        String realPath = getServletContext().getRealPath("log");
        saveLog(realPath, URLDecoder.decode(log, "UTF-8"), devices + "_" + ip + ".log");
        return null;
    }

    public Object err() {
        String devices = getParameter("devices");
        String log = getParameter("log");
        String ip = getRequest().getRemoteAddr();
        String realPath = getServletContext().getRealPath("log");
        saveLog(realPath, log, devices + "_" + ip + ".err");
        return createResponseData(200, null);
    }

    public Object showlog() throws ServletException, IOException {
        String token = (String) getRequest().getSession().getAttribute("token");
        if (TOKEN.equals(token)) {
            goPage("/WEB-INF/html/showlog.html");
        } else {
            String password = getParameter("password");
            if (password == null || "".equals(password)) {
                goPage("/WEB-INF/html/checklog.html");
            } else {
                if ("9527".equals(password)) {
                    getRequest().getSession().setAttribute("token", TOKEN);
                    goPage("/WEB-INF/html/showlog.html");
                } else {
                    goPage("/WEB-INF/html/checklog.html");
                }
            }
        }
        return null;
    }

    public Object getloglist() {
        String realPath = getServletContext().getRealPath("log");
        File file = new File(realPath);
        if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
        }
        File[] listFiles = file.listFiles();
        List<String> list = new ArrayList<>();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                list.add(file2.getName());
            }
        }
        return createResponseData(200, list);
    }

    public Object getlogfile() throws IOException {
        String realPath = getServletContext().getRealPath("log");
        String name = getParameter("filename");
        File file = new File(realPath);
        if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                String filename = file2.getName();
                if (filename.equals(name)) {
                    StringBuilder sb = new StringBuilder();
                    FileInputStream fis = new FileInputStream(file2);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    while (br.ready()) {
                        sb.append(br.readLine() + "</br>");
                    }
                    br.close();
                    isr.close();
                    fis.close();
                    return createResponseData(200, sb.toString());
                }
            }
        }
        return createResponseData(200, "");
    }

    public Object deletefile() {
        String name = getParameter("filename");
        System.out.println("filename=" + name);
        if (name != null && !"".equals(name)) {
            String log = getServletContext().getRealPath("log");
            File file = new File(log, name);
            if (file.exists() && file.delete()) {
                return getloglist();
            }
        }
        return createResponseData(203, null);
    }

    private synchronized static void saveLog(final String dir, final String msg, final String name) {
        File file_dir = new File(dir);
        if (!file_dir.exists()) {
            boolean mkdirs = file_dir.mkdirs();
            if (mkdirs) {
                File file = new File(file_dir, name);
                try (FileOutputStream fos = new FileOutputStream(file, true)) {
                    byte[] b = msg.getBytes(StandardCharsets.UTF_8);
                    fos.write(b);
                    fos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
