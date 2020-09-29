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
import com.tao.player.pojo.MusicAlbumRelation;
import com.tao.player.service.IMusicAlbumRelationService;
import com.tao.player.service.impl.MusicAlbumRelationService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/MusicAlbumRelationController")
public class MusicAlbumRelationController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IMusicAlbumRelationService musicAlbumRelationService = (IMusicAlbumRelationService)new ProxyService().setTarget(new MusicAlbumRelationService()).getProxy();

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
	
	protected void findMusicAlbumRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			MusicAlbumRelation musicAlbumRelation = musicAlbumRelationService.findMusicAlbumRelationById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",musicAlbumRelation);
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
	
	protected void addMusicAlbumRelation(HttpServletRequest request, HttpServletResponse response) {
		int albumId = Integer.parseInt(request.getParameter("albumId"));
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		MusicAlbumRelation musicAlbumRelation = new MusicAlbumRelation(musicId, albumId);
		try {
			musicAlbumRelationService.addMusicAlbumRelation(musicAlbumRelation);

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
	
	protected void deleteMusicAlbumRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			musicAlbumRelationService.deleteMusicAlbumRelationById(id);
			
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