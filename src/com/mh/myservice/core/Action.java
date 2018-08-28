package com.mh.myservice.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.util.NameValues;
import com.mh.myservice.util.Util;

public abstract class Action extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
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
//		{
//			StringBuffer sb = new StringBuffer();
//			String addr = req.getRemoteAddr();
//			Date date = new Date();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
//			String time_format = format.format(date);
//			sb.append("\n时间：\t");
//			sb.append(time_format);
//			sb.append("\n地址：\t");
//			sb.append(addr);
//			sb.append("\n接口：\t");
//			sb.append(req.getRequestURL().toString());
//			if (app!=null && !"".equals(app)){
//				sb.append("?app="+app);
//			}
//			System.out.println(sb.toString());
//		}
		PrintWriter w = null;
		if (app==null || "".equals(app)) {
			Object result = null;
			try {
				result = deFault();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (result!=null && !"".equals(result)) {
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
			if (result!=null) {
				if (result instanceof String) {
					w = resp.getWriter();
					w.print(result);
				}else {
					Gson gson = new Gson();
					String json = gson.toJson(result);
					w = resp.getWriter();
					w.print(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (w!=null) {
				w.close();
			}
		}
	}
	
	public String getParameter(String name){
		String parameter = this.request.getParameter(name);
		return parameter;
	}
	
	public PrintWriter getWriter() throws IOException{
		return this.response.getWriter();
	}
	
	public void write(String data) throws IOException{
		PrintWriter w = this.response.getWriter();
		w.print(data);
		w.flush();
		w.close();
	}
	
	public void sendRedirect(String str){
		try {
			response.sendRedirect(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HttpSession getSession()
	{
		HttpSession session = getRequest().getSession();
		return session;
	}
	
    public void getRequestDispatcher(String path, String key, Object value) throws ServletException, IOException
    {
        request.setAttribute(key, value);
        request.getRequestDispatcher(path).forward(request, response);
    }
	
	public abstract Object deFault() throws ServletException, IOException, SQLException;
	
	/**
	 * 接收上传的文件
	 * @param field 文件参数名，如果为空，则获取全部文件
	 * @param dir 保存路径，如果为空，则保存至默认路径
	 * @return 文件属性
	 * @throws FileUploadException
	 * @throws IOException
	 */
	public List<FileInfo> uploadFile(String field, String dir) throws FileUploadException, IOException{
		String realDir = getRequest().getServletContext().getRealPath(NameValues.getConfig(NameValues.DEFAULT_UPLOAD_PATH)+(dir==null?"":dir));
		File file = new File(realDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = upload.parseRequest(getRequest());
		if (list!=null) {
			List<FileInfo> resultList = new ArrayList<FileInfo>();
			System.out.println("文件数为:"+list.size());
			for (int i = 0; i < list.size(); i++) {
				FileItem fileItem = list.get(i);
				String fieldName = fileItem.getFieldName();
				if (field!=null && !"".equals(field) && !field.equals(fieldName)) {
					continue;
				}
				long size = fileItem.getSize();
				if (size<=0) {
					continue;
				}
				String name = fileItem.getName();
				System.out.println("fieldName="+fieldName+";name="+name+";size="+fileItem.getSize());
				InputStream is = fileItem.getInputStream();
				int len = 0;
				byte[] buffer = new byte[2048];
				File path = new File(realDir,System.currentTimeMillis()+"_"+name);
				FileOutputStream fos = new FileOutputStream(path);
				while((len = is.read(buffer))!=-1){
					fos.write(buffer, 0, len);
				}
				fos.flush();
				fos.close();
				is.close();
				FileInfo info = new FileInfo();
				info.filedir = dir;
				info.filename = name;
				info.filesize = size;
				resultList.add(info);
			}
			return resultList;
		}
		return null;
	}
	
	public class FileInfo{
		private String filedir;
		private String filename;
		private long filesize;
		public String getFiledir() {
			return filedir;
		}
		public void setFiledir(String filedir) {
			this.filedir = filedir;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public long getFilesize() {
			return filesize;
		}
		public void setFilesize(long filesize) {
			this.filesize = filesize;
		}
	}
	
	/**
	 * 获得客户端IP地址
	 * 
	 * @return
	 */
	public String getRemoteAddress() {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得客户端MAC地址
	 * 
	 * @param ip
	 * @return
	 */
	public String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
	
	public ResponseData createResponseData(int code, Object data){
		return ResponseData.create(code, data);
	}
	
	
	public void jumpJsp(String jsp) throws ServletException, IOException{
		getRequest().getRequestDispatcher("/WEB-INF/jsp/"+jsp).forward(getRequest(), getResponse());
	}
	
	public File loadFile(String path)
    {
        String realPath = getRequest().getServletContext().getRealPath(path);
        return new File(realPath);
    }
	
	public String loadFileString(String path)
	{
		String realPath = getRequest().getServletContext().getRealPath(path);
		File file = new File(realPath);
		String text = Util.loadTextOfFile(file);
		return text;
	}
	
}
