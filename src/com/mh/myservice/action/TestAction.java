package com.mh.myservice.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.mh.myservice.core.Action;

public class TestAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		getResponse().sendRedirect("test.apk");
		return null;
	}

	public Object log() throws ServletException, IOException, SQLException {
		String devices = getParameter("devices");
		String log = getParameter("log");
		String ip = getRequest().getRemoteAddr();
		String realPath = getServletContext().getRealPath("log");
		saveLog(realPath, log, devices + "_" + ip + ".log");
		return null;
	}

	public Object err() throws ServletException, IOException, SQLException {
		String devices = getParameter("devices");
		String log = getParameter("log");
		String ip = getRequest().getRemoteAddr();
		String realPath = getServletContext().getRealPath("err");
		saveLog(realPath, log, devices + "_" + ip + ".err");
		return createResponseData(200, null);
	}

	public Object showlog() throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = getRequest().getRequestDispatcher("/WEB-INF/html/showlog.html");
		dispatcher.forward(getRequest(), getResponse());
		return null;
	}

	public Object getloglist() throws ServletException, IOException, SQLException {
		String realPath = getServletContext().getRealPath("log");
		File file = new File(realPath);
		if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
		}
		File[] listFiles = file.listFiles();
		if (listFiles != null) {
			List<String> list = new ArrayList<>();
			for (File file2 : listFiles) {
				list.add(file2.getName());
			}
			return createResponseData(200, list);
		}
		return createResponseData(203, null);
	}
	
	public Object geterrlist() throws ServletException, IOException, SQLException {
		String realPath = getServletContext().getRealPath("err");
		File file = new File(realPath);
		if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
		}
		File[] listFiles = file.listFiles();
		if (listFiles != null) {
			List<String> list = new ArrayList<>();
			for (File file2 : listFiles) {
				list.add(file2.getName());
			}
			return createResponseData(200, list);
		}
		return createResponseData(203, null);
	}

	public Object getlogfile() throws ServletException, IOException, SQLException {
		String name = getParameter("filename");
		String realPath = getServletContext().getRealPath("log");
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
		return createResponseData(203, null);
	}
	
	public Object geterrfile() throws ServletException, IOException, SQLException {
		String name = getParameter("filename");
		String realPath = getServletContext().getRealPath("err");
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
