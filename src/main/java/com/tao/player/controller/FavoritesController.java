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
import com.tao.player.pojo.Favorites;
import com.tao.player.service.IFavoritesService;
import com.tao.player.service.impl.FavoritesService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/FavoritesController")
public class FavoritesController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IFavoritesService favoritesService = (IFavoritesService)new ProxyService().setTarget(new FavoritesService()).getProxy();

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
	
	protected void findFavoritesById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			Favorites favorites = favoritesService.findFavoritesById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",favorites);
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
	
	protected void addFavorites(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		Favorites favorites = new Favorites(userId, musicId);
		try {
			favoritesService.addFavorites(favorites);

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
	
	protected void deleteFavoritesById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			favoritesService.deleteFavoritesById(id);
			
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