package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.TestLogDao;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.entity.TestLogEntity;
import com.mh.myservice.util.Base64;

import javax.servlet.ServletException;
import java.io.*;
import java.nio.charset.StandardCharsets;
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

    private ResponseData save(int type){
        String devices = getParameter("devices");
        String log = getParameter("log");
        String ip = getRequest().getRemoteAddr();

        TestLogDao dao = new TestLogDao();
        TestLogEntity data = new TestLogEntity();
        data.setTime(System.currentTimeMillis());
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
        }
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
        }finally {
            dao.close();
        }
    }

    public Object getlogfile() throws IOException {
        String name = getParameter("filename");
        TestLogDao dao = new TestLogDao();
        try {
            List<TestLogEntity> devices = dao.listGroupData("devices", name);
            StringBuilder sb = new StringBuilder();
            for (TestLogEntity entity : devices) {
                sb.append(entity.getLog());
                sb.append("</br>");
            }
            return createResponseData(200, sb.toString());
        } catch (SQLException e) {
            return createResponseData(203, "");
        }finally {
            dao.close();
        }
        /*String realPath = getServletContext().getRealPath("log");
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
        return createResponseData(200, "");*/
    }

    public Object deletefile() {
        /*String name = getParameter("filename");
        System.out.println("filename=" + name);
        if (name != null && !"".equals(name)) {
            String log = getServletContext().getRealPath("log");
            File file = new File(log, name);
            if (file.exists() && file.delete()) {
                return getloglist();
            }
        }
        return createResponseData(203, null);*/
        String name = getParameter("filename");
        TestLogDao dao = new TestLogDao();
        try {
            dao.delete("devices", name);
            return getloglist();
        } catch (SQLException e) {
            return createResponseData(203, null);
        }finally {
            dao.close();
        }
    }

    public Object getBase(){
        String image = getParameter("image");
        String image1 = getServletContext().getRealPath("image");
        File file = new File(image1);
        boolean mkdirs = false;
        if (!(file.exists() && file.isDirectory())){
            mkdirs = file.mkdirs();
        }
        if (mkdirs){
            String path = new File(image1, "test.jpg").getPath();
            base64ToFile(image, path);
            return createResponseData(200, path);
        }
        return createResponseData(100, null);
    }

    //++++++++++++++++++++++++以上为接口++++++++++++++++++++++++++++
    //++++++++++++++++++++++++以下为工具++++++++++++++++++++++++++++

    private synchronized static void saveLog(final String dir, final String msg, final String name) {
        File file_dir = new File(dir);
        if (file_dir.exists() || file_dir.mkdirs()) {
            File file = new File(file_dir, name);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file, true);
                byte[] b = msg.getBytes(StandardCharsets.UTF_8);
                fos.write(b);
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * base64字符串转文件
     * @param base64
     * @return
     */
    public static File base64ToFile(String base64, String path) {
        File file = null;
        //String fileName = "/Petssions/record/testFile.amr";
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(path);
            if (!file.exists())
                file.createNewFile();
            //byte[] bytes = Base64.decodeBase64(base64);
            byte[] bytes = Base64.decode(base64);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
    }

}
