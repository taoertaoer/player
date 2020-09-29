package com.tao.player.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ImageServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("refresh", "10");
		BufferedImage image = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 100, 25);
		g.setColor(Color.blue);
		g.setFont(new Font(null, Font.BOLD, 30));
		String num = makeNum();
		System.out.println("num==="+num);
		request.getSession().setAttribute("verifyNum", num);
		g.drawString(num, 0, 25);
		
		response.setContentType("image/jpeg");
		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		ImageIO.setUseCache(false);
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	private String makeNum() {
		Random random = new Random();
		String num =random.nextInt(999999)+"";
		StringBuffer sb =new StringBuffer();
		for (int i = 0; i < 6-num.length(); i++) {
			sb.append("0");
		}
		return sb.toString()+num;
	}
}
