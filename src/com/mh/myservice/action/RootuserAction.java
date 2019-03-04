package com.mh.myservice.action;

import com.mh.myservice.core.Action;
import com.mh.myservice.util.AuthCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class RootuserAction extends Action {

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

}
