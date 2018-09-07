package com.mh.myservice.action;

import com.mh.myservice.core.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class TestAction extends Action {

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

    private static final String TOKEN = "weoiurwoieurtiowutoiuer";

    public Object showlog() throws ServletException, IOException {
        String token = (String) getRequest().getSession().getAttribute("token");
        if (TOKEN.equals(token)){
            checklog("/WEB-INF/html/showlog.html");
        }else{
            String password = getParameter("password");
            if (password==null || "".equals(password)){
                checklog("/WEB-INF/html/checklog.html");
            }else {
                if ("9527".equals(password)){
                    getRequest().getSession().setAttribute("token", TOKEN);
                    checklog("/WEB-INF/html/showlog.html");
                }else{
                    checklog("/WEB-INF/html/checklog.html");
                }
            }
        }
        return null;
    }

    private void checklog(String s) throws ServletException, IOException {
        RequestDispatcher dispatcher = getRequest().getRequestDispatcher(s);
        dispatcher.forward(getRequest(), getResponse());
    }

    public Object getloglist() {
        String realPath = getServletContext().getRealPath("log");
        return getFileList(realPath);
    }

    /*public Object geterrlist() {
        String realPath = getServletContext().getRealPath("err");
        return getFileList(realPath);
    }*/

    private Object getFileList(String realPath) {
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
        return getFileText(realPath);
    }

    /*public Object geterrfile() throws IOException {
        String realPath = getServletContext().getRealPath("err");
        return getFileText(realPath);
    }*/

    private Object getFileText(String realPath) throws IOException {
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
        if (name!=null && !"".equals(name)){
            String log = getServletContext().getRealPath("log");
            File file = new File(log, name);
            if (file.exists() && file.delete()){
                return getloglist();
            }
        }
        return createResponseData(203, null);
    }

    private synchronized static void saveLog(final String dir, final String msg, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file_dir = new File(dir);
                if (!file_dir.exists()) {
                    file_dir.mkdirs();
                }
                File file = new File(file_dir, name);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file, true);
                    byte[] b = msg.getBytes("utf-8");
                    fos.write(b);
                    fos.flush();
                } catch (Exception e) {
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }).start();
    }

}
