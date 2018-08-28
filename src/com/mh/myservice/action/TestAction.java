package com.mh.myservice.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

		/*// String log = getParameter("log");
		String[] s = log.split(",");
		byte[] b = new byte[s.length];
		for (int i = 0; i < s.length; i++) {
			try {
				b[i] = (byte) Integer.parseInt(s[i]);
			} catch (Exception e) {
			}
		}
		log = new String(b, "UTF-8");*/
		String ip = getRequest().getRemoteAddr();
		saveLog(log, "log_" + ip + ".log");
		return null;
	}
	
	
	public Object logv2() throws ServletException, IOException, SQLException {
//		ServletInputStream is = getRequest().getInputStream();
//		int len = 0;
//		byte[] buffer = new byte[1024];
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		while ((len = is.read(buffer)) != -1) {
//			bos.write(buffer, 0, len);
//		}
//		byte[] byteArray = bos.toByteArray();
//		String log = new String(byteArray, "UTF-8");
//		System.out.println(log);
		String devices = getParameter("devices");
		String msg = getParameter("log");
//		System.out.println(devices);
//		System.out.println(msg);
		String ip = getRequest().getRemoteAddr();
		saveLog(msg, devices + "_" + ip + ".log");
		return null;
	}
	

	private synchronized static void saveLog(final String msg, final String name) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				File dir = new File("D:/log/");
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
