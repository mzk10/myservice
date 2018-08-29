package com.mh.myservice.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;

import com.mh.myservice.core.Action;

public class TestAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		return null;
	}

	public Object log() throws ServletException, IOException, SQLException {
		ServletInputStream is = getRequest().getInputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = is.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		byte[] byteArray = bos.toByteArray();
		String log = new String(byteArray, "UTF-8");

		/*
		 * // String log = getParameter("log"); String[] s = log.split(","); byte[] b =
		 * new byte[s.length]; for (int i = 0; i < s.length; i++) { try { b[i] = (byte)
		 * Integer.parseInt(s[i]); } catch (Exception e) { } } log = new String(b,
		 * "UTF-8");
		 */
		String ip = getRequest().getRemoteAddr();
		System.out.println("ip="+ip);
		System.out.println("log="+log);
		saveLog(log, "log_" + ip + ".log");
		return null;
	}

	public Object logv2() throws ServletException, IOException, SQLException {
		// ServletInputStream is = getRequest().getInputStream();
		// int len = 0;
		// byte[] buffer = new byte[1024];
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// while ((len = is.read(buffer)) != -1) {
		// bos.write(buffer, 0, len);
		// }
		// byte[] byteArray = bos.toByteArray();
		// String log = new String(byteArray, "UTF-8");
		// System.out.println(log);
		String devices = getParameter("devices");
		String msg = getParameter("log");
		// System.out.println(devices);
		// System.out.println(msg);
		String ip = getRequest().getRemoteAddr();
		saveLog(msg, devices + "_" + ip + ".log");
		return null;
	}

	public Object checktoken() throws ServletException, IOException, SQLException {
		String token = (String) getSession().getAttribute("token");
		if (token != null && "lksjaflkjoihg032opi4oigasdoihvb9wa".equals(token)) {
			sendRedirect("test?app=showlog");
			return null;
		}

		String key = getParameter("key");
		if (key != null && "95279527".equals(key)) {
			sendRedirect("checktoken.html");
		}
		return null;
	}

	public Object dochecktoken() throws ServletException, IOException, SQLException {
		String key = getParameter("key");
		if (key != null && "95279527".equals(key)) {
			getSession().setAttribute("token", "lksjaflkjoihg032opi4oigasdoihvb9wa");
			sendRedirect("test?app=showlog");
		} else {
			sendRedirect("checktoken.html");
		}
		return null;
	}

	public Object showlog() throws ServletException, IOException, SQLException {
//		String token = (String) getSession().getAttribute("token");
//		if (token == null || "".equals(token)) {
//			sendRedirect("checktoken.html");
//		} else {
			//sendRedirect("showlog.html");
			String file = loadFileString("showlog.html");
//		}
		return file;
	}

	public Object getfilelist() throws ServletException, IOException, SQLException {
//		String token = (String) getSession().getAttribute("token");
//		if (token != null && "lksjaflkjoihg032opi4oigasdoihvb9wa".equals(token)) {
			File file = new File("E:/log");
			if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
			}
			File[] listFiles = file.listFiles();
			if (listFiles != null) {
				List<String> list = new ArrayList<>();
				for (File file2 : listFiles) {
					// Log log = new Log();
					// log.setLog(file2.getPath());
					// log.setName(file2.getName());
					// String content = loadFileString(file2.getPath());
					// log.setLog(content);
					list.add(file2.getName());
				}
				return createResponseData(200, list);
			}
//		}
		return createResponseData(203, null);
	}

	public Object getlogfile() throws ServletException, IOException, SQLException {
//		String token = (String) getSession().getAttribute("token");
//		if (token != null && "lksjaflkjoihg032opi4oigasdoihvb9wa".equals(token)) {
			String name = getParameter("filename");
			File file = new File("E:/log");
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
						
						while(br.ready()) {
							sb.append(br.readLine()+"</br>");
						}
						br.close();
						isr.close();
						fis.close();
						return createResponseData(200, sb.toString());
					}
				}
			}
//		}
		return createResponseData(203, null);
	}

	private synchronized static void saveLog(final String msg, final String name) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				File dir = new File("E:/log/");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File file = new File(dir, name);
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
