package com.tao.player.controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.AuthUser;
import com.tao.player.pojo.Singer;
import com.tao.player.service.ISingerService;
import com.tao.player.service.impl.SingerService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/SingerController")
public class SingerController extends HttpServlet{
private static final long serialVersionUID=1L;

	private ISingerService singerService = (ISingerService)new ProxyService().setTarget(new SingerService()).getProxy();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodname = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodname, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);// 调用各自的方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void findSingerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			Singer singer = singerService.findSingerById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",singer);
			result.put("code", "02");
			System.out.println(result);
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void addSinger(HttpServletRequest request, HttpServletResponse response) {
		int singerVisitors = Integer.parseInt(request.getParameter("singerVisitors"));
		String singerName = request.getParameter("singerName");
		String singerSex = request.getParameter("singerSex");
		String singerBirthday = request.getParameter("singerBirthday");
		String hobby = request.getParameter("hobby");
		AuthUser currentAuthUser = (AuthUser) request.getSession().getAttribute("currentUser");
		Singer singer = new Singer(singerName, singerSex, singerBirthday, singerVisitors, hobby, null, new Date(), currentAuthUser.getUsername());
		try {
			singerService.addSinger(singer);

			JSONObject result = new JSONObject();
			result.put("code", "01");
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteSingerById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			singerService.deleteSingerById(id);
			
			JSONObject result = new JSONObject();
			result.put("code", "02");
			System.out.println(result);
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (SecurityException | IllegalArgumentException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}