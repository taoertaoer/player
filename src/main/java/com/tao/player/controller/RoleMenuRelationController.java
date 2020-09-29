package com.tao.player.controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.RoleMenuRelation;
import com.tao.player.service.IRoleMenuRelationService;
import com.tao.player.service.impl.RoleMenuRelationService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/RoleMenuRelationController")
public class RoleMenuRelationController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IRoleMenuRelationService roleMenuRelationService = (IRoleMenuRelationService)new ProxyService().setTarget(new RoleMenuRelationService()).getProxy();

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
	
	protected void findRoleMenuRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			RoleMenuRelation roleMenuRelation = roleMenuRelationService.findRoleMenuRelationById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",roleMenuRelation);
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
	
	protected void addRoleMenuRelation(HttpServletRequest request, HttpServletResponse response) {
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		RoleMenuRelation roleMenuRelation = new RoleMenuRelation(menuId, roleId);
		try {
			roleMenuRelationService.addRoleMenuRelation(roleMenuRelation);

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
	
	protected void deleteRoleMenuRelationById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			roleMenuRelationService.deleteRoleMenuRelationById(id);
			
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