package com.mh.myservice.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.mh.myservice.core.Action;
import com.mh.myservice.db.dao.RootuserDao;
import com.mh.myservice.entity.ResponseData;
import com.mh.myservice.entity.Rootuser;
import com.mh.myservice.util.AuthCode;

public class RootuserAction extends Action{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object deFault() throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object auth() throws ServletException, IOException, SQLException {
		String authCode = AuthCode.getAuthCode();
		getSession().setAttribute("authCode", authCode);
		BufferedImage authImg = AuthCode.getAuthImg(authCode);
		ImageIO.write(authImg, "PNG", getResponse().getOutputStream());
		return null;
	}
	
	public Object doregist() throws ServletException, IOException, SQLException {
		HttpSession session = getSession();
		String attribute = (String) session.getAttribute("authCode");
		String authcode = getParameter("authcode");
		if (attribute.equals(authcode)) {
			String username = getParameter("username");
			String password = getParameter("password");
			
			RootuserDao dao = new RootuserDao();
			Rootuser data = new Rootuser();
			data.setUsername(username);
			data.setPassword(password);
			boolean add = dao.add(data);
			if (add) {
				//成功
				session.setAttribute("username", username);
				return "成功200";
			}else{
				return "用户名重复";
			}
		}else{
			return "验证码不正确";
		}
		
	}

}
