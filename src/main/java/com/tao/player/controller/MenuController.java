package com.tao.player.controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.AuthRole;
import com.tao.player.pojo.AuthUser;
import com.tao.player.pojo.Menu;
import com.tao.player.service.IMenuService;
import com.tao.player.service.impl.MenuService;
import com.tao.player.util.proxy.ProxyService;

//@WebServlet(name="/MenuController",urlPatterns = {"/menuController"})
public class MenuController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IMenuService menuService = (IMenuService)new ProxyService().setTarget(new MenuService()).getProxy();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		Integer id = request.getParameter("id")==null?null:Integer.parseInt(request.getParameter("id"));
		Integer fatherId = request.getParameter("fatherId")==null?null:Integer.parseInt(request.getParameter("fatherId"));
		String menuName = request.getParameter("menuName");
		String menuUrl = request.getParameter("menuUrl");
		String isValid = request.getParameter("isValid");
		Menu menu = new Menu(menuName, menuUrl, fatherId, isValid);
		menu.setId(id);
		
		String methodname = request.getParameter("method");
		System.out.println("methodname===="+methodname+"  menu==="+menu);
		try {
			Method method = getClass().getDeclaredMethod(methodname, Menu.class, HttpServletRequest.class,
					HttpServletResponse.class);
			Object result = method.invoke(this, menu, request, response);// 调用各自的方法
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected JSONObject findMenusRoleUser(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			List<Menu> list = (List<Menu>) request.getSession().getAttribute("currentMenus");
			AuthRole authRole = (AuthRole) request.getSession().getAttribute("currentAuthRole");
			AuthUser authUser = (AuthUser) request.getSession().getAttribute("currentAuthUser");
			result.put("menus", list);
			result.put("role", authRole);
			result.put("user", authUser);
			result.put("code", "02");
		} catch (SecurityException | IllegalArgumentException  e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	protected JSONObject findAllMenus(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			try {
				List<Menu> list = menuService.findAllMenus();
				result.put("data", list);
				result.put("count", list.size());
				result.put("code", "02");
			} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException
					| NoSuchMethodException | SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SecurityException | IllegalArgumentException  e) {
			e.printStackTrace();
		} 
		return result;
	}

	protected JSONObject findMenus(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			try {
				List<Menu> list = menuService.findMenus(menu);
				result.put("data", list);
				result.put("code", "02");
			} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException
					| NoSuchMethodException | SQLException e) {
				e.printStackTrace();
			}
		} catch (SecurityException | IllegalArgumentException  e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	protected void findMenuById(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			Menu m = menuService.findMenuById(menu.getId());
			
			JSONObject result = new JSONObject();
			result.put("data",m);
			result.put("code", "02");
			System.out.println(result);
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void addMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			menuService.addMenu(menu);

			JSONObject result = new JSONObject();
			result.put("code", "01");
			result.put("message", "新增成功");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteMenuById(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		try {
			menuService.deleteMenuById(menu.getId());
			
			JSONObject result = new JSONObject();
			result.put("code", "02");
			result.put("message", "删除成功");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (SecurityException | IllegalArgumentException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			menuService.updateMenu(menu);

			JSONObject result = new JSONObject();
			result.put("code", "03");
			result.put("message", "修改成功");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (IllegalArgumentException | SecurityException |SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}