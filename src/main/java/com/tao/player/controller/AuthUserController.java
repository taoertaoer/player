package com.tao.player.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.AuthRole;
import com.tao.player.pojo.AuthUser;
import com.tao.player.pojo.Menu;
import com.tao.player.service.IAuthRoleService;
import com.tao.player.service.IMenuService;
import com.tao.player.service.IUserService;
import com.tao.player.service.impl.AuthRoleService;
import com.tao.player.service.impl.MenuService;
import com.tao.player.service.impl.UserService;
import com.tao.player.util.collection.CollectionBuilder;
import com.tao.player.util.page.Page;
import com.tao.player.util.proxy.ProxyService;

//@WebServlet("/AuthUserController")
public class AuthUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService userService = (IUserService) new ProxyService().setTarget(new UserService()).getProxy();

	private IMenuService menuService = (IMenuService)new ProxyService().setTarget(new MenuService()).getProxy();
	
	private IAuthRoleService authRoleService = (IAuthRoleService)new ProxyService().setTarget(new AuthRoleService()).getProxy();

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
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String temp = en.nextElement();
			session.removeAttribute(temp);
		}
		try {
			response.sendRedirect("static/login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		JSONObject json = CollectionBuilder.builder(new JSONObject()).put("code", 0).put("content", "退出登录成功").build();
		
//		try {
//			response.setContentType("application/json;charset=UTF-8");
//			response.getWriter().println(json);
//			response.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password =request.getParameter("md5_password");
		String verifyNum = request.getParameter("verifyNum");
		System.out.println("username=="+username);
		System.out.println("password=="+password);
		System.out.println("verifyNum=="+verifyNum);
		JSONObject json = null;

		if(verifyNum != null) {
			System.out.println("session  verifyNum==="+request.getSession().getAttribute("verifyNum"));
			if(verifyNum.equals(request.getSession().getAttribute("verifyNum"))) {
				AuthUser conditions = new AuthUser();
				conditions.setUsername(username);
				List<AuthUser> list = userService.findAuthUsers(conditions);
				System.out.println("list="+list);
				if(list.size()==0) {
					json = CollectionBuilder.builder(new JSONObject()).put("code", 901).put("content", "用户不存在").build();
				}else {
					AuthUser currentAuthUser = list.get(0);
					if(!password.equals(currentAuthUser.getPassword())) {
						json = CollectionBuilder.builder(new JSONObject()).put("code", 901).put("content", "密码不正确").build();
					}else {
						request.getSession().setMaxInactiveInterval(-1);
						request.getSession().setAttribute("currentAuthUser", currentAuthUser);
						AuthRole authRole = authRoleService.findAuthRoleByAuthUserId(currentAuthUser.getId());
						request.getSession().setAttribute("currentAuthRole", authRole);
						System.out.println("login currentAuthRole=="+authRole);
						List<Menu> menus = null;
						if(currentAuthUser.getUsername().equals("admin")) {
							try {
								menus = menuService.findAllMenus();
							} catch (InstantiationException | IllegalAccessException | NoSuchFieldException
									| SecurityException | IllegalArgumentException | InvocationTargetException
									| NoSuchMethodException | SQLException e) {
								e.printStackTrace();
							} 
						}else {
							menus = menuService.findMenuByRoleId(authRole.getId());
						}
						request.getSession().setAttribute("currentMenus", menus);
						json = CollectionBuilder.builder(new JSONObject()).put("code", 0).put("content", "登录成功").build();			
					}
				}
			}else {
				json = CollectionBuilder.builder(new JSONObject()).put("code", 904).put("content", "验证码输入不正确，请重新输入").build();
			}
		}else {
			json = CollectionBuilder.builder(new JSONObject()).put("code", 903).put("content", "请输入验证码").build();
		}

		
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(json);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	protected void findAllAuthUsers(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<AuthUser> list = userService.findAllAuthUsers();

			JSONObject result = new JSONObject();
			result.put("data", list);
			result.put("code", "01");
			System.out.println(result);
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | IOException |SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void findAuthUsers(AuthUser authUser, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<AuthUser> list = userService.findAuthUsers(authUser);

			JSONObject result = new JSONObject();
			result.put("data", list);
			result.put("code", "01");
			System.out.println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch ( SecurityException	| IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void findAuthUsersPaging(AuthUser authUser, Page page, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			List<AuthUser> list = userService.findAuthUsersPaging(authUser, page);
			int total = userService.findAuthUsersCount(authUser);
			JSONObject result = new JSONObject();
			result.put("data", list);
			result.put("total", total);
			result.put("code", "01");
			System.out.println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch ( SecurityException	| IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void findAuthUserById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			AuthUser authUser = userService.findAuthUserById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",authUser);
			result.put("code", "02");
			System.out.println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void addAuthUser(HttpServletRequest request, HttpServletResponse response) {
		int age = Integer.parseInt(request.getParameter("age"));
		String username = request.getParameter("username");
		String location = request.getParameter("location");
		String password = request.getParameter("password");
		AuthUser authUser = new AuthUser(username, null, password, age, location, null, null, new Date());
		try {
			userService.addAuthUser(authUser);

			JSONObject result = new JSONObject();
			result.put("code", "01");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |SQLException | IOException e) {
			e.printStackTrace();
		}
	}
//	protected void updateAuthUser(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			int id = Integer.parseInt(request.getParameter("id"));
//			System.out.println("id==" + id);
//			userService.updateAuthUser(id);
//			
//			JSONObject result = new JSONObject();
//			result.put("code", "02");
//			System.out.println(result);
//			response.getWriter().println(result);
//			response.setContentType("application/json;charset=UTF-8");
//			response.getWriter().close();
//		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
//				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {
//			e.printStackTrace();
//		}
//	}
	protected void deleteAuthUserById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			userService.deleteAuthUserById(id);
			
			JSONObject result = new JSONObject();
			result.put("code", "02");
			System.out.println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().println(result);
			response.getWriter().close();
		} catch (SecurityException | IllegalArgumentException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
