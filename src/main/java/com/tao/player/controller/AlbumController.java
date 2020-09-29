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
import com.tao.player.pojo.Album;
import com.tao.player.pojo.AuthUser;
import com.tao.player.service.IAlbumService;
import com.tao.player.service.impl.AlbumService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/AlbumController")
public class AlbumController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IAlbumService albumService = (IAlbumService)new ProxyService().setTarget(new AlbumService()).getProxy();

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
	
	protected void findAlbumById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			Album album = albumService.findAlbumById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",album);
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
	
	protected void addAlbum(HttpServletRequest request, HttpServletResponse response) {
		int albumVisitors = Integer.parseInt(request.getParameter("albumVisitors"));
		String albumName = request.getParameter("albumName");
		String albumDescription = request.getParameter("albumDescription");
		AuthUser currentAuthUser = (AuthUser) request.getSession().getAttribute("currentUser");
		Album album = new Album(albumName, albumVisitors, albumDescription, new Date(), null, currentAuthUser.getUsername());
		try {
			albumService.addAlbum(album);

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
	
	protected void deleteAlbumById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			albumService.deleteAlbumById(id);
			
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