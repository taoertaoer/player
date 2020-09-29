package com.tao.player.controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.SingerMusicRelation;
import com.tao.player.service.ISingerMusicRelationService;
import com.tao.player.service.impl.SingerMusicRelationService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/SingerMusicRelationController")
public class SingerMusicRelationController extends HttpServlet{
private static final long serialVersionUID=1L;

	private ISingerMusicRelationService singerMusicRelationService = (ISingerMusicRelationService)new ProxyService().setTarget(new SingerMusicRelationService()).getProxy();

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
	
	protected void findSingerMusicRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			SingerMusicRelation singerMusicRelation = singerMusicRelationService.findSingerMusicRelationById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",singerMusicRelation);
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
	
	protected void addSingerMusicRelation(HttpServletRequest request, HttpServletResponse response) {
		int singerId = Integer.parseInt(request.getParameter("singerId"));
		int musicId = Integer.parseInt(request.getParameter("singerId"));
		SingerMusicRelation singerMusicRelation = new SingerMusicRelation(singerId, musicId);
		try {
			singerMusicRelationService.addSingerMusicRelation(singerMusicRelation);

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
	
	protected void deleteSingerMusicRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			singerMusicRelationService.deleteSingerMusicRelationById(id);
			
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