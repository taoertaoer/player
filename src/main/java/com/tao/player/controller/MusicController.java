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
import com.tao.player.pojo.Music;
import com.tao.player.service.IMusicService;
import com.tao.player.service.impl.MusicService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/MusicController")
public class MusicController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IMusicService musicService = (IMusicService)new ProxyService().setTarget(new MusicService()).getProxy();

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
	
	protected void findMusicById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			Music music = musicService.findMusicById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",music);
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
	
	protected void addMusic(HttpServletRequest request, HttpServletResponse response) {
		int musicVisitors = Integer.parseInt(request.getParameter("musicVisitors"));
		int singerId = Integer.parseInt(request.getParameter("singerId"));
		String musicName = request.getParameter("musicName");
		String musicType = request.getParameter("musicType");
		AuthUser currenAuthUser = (AuthUser) request.getSession().getAttribute("currentUser");
		String musicPath = "";//TODO
		String lyricPath = "";//TODO
		Music music = new Music(musicName, musicVisitors, musicType, new Date(), null, musicPath, lyricPath, currenAuthUser.getUsername());
		try {
			musicService.addMusic(music);

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
	
	protected void deleteMusicById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			musicService.deleteMusicById(id);
			
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