package com.mh.myservice.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AuthCode {
	public static final int AUTHCODE_LENGTH = 4; // 验证码长度
	public static final int SINGLECODE_WIDTH = 10; // 单个验证码宽度
	public static final int SINGLECODE_HEIGHT = 20; // 单个验证码高度
	public static final int SINGLECODE_GAP = 4; // 单个验证码之间间隔
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP) + 10;
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT + 6;

	public static String getAuthCode() {
		String authCode = "";
		for (int i = 0; i < AUTHCODE_LENGTH; i++) {
			authCode += (new Random()).nextInt(10);
		}
		return authCode;
	}

	public static BufferedImage getAuthImg(String authCode) {
		// 设置图片的高、宽、类型
		// RGB编码：red、green、blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
		// 得到图片上的一个画笔
		Graphics g = img.getGraphics();
		// 设置画笔的颜色，用来做背景色
		g.setColor(new Color(111, 171, 191));
		// 用画笔来填充一个矩形，矩形的左上角坐标，宽，高
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// 将画笔颜色设置为黑色，用来写字
		g.setColor(Color.BLACK);
		// 设置字体：宋体、不带格式的、字号
		g.setFont(new Font("华文新魏", Font.PLAIN, SINGLECODE_HEIGHT + 5));

		// 输出数字
		char c;
		int start = (IMG_WIDTH/2)-((SINGLECODE_WIDTH + SINGLECODE_GAP)*2);
		for (int i = 0; i < authCode.toCharArray().length; i++) {
			// 取到对应位置的字符
			c = authCode.charAt(i);
			g.drawString(
					c + "", 
					start,
					(IMG_HEIGHT + SINGLECODE_HEIGHT) / 2
					);
			start = start+SINGLECODE_WIDTH + SINGLECODE_GAP;
		}
		Random random = new Random();
		// 干扰素
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}
		return img;
	}
}