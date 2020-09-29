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
import com.tao.player.pojo.SingerAlbumRelation;
import com.tao.player.service.ISingerAlbumRelationService;
import com.tao.player.service.impl.SingerAlbumRelationService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/SingerAlbumRelationController")
public class SingerAlbumRelationController extends HttpServlet{
private static final long serialVersionUID=1L;

	private ISingerAlbumRelationService singerAlbumRelationService = (ISingerAlbumRelationService)new ProxyService().setTarget(new SingerAlbumRelationService()).getProxy();

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
	
	protected void findSingerAlbumRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			SingerAlbumRelation singerAlbumRelation = singerAlbumRelationService.findSingerAlbumRelationById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",singerAlbumRelation);
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
	
	protected void addSingerAlbumRelation(HttpServletRequest request, HttpServletResponse response) {
		int singerId = Integer.parseInt(request.getParameter("singerId"));
		int albumId = Integer.parseInt(request.getParameter("albumId"));
		SingerAlbumRelation singerAlbumRelation = new SingerAlbumRelation(singerId, albumId);
		try {
			singerAlbumRelationService.addSingerAlbumRelation(singerAlbumRelation);

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
	
	protected void deleteSingerAlbumRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			singerAlbumRelationService.deleteSingerAlbumRelationById(id);
			
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