package com.mh.myservice.core;

import com.google.gson.Gson;
import com.mh.myservice.entity.FileInfoEntity;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.util.NameValues;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import java.io.*;

public abstract class Action extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected static final int CODE_SUCCESS = 200; //成功
    protected static final int CODE_EMPTY_USER = 201; //用户名和密码不可为空
    protected static final int CODE_WRONG_USER = 202; //用户名或密码错误
    protected static final int CODE_DATABASE_ERROR = 203; //数据库错误
    protected static final int CODE_SESSION_ERROR = 204; //session错误
    protected static final int CODE_404 = 404; //找不到页面
    protected static final int CODE_UNKNOW = 100; //未知错误

    private HttpServletRequest request;
    private HttpServletResponse response;

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        String app = req.getParameter("app");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
		/*{
			StringBuffer sb = new StringBuffer();
			String addr = req.getRemoteAddr();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
			String time_format = format.format(date);
			sb.append("\n时间：\t");
			sb.append(time_format);
			sb.append("\n地址：\t");
			sb.append(addr);
			sb.append("\n接口：\t");
			sb.append(req.getRequestURL().toString());
			if (app!=null && !"".equals(app)){
				sb.append("?app="+app);
			}
			System.out.println(sb.toString());
		}*/
        PrintWriter w = null;
        if (app == null || "".equals(app)) {
            Object result = null;
            try {
                result = deFault();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (result != null && !"".equals(result)) {
                Gson gson = new Gson();
                String json = gson.toJson(result);
                w = resp.getWriter();
                w.print(json);
            }
            return;
        }
        try {
            Method method = this.getClass().getMethod(app);
            Object result = method.invoke(this);
            if (result != null) {
                if (result instanceof String) {
                    w = resp.getWriter();
                    w.print(result);
                } else {
                    Gson gson = new Gson();
                    String json = gson.toJson(result);
                    w = resp.getWriter();
                    w.print(json);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (w != null) {
                w.close();
            }
        }
    }

    protected String getParameter(String name) {
        return request.getParameter(name);
    }

//	public PrintWriter getWriter() throws IOException{
//		return this.response.getWriter();
//	}

//	public void write(String data) throws IOException{
//		PrintWriter w = this.response.getWriter();
//		w.print(data);
//		w.flush();
//		w.close();
//	}

//	public void sendRedirect(String str){
//		try {
//			response.sendRedirect(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

    protected HttpSession getSession() {
        return request.getSession();
    }

    protected void goPage(String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    public abstract Object deFault() throws ServletException, IOException, SQLException;

    /**
     * 接收上传的文件
     *
     * @param field 文件参数名，如果为空，则获取全部文件
     * @param dir   保存路径，如果为空，则保存至默认路径
     * @return 文件属性
     */
    protected List<FileInfoEntity> uploadFile(String field, String dir) throws FileUploadException, IOException {
        String realDir = getRequest().getServletContext().getRealPath(NameValues.getStringConfig(NameValues.DEFAULT_UPLOAD_PATH) + (dir == null ? "" : dir));
        File file = new File(realDir);
        if (!file.exists()) {
            boolean mkdir = file.mkdirs();
            if (mkdir) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> list = upload.parseRequest(getRequest());
                if (list != null) {
                    List<FileInfoEntity> resultList = new ArrayList<>();
                    System.out.println("文件数为:" + list.size());
                    for (FileItem fileItem : list) {
                        String fieldName = fileItem.getFieldName();
                        if (field != null && !"".equals(field) && !field.equals(fieldName)) {
                            continue;
                        }
                        long size = fileItem.getSize();
                        if (size <= 0) {
                            continue;
                        }
                        String name = fileItem.getName();
                        System.out.println("fieldName=" + fieldName + ";name=" + name + ";size=" + fileItem.getSize());
                        InputStream is = fileItem.getInputStream();
                        int len;
                        byte[] buffer = new byte[2048];
                        File path = new File(realDir, System.currentTimeMillis() + "_" + name);
                        FileOutputStream fos = new FileOutputStream(path);
                        while ((len = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        is.close();
                        FileInfoEntity info = new FileInfoEntity();
                        info.setFiledir(dir == null ? "" : dir);
                        info.setFilename(name);
                        info.setFilesize(size);
                        resultList.add(info);
                    }
                    return resultList;
                }
            }
        }

        return null;
    }

//	/**
//	 * 获得客户端IP地址
//	 *
//	 * @return
//	 */
//	public String getRemoteAddress() {
//
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}

//    /**
//     * 获得客户端MAC地址
//     *
//     * @param ip
//     * @return
//     */
//    public String getMACAddress(String ip) {
//        String str = "";
//        String macAddress = "";
//        try {
//            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
//            InputStreamReader ir = new InputStreamReader(p.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            for (int i = 1; i < 100; i++) {
//                str = input.readLine();
//                if (str != null) {
//                    if (str.indexOf("MAC Address") > 1) {
//                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
//                        break;
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace(System.out);
//        }
//        return macAddress;
//    }

    protected ResponseData createResponseData(int code) {
        return ResponseData.create(code, null);
    }

    protected ResponseData createResponseData(int code, Object data) {
        return ResponseData.create(code, data);
    }


//	public void jumpJsp(String jsp) throws ServletException, IOException{
//		request.getRequestDispatcher("/WEB-INF/jsp/"+jsp).forward(request, response);
//	}
//	public File loadFile(String path)
//    {
//        String realPath = getRequest().getServletContext().getRealPath(path);
//        return new File(realPath);
//    }
//
//	public String loadFileString(String path)
//	{
//		String realPath = getRequest().getServletContext().getRealPath(path);
//		File file = new File(realPath);
//		String text = Util.loadTextOfFile(file);
//		return text;
//	}

}
